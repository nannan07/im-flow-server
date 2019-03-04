package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeButtonMapper;
import com.allmsi.flow.dao.FlowNodeDealMapper;
import com.allmsi.flow.dao.FlowNodeMapper;
import com.allmsi.flow.dao.FlowRouteDealMapper;
import com.allmsi.flow.dao.FlowRouteMapper;
import com.allmsi.flow.model.FlowNode;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowNodeIVo;
import com.allmsi.flow.model.ovo.FlowNodeOVo;
import com.allmsi.flow.service.FlowNodeService;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowNodeServiceImpl implements FlowNodeService {

	@Resource
	private FlowNodeMapper flowNodeDao;

	@Resource
	private FlowNodeButtonMapper flowNodeButtonDao;

	@Resource
	private FlowRouteMapper flowRouteDao;

	@Resource
	private FlowNodeDealMapper flowNodeDealDao;

	@Resource
	private FlowRouteDealMapper flowRouteDealDao;

	@Resource
	private FlowUserService flowUserService;

	@Resource
	private FlowRouteService flowRouteService;

	@Override
	public FlowNodeOVo selectTheNextOneToStart(String flowCode) {
		if (StrUtil.isEmpty(flowCode)) {
			return null;
		}
		FlowNode flowNode = flowNodeDao.selectTheNextOneToStart(flowCode);
		return new FlowNodeOVo(flowNode);
	}

	@Override
	public List<FlowNodeOVo> selectFlowNodeByFlowId(String flowId) {
		List<FlowNodeOVo> list = new ArrayList<FlowNodeOVo>();
		if (StrUtil.isEmpty(flowId)) {
			return list;
		}

		List<FlowNode> flowNodeList = flowNodeDao.selectFlowNodeByFlowId(flowId);
		List<String> ids = new ArrayList<String>();
		for (FlowNode fn : flowNodeList) {
			if (StrUtil.notEmpty(fn.getcUserId())) {
				ids.add(fn.getcUserId());
			}
		}
		Map<String, FlowUserModel> map = flowUserService.getFlowUserList(ids);
		for (FlowNode fn : flowNodeList) {
			FlowNodeOVo flowNodeOVo = new FlowNodeOVo(fn);
			if (StrUtil.notEmpty(fn.getcUserId())) {
				FlowUserModel flowUser = map.get(fn.getcUserId());
				if (flowUser != null) {
					flowNodeOVo.setUser(flowUser);
				}
			}
			list.add(flowNodeOVo);
		}
		return list;
	}

	@Override
	public FlowNodeOVo selectByPrimaryKey(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		FlowNode flowNode = flowNodeDao.selectByPrimaryKey(id);
		if (flowNode != null) {
			FlowNodeOVo flowNodeOVo = new FlowNodeOVo(flowNode);
			if (StrUtil.notEmpty(flowNode.getcUserId())) {
				Map<String, FlowUserModel> map = flowUserService.getUserInfo(flowNode.getcUserId());
				FlowUserModel flowUser = map.get(flowNode.getcUserId());
				if (flowUser != null) {
					flowNodeOVo.setUser(flowUser);
				}
			}
			return flowNodeOVo;
		}
		return null;
	}

	@Override
	public String addFlowNode(FlowNodeIVo flowNodeIVo) {
		if (flowNodeIVo == null) {
			return null;
		}
		FlowNode flowNode = new FlowNode(flowNodeIVo);
		flowNode.setcUserId(flowNodeIVo.getcUserId());
		flowNode.setId(UUIDUtil.getUUID());
		return (flowNodeDao.insertSelective(flowNode) == 0) ? null : flowNode.getId();
	}

	@Override
	public String updateFlowNode(FlowNodeIVo flowNodeIVo) {
		if (flowNodeIVo == null || StrUtil.isEmpty(flowNodeIVo.getId())) {
			return null;
		}
		FlowNode flowNode = new FlowNode(flowNodeIVo);
		flowNode.setuUserId(flowNodeIVo.getcUserId());
		return (flowNodeDao.updateByPrimaryKeySelective(flowNode) == 0) ? null : flowNode.getId();
	}

	@Override
	public boolean delFlowNode(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		flowNodeButtonDao.deleteByNodeId(id);
		flowRouteDao.deleteByNodeId(id);
		flowNodeDealDao.deleteByNodeId(id);

		FlowNode flowNode = new FlowNode();
		flowNode.setId(id);
		flowNode.setuUserId(uUserId);
		flowNodeDao.deleteByPrimaryKey(flowNode);
		return true;
	}
}
