package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceMapper;
import com.allmsi.flow.dao.FlowNodeDealMapper;
import com.allmsi.flow.dao.FlowRouteMapper;
import com.allmsi.flow.model.FlowDealType;
import com.allmsi.flow.model.FlowInstance;
import com.allmsi.flow.model.FlowNodeDeal;
import com.allmsi.flow.model.FlowRoute;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowInstance4ListIVo;
import com.allmsi.flow.model.ivo.FlowInstanceIVo;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVo;
import com.allmsi.flow.model.ovo.FlowInstanceOVo;
import com.allmsi.flow.service.FlowInstanceService;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceServiceImpl implements FlowInstanceService {

	@Resource
	private FlowInstanceMapper flowInstanceDao;

	@Resource
	private FlowUserService flowUserService;

	@Resource
	private FlowInstanceStateService flowInstanceStateService;

	@Resource
	private FlowRouteMapper flowRouteDao;

	@Resource
	private FlowNodeDealMapper flowNodeDealDao;

	@Override
	public int selectCountInstanceList(FlowInstance4ListIVo fIListIVo) {
		if (fIListIVo == null || StrUtil.isEmpty(fIListIVo.getUserId())) {
			return 0;
		}
		Map<String, Object> map = getMap(fIListIVo);
		int total = flowInstanceDao.countFlowInstanceList(map);
		return total;
	}

	@Override
	public List<FlowInstanceOVo> selectInstanceList(FlowInstance4ListIVo fIListIVo, Integer page, Integer pageSize) {
		List<FlowInstanceOVo> flowInstanceOVoList = new ArrayList<FlowInstanceOVo>();
		if (fIListIVo == null || StrUtil.isEmpty(fIListIVo.getUserId())) {
			return flowInstanceOVoList;
		}
		Map<String, Object> map = getMap(fIListIVo, page, pageSize);
		List<FlowInstance> flowInstancePoList = flowInstanceDao.selectInstanceList(map);
		for (FlowInstance flowInstancePo : flowInstancePoList) {
			flowInstanceOVoList.add(new FlowInstanceOVo(flowInstancePo));
		}
		return flowInstanceOVoList;
	}

	@Override
	public int selectCountInstanceMyList(FlowInstance4ListIVo fIListIVo) {
		if (fIListIVo == null || StrUtil.isEmpty(fIListIVo.getUserId())) {
			return 0;
		}
		String user = fIListIVo.getUserId();
		Map<String, List<String>> authMap = flowUserService.getUserAuthIdSort(user);
		String flowName = fIListIVo.getFlowName();
		String flowType = fIListIVo.getFlowType();
		Map<String, Object> map = getMap(flowName, flowType, authMap);
		int total = flowInstanceDao.countFlowInstanceMyList(map);
		return total;
	}

	@Override
	public List<FlowInstanceOVo> selectInstanceMyList(FlowInstance4ListIVo fIListIVo, Integer page, Integer pageSize) {
		List<FlowInstanceOVo> flowInstanceOVoList = new ArrayList<FlowInstanceOVo>();
		if (fIListIVo == null || StrUtil.isEmpty(fIListIVo.getUserId())) {
			return flowInstanceOVoList;
		}
		String user = fIListIVo.getUserId();
		Map<String, List<String>> authMap = flowUserService.getUserAuthIdSort(user);
		String flowName = fIListIVo.getFlowName();
		String flowType = fIListIVo.getFlowType();
		Map<String, Object> map = getMap(flowName, flowType, authMap, page, pageSize);
		List<FlowInstance> flowInstancePoList = flowInstanceDao.selectInstanceMyList(map);
		List<String> userIds = new ArrayList<String>();
		for (FlowInstance flowInstancePo : flowInstancePoList) {
			String userId = flowInstancePo.getcUserId();
			if (StrUtil.isEmpty(userId)) {
				continue;
			}
			userIds.add(userId);
		}
		Map<String, FlowUserModel> flowUserMap = flowUserService.getFlowUserList(userIds);

		for (FlowInstance flowInstancePo : flowInstancePoList) {
			FlowInstanceOVo flowInstanceOVo = new FlowInstanceOVo(flowInstancePo);
			String userId = flowInstancePo.getcUserId();
			if (StrUtil.isEmpty(userId)) {
				flowInstanceOVoList.add(flowInstanceOVo);
				continue;
			}
			flowInstanceOVo.setUser(flowUserMap.get(userId));
			flowInstanceOVoList.add(flowInstanceOVo);
		}
		return flowInstanceOVoList;
	}

	@Override
	public String addFlowInstance(FlowInstanceIVo flowInstanceIVo) {
		if (flowInstanceIVo == null || StrUtil.isEmpty(flowInstanceIVo.getFlowId())) {
			return null;
		}
		FlowInstance fi = new FlowInstance(flowInstanceIVo);
		String id = UUIDUtil.getUUID();
		fi.setId(id);

		flowInstanceDao.insertSelective(fi);// 添加流程实例
		// flowId查询流程的下一节点
		FlowRoute route = flowRouteDao.selectsufNodeByFlowId(flowInstanceIVo.getFlowId());
		FlowRoute sufNode = flowRouteDao.selectSufNodeByPreNode(route.getSufNode());
		// 查询节点办理信息
		List<FlowNodeDeal> dealList = flowNodeDealDao.selectByNodeId(sufNode.getSufNode());
		List<FlowInstanceStateIVo> list = new ArrayList<FlowInstanceStateIVo>();
		for (FlowNodeDeal flowNodeDeal : dealList) {
			FlowInstanceStateIVo vo = new FlowInstanceStateIVo();
			vo.setInstanceId(id);
			vo.setNodeId(flowNodeDeal.getNodeId());
			vo.setRouteId(sufNode.getId());
			vo.setPreDealId(flowInstanceIVo.getcUserId());
			vo.setSufDealId(flowNodeDeal.getNodeDealId());
			vo.setSufDealType(flowNodeDeal.getNodeDealType());
			vo.setSufAuthType("01");
			list.add(vo);
		}
		flowInstanceStateService.addFlowInstanceStates(list);
		return id;
	}

	@Override
	public boolean delFlowInstance(String id) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		flowInstanceDao.deleteByPrimaryKey(id);
		return true;
	}

	private Map<String, Object> getMap(String flowName, String flowType, Map<String, List<String>> authMap) {
		return getMap(flowName, flowType, authMap, null, null);
	}

	private Map<String, Object> getMap(String flowName, String flowType, Map<String, List<String>> authMap,
			Integer page, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flowName", flowName);
		map.put("flowType", flowType);
		List<FlowDealType> faList = new ArrayList<FlowDealType>();
		for (String authDealType : authMap.keySet()) {
			List<String> authDealIdList = authMap.get(authDealType);
			for (String authDealId : authDealIdList) {
				FlowDealType fa = new FlowDealType();
				fa.setAuthDealType(authDealType);
				fa.setAuthDealId(authDealId);
				faList.add(fa);
			}
		}
		map.put("flowDealType", faList);
		if (page != null && pageSize != null) {
			map.put("page", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		return map;
	}

	private Map<String, Object> getMap(FlowInstance4ListIVo flowInstanceIVo) {
		return getMap(flowInstanceIVo, null, null);
	}

	private Map<String, Object> getMap(FlowInstance4ListIVo flowInstanceIVo, Integer page, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", flowInstanceIVo.getUserId());
		map.put("flowName", flowInstanceIVo.getFlowName());
		map.put("flowType", flowInstanceIVo.getFlowType());
		if (page != null && pageSize != null) {
			map.put("page", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		return map;
	}

	@Override
	public List<FlowInstanceOVo> getFlowInstanceInfoByObjectId(String objId) {
		if (StrUtil.isEmpty(objId)) {
			return null;
		}
		List<FlowInstance> fiList = flowInstanceDao.getFlowInstanceInfoByObjectId(objId);
		List<FlowInstanceOVo> list = new ArrayList<FlowInstanceOVo>();
		for (FlowInstance flowInstance : fiList) {
			list.add(new FlowInstanceOVo(flowInstance));
		}
		return list;
	}

	@Override
	public List<String> selectInstanceByObjId(String objId) {
		List<String> list = new ArrayList<String>();
		List<FlowInstance> fiList = flowInstanceDao.selectInstanceByObjId(objId);
		for (FlowInstance flowInstance : fiList) {
			list.add(flowInstance.getId());
		}
		return list;
	}

	@Override
	public String insertFlowInstance(FlowInstanceIVo flowInstanceIVo) {
		FlowInstance fi = new FlowInstance(flowInstanceIVo);
		String id = UUIDUtil.getUUID();
		fi.setId(id);
		int msg = flowInstanceDao.insertSelective(fi);// 添加流程实例
		return (msg == 0) ? "" : id;
	}

	@Override
	public FlowInstanceOVo selectById(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return null;
		}
		return new FlowInstanceOVo(flowInstanceDao.selectByPrimaryKey(instanceId));
	}
}
