package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowAuthMapper;
import com.allmsi.flow.dao.FlowInstanceMapper;
import com.allmsi.flow.dao.FlowMapper;
import com.allmsi.flow.dao.FlowNodeMapper;
import com.allmsi.flow.dao.FlowRouteMapper;
import com.allmsi.flow.model.Flow;
import com.allmsi.flow.model.FlowDealType;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowIVo;
import com.allmsi.flow.model.ovo.Flow4ListOVo;
import com.allmsi.flow.model.ovo.FlowOVo;
import com.allmsi.flow.service.FlowService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowServiceImpl implements FlowService {

	@Resource
	private FlowMapper flowDao;

	@Resource
	private FlowNodeMapper flowNodeDao;

	@Resource
	private FlowRouteMapper flowRouteDao;

	@Resource
	private FlowAuthMapper flowAuthDao;

	@Resource
	private FlowInstanceMapper flowInstanceDao;

	@Resource
	private FlowUserService flowUserService;

	@Override
	public String selectFlowCodeById(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		Flow flow = flowDao.selectByPrimaryKey(id);
		return (flow != null) ? flow.getFlowCode() : "";
	}
	
	
	@Override
	public int selectMyFlowListCount(String userId, String flowName) {
		Map<String, List<String>> authMap = flowUserService.getUserAuthIdSort(userId);
		return selectMyFlowListCount(authMap, flowName);
	}

	@Override
	public int selectMyFlowListCount(Map<String, List<String>> authMap, String flowName) {
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flowDealType", faList);
		map.put("flowName", flowName);
		return flowDao.selectMyFlowListCount(map);
	}

	@Override
	public List<Flow4ListOVo> selectMyFlowList(String userId, String flowName, Integer page, Integer pageSize) {
		Map<String, List<String>> authMap = flowUserService.getUserAuthIdSort(userId);
		return selectMyFlowList(authMap, flowName, page, pageSize);
	}

	@Override
	public List<Flow4ListOVo> selectMyFlowList(Map<String, List<String>> authMap, String flowName, Integer page,
			Integer pageSize) {
		List<Flow4ListOVo> list = new ArrayList<Flow4ListOVo>();
		if (authMap == null) {
			return list;
		}
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
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("flowDealType", faList);
		map.put("flowName", flowName);
		if (page != null && pageSize != null) {
			map.put("page", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		List<Flow> fList = flowDao.selectMyFlowList(map);
		for (Flow f : fList) {
			list.add(new Flow4ListOVo(f));
		}
		return list;
	}

	@Override
	public int selectConut(String flowType) {
		if (StrUtil.isEmpty(flowType)) {
			flowType = "01";
		}
		return flowDao.selectConut(flowType);
	}

	@Override
	public List<FlowOVo> selectFlowList(String flowType, Integer page, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (page != null && pageSize != null) {
			map.put("page", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		if (StrUtil.notEmpty(flowType)) {
			map.put("flowType", flowType);
		}
		List<Flow> flowList = flowDao.selectFlowInfoList(map);
		List<String> ids = new ArrayList<String>();
		for (Flow flow : flowList) {
			if (StrUtil.notEmpty(flow.getcUserId())) {
				ids.add(flow.getcUserId());
			}
		}
		List<FlowOVo> list = new ArrayList<FlowOVo>();
		Map<String, FlowUserModel> FlowUserMap = flowUserService.getFlowUserList(ids);
		for (Flow flow : flowList) {
			FlowOVo flowOVo = new FlowOVo(flow);
			if (StrUtil.notEmpty(flow.getcUserId())) {
				FlowUserModel flowUser = FlowUserMap.get(flow.getcUserId());
				if (flowUser != null) {
					flowOVo.setUser(flowUser);
				}
			}
			list.add(flowOVo);
		}
		return list;
	}

	@Override
	public FlowOVo selectFlowInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		FlowOVo flowOVo = selectByPrimaryKey(id);
		if (flowOVo != null) {
			flowOVo.setChildList(getChildList(id));
		}
		return flowOVo;
	}

	@Override
	public String addFlow(FlowIVo flowVo) {
		if (flowVo == null) {
			return null;
		}
		Flow flow = new Flow(flowVo);
		flow.setcUserId(flowVo.getcUserId());
		flow.setId(UUIDUtil.getUUID());
		return (flowDao.insertSelective(flow) == 0) ? null : flow.getId();
	}

	@Override
	public String updateFlow(FlowIVo flowVo) {
		if (flowVo == null || StrUtil.isEmpty(flowVo.getId())) {
			return null;
		}
		Flow flow = new Flow(flowVo);
		flow.setuUserId(flowVo.getcUserId());
		return (flowDao.updateByPrimaryKeySelective(flow) == 0) ? null : flow.getId();
	}

	@Override
	public boolean delFlow(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		List<String> ids = flowDao.selectByPId(id);
		for (String string : ids) {
			delFlow(string, uUserId);
		}
		flowDao.deleteByPId(id);
		flowNodeDao.deleteByFlowId(id);
		flowRouteDao.deleteByFlowId(id);
		flowAuthDao.deleteByFlowId(id);
		flowInstanceDao.deleteByFlowId(id);
		Flow flowInfo = new Flow();
		flowInfo.setId(id);
		flowInfo.setuUserId(uUserId);
		flowDao.deleteByPrimaryKey(flowInfo);
		return true;
	}

	private FlowOVo selectByPrimaryKey(String id) {
		Flow flow = flowDao.selectByPrimaryKey(id);
		if (flow != null) {
			FlowOVo flowOVo = new FlowOVo(flow);
			if (StrUtil.notEmpty(flow.getcUserId())) {
				Map<String, FlowUserModel> map = flowUserService.getUserInfo(flow.getcUserId());
				FlowUserModel flowUser = map.get(flow.getcUserId());
				if (flowUser != null) {
					flowOVo.setUser(flowUser);
				}
			}
			return flowOVo;
		}
		return null;
	}

	private List<FlowOVo> getChildList(String id) {
		List<FlowOVo> childList = new ArrayList<FlowOVo>();
		List<String> ids = flowDao.selectByPId(id);
		for (String string : ids) {
			FlowOVo flowOVo = selectByPrimaryKey(string);
			if (flowOVo != null) {
				flowOVo.setChildList(getChildList(string));
				childList.add(flowOVo);
			}
		}
		return childList;
	}

}
