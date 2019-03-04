package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.model.BusGuideNode;
import com.allmsi.flow.model.BusGuideRoute;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowNodeDealOVo;
import com.allmsi.flow.model.ovo.FlowRouteOVo;
import com.allmsi.flow.service.FlowNodeDealService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.flow.service.NodeDealRuleEngine;

@Service
public class NodeDealRuleEngineImpl implements NodeDealRuleEngine {

	@Resource
	private FlowNodeDealService flowNodeDealService;

	@Resource
	private FlowUserService flowUserService;

	@Override
	public BusGuideNode guideNode(BusGuideRoute busGuideRoute) {
		boolean flag = busGuideRoute.isFlag();
		List<FlowUserModel> routelist = new ArrayList<FlowUserModel>();// 路由处理人
		routelist.addAll(busGuideRoute.getRouteUserList());
		if (flag) {// 是否要路由人员
			return new BusGuideNode("user", "user", routelist);
		}
		List<FlowRouteOVo> flowRouteList = busGuideRoute.getFlowRouteList();
		FlowRouteOVo flowRouteOVo = flowRouteList.get(0);
		String nodeId = flowRouteOVo.getSufNode();
		List<FlowNodeDealOVo> fndList = flowNodeDealService.selectFlowNodeDealListByNodeId(nodeId);
		List<FlowUserModel> nodelist = getNodeDealUser(fndList, nodeId);// 节点处理人
		List<FlowUserModel> list = removeRepetition(routelist, nodelist);//取交集
		if (list != null && list.size() > 0) {
			return new BusGuideNode("user", "user", list);
		} else {// 为空
			String isContinuation = fndList.get(0).getContinuation();
			if ("01".equals(isContinuation)) {// 继续
				return new BusGuideNode("nodeId", "nodeId", nodeId);
			} else {
				List<FlowUserModel> index = new ArrayList<FlowUserModel>();// 查询所有用户
				// return new BusGuideNode("user", "user", flowUserService.selectUserList());
				return new BusGuideNode("user", "user", index);
			}
		}
	}

	private List<FlowUserModel> getNodeDealUser(List<FlowNodeDealOVo> fndList, String nodeId) {
		List<FlowUserModel> nodelist = new ArrayList<FlowUserModel>();// 节点处理人
		for (FlowNodeDealOVo flowNodeDealOVo : fndList) {
			String nodeDealId = flowNodeDealOVo.getNodeDealId();
			String nodeDealType = flowNodeDealOVo.getNodeDealType();
			switch (nodeDealType) {
			case "01":// 查询用户
				nodelist.add(flowUserService.selectUserInfo(nodeDealId));
				break;
			case "02":// 查询部门
				nodelist.addAll(flowUserService.selectUserByDeptId(nodeDealId));
				break;
			case "03":// 查询角色
				nodelist.addAll(flowUserService.selectUserByRoleId(nodeDealId));
				break;
			}
		}
		return nodelist;
	}

	private List<FlowUserModel> removeRepetition(List<FlowUserModel> routelist, List<FlowUserModel> nodelist) {
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		if (routelist != null && routelist.size() > 0 && nodelist != null && nodelist.size() > 0) {
			list.addAll(nodelist);
			nodelist.removeAll(routelist);
			list.removeAll(nodelist);
		} else {
			list.addAll(nodelist);
			list.addAll(routelist);
		}
		return list;
	}
}
