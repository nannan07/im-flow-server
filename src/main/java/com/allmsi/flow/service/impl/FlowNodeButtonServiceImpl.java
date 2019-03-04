package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeButtonMapper;
import com.allmsi.flow.model.FlowNodeButton;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowNodeButtonIVo;
import com.allmsi.flow.model.ovo.FlowNodeButtonOVo;
import com.allmsi.flow.model.ovo.FlowNodeButtonSimpleOVO;
import com.allmsi.flow.model.ovo.FlowNodeOVo;
import com.allmsi.flow.model.ovo.FlowRouteOVo;
import com.allmsi.flow.service.FlowNodeButtonService;
import com.allmsi.flow.service.FlowNodeService;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowNodeButtonServiceImpl implements FlowNodeButtonService {

	@Resource
	private FlowNodeButtonMapper flowNodeButtonDao;

	@Resource
	private FlowUserService flowUserService;

	@Resource
	private FlowNodeService flowNodeService;

	@Resource
	private FlowNodeButtonService flowNodeButtonService;

	@Resource
	private FlowRouteService flowRouteService;

	@Override
	public List<FlowNodeButtonOVo> selectNodeButtonByNodeId(String nodeId) {
		List<FlowNodeButton> flowNodeButtonPList = flowNodeButtonDao.selectByNodeId(nodeId);

		List<String> ids = new ArrayList<String>();
		for (FlowNodeButton fb : flowNodeButtonPList) {
			if (StrUtil.notEmpty(fb.getcUserId())) {
				ids.add(fb.getcUserId());
			}
		}
		Map<String, FlowUserModel> map = flowUserService.getFlowUserList(ids);
		List<FlowNodeButtonOVo> flowNodeButtonVList = new ArrayList<FlowNodeButtonOVo>();
		for (FlowNodeButton fb : flowNodeButtonPList) {
			FlowNodeButtonOVo flowNodeB = new FlowNodeButtonOVo(fb);
			if (StrUtil.notEmpty(fb.getcUserId())) {
				FlowUserModel fu = map.get(fb.getcUserId());
				if (fu != null) {
					flowNodeB.setUser(fu);
				}
			}
			flowNodeButtonVList.add(flowNodeB);
		}
		return flowNodeButtonVList;
	}

	@Override
	public FlowNodeButtonOVo selectByPrimaryKey(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		FlowNodeButton fb = flowNodeButtonDao.selectByPrimaryKey(id);
		if (fb != null) {
			FlowNodeButtonOVo fbVo = new FlowNodeButtonOVo(fb);
			if (StrUtil.notEmpty(fb.getcUserId())) {
				Map<String, FlowUserModel> map = flowUserService.getUserInfo(fb.getcUserId());
				FlowUserModel fu = map.get(fb.getcUserId());
				fbVo.setUser(fu);
			}
			return fbVo;
		}
		return null;
	}

	@Override
	public String addFlowNodeButton(FlowNodeButtonIVo flowNodeButtonIVo) {
		if (flowNodeButtonIVo == null) {
			return null;
		}
		FlowNodeButton fb = new FlowNodeButton(flowNodeButtonIVo);
		fb.setId(UUIDUtil.getUUID());
		fb.setcUserId(flowNodeButtonIVo.getcUserId());
		return (flowNodeButtonDao.insertSelective(fb) == 0) ? null : fb.getId();
	}

	@Override
	public String updateFlowNodeButton(FlowNodeButtonIVo flowNodeButtonIVo) {
		if (flowNodeButtonIVo == null || StrUtil.isEmpty(flowNodeButtonIVo.getId())) {
			return null;
		}
		FlowNodeButton fb = new FlowNodeButton(flowNodeButtonIVo);
		fb.setuUserId(flowNodeButtonIVo.getcUserId());
		return (flowNodeButtonDao.updateByPrimaryKeySelective(fb) == 0) ? null : fb.getId();
	}

	@Override
	public boolean delFlowNodeButton(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		FlowNodeButton fb = new FlowNodeButton();
		fb.setId(id);
		fb.setuUserId(uUserId);
		flowNodeButtonDao.deleteByPrimaryKey(fb);
		return true;
	}

	@Override
	public List<FlowNodeButtonOVo> getNodeButtonByInstanceId(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return null;
		}
		List<FlowNodeButtonOVo> list = new ArrayList<FlowNodeButtonOVo>();
		List<FlowNodeButton> fnbList = flowNodeButtonDao.getNodeButtonByInstanceId(instanceId);
		for (FlowNodeButton flowNodeButton : fnbList) {
			list.add(new FlowNodeButtonOVo(flowNodeButton));
		}
		return list;
	}

	@Override
	public List<FlowNodeButtonOVo> getNodeButtonByFlowId(String flowId) {
		List<FlowNodeButtonOVo> list = new ArrayList<FlowNodeButtonOVo>();
		if (StrUtil.isEmpty(flowId)) {
			return list;
		}
		List<FlowNodeOVo> nodeList = flowNodeService.selectFlowNodeByFlowId(flowId);
		for (FlowNodeOVo flowNodeOVo : nodeList) {
			if ("01".equals(flowNodeOVo.getNodeType())) {
				String nodeId = flowNodeOVo.getId();
				List<FlowRouteOVo> routeList = flowRouteService.selectRouteListByPreNode(flowId, nodeId);
				if (routeList != null && routeList.size() > 0) {
					String sufNodeId = routeList.get(0).getSufNode();// 开始节点的下一个节点
					String routed = routeList.get(0).getId();
					list.addAll(flowNodeButtonService.selectNodeButtonByNodeId(sufNodeId));
					for (FlowNodeButtonOVo flowNodeButtonOVo : list) {
						flowNodeButtonOVo.setRouteId(routed);
					}
				}
				return list;
			}
		}
		return null;
	}

	@Override
	public List<FlowNodeButtonSimpleOVO> selectNodeButtonForNodeId(String nodeId) {
		List<FlowNodeButtonSimpleOVO> flowNodeButtonVList = new ArrayList<FlowNodeButtonSimpleOVO>();
		List<FlowNodeButton> flowNodeButtonPList = flowNodeButtonDao.selectByNodeId(nodeId);
		for (FlowNodeButton flowNodeButton : flowNodeButtonPList) {
			flowNodeButtonVList
					.add(new FlowNodeButtonSimpleOVO(flowNodeButton.getButtonName(), flowNodeButton.getButtonType()));
		}
		return flowNodeButtonVList;
	}

}
