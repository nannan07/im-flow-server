package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.model.BusGuide;
import com.allmsi.flow.model.BusGuideNode;
import com.allmsi.flow.model.BusGuideRoute;
import com.allmsi.flow.model.OperationMenu;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowInstanceState4Deal;
import com.allmsi.flow.model.ivo.FlowTodoVo;
import com.allmsi.flow.model.ovo.FlowRouteOVo;
import com.allmsi.flow.service.FlowInstanceEngine;
import com.allmsi.flow.service.NodeDealRuleEngine;
import com.allmsi.flow.service.OperationBusService;
import com.allmsi.flow.service.RouteDealRuleEngine;
import com.allmsi.sys.util.StrUtil;

@Service
public class OperationBusServiceImpl implements OperationBusService {

	@Resource
	private RouteDealRuleEngine routeDealRuleEngine;

	@Resource
	private NodeDealRuleEngine nodeDealRuleEngine;

	@Resource
	private FlowInstanceEngine flowInstanceEngine;

	@Override
	public BusGuide guide(String flowCode, String instanceId, String instanceStateId, String nodeId, String routeId,
			String query, String userId) {
		BusGuide busGuide = new BusGuide();
		if (StrUtil.isEmpty(flowCode) || StrUtil.isEmpty(nodeId)) {
			busGuide.setType(OperationMenu.ERROR);
			busGuide.setMsg("error");
			return busGuide;
		}
		BusGuideRoute busGuideRoute = routeGuide(flowCode, nodeId, routeId, query, userId);
		String type = busGuideRoute.getType();
		if (OperationMenu.ERROR.equals(type)) {
			busGuide.setType(OperationMenu.ERROR);
			busGuide.setMsg("error");
		}
		if (OperationMenu.USER.equals(type)) {
			busGuide.setType(OperationMenu.USER);
			busGuide.setFlowUserList(nodeGuide(flowCode, instanceId, instanceStateId, userId, busGuideRoute));
			busGuide.setFlowRouteList(busGuideRoute.getFlowRouteList());
		}
		if (OperationMenu.ROUTE.equals(type)) {
			busGuide.setType(OperationMenu.ROUTE);
			busGuide.setFlowRouteList(busGuideRoute.getFlowRouteList());
		}
		if (OperationMenu.QUERY.equals(type)) {
			busGuide.setType(OperationMenu.QUERY);
			busGuide.setMsg(busGuideRoute.getMsg());
		}
		return busGuide;
	}

	private BusGuideRoute routeGuide(String flowCode, String nodeId, String routeId, String query, String userId) {
		BusGuideRoute busGuideRoute = new BusGuideRoute();
		if (StrUtil.notEmpty(routeId)) {// 路由已选定
			FlowRouteOVo flowRouteOVo = routeDealRuleEngine.getFlowRoute(routeId);
			if (flowRouteOVo != null) {
				busGuideRoute = routeDealRuleEngine.getFlowDealModelList(flowRouteOVo, userId);// 查询路由人员信息
			}
		} else {// 查询路由,筛选出最终的一条路由
			List<FlowRouteOVo> routeList = routeDealRuleEngine.guideRoute(flowCode, nodeId, query, userId);
			if (routeList != null && routeList.size() > 0) {// 存在路由
				int size = routeList.size();
				if (1 == size) {// 路由已选定
					busGuideRoute = routeDealRuleEngine.getFlowDealModelList(routeList.get(0), userId);// 查询路由人员信息
				} else {
					return routeDealRuleEngine.ruleJudgment(routeList, userId, nodeId, query);
				}
			} else {
				busGuideRoute.setType(OperationMenu.ERROR);
				busGuideRoute.setMsg("error");
			}
		}
		return busGuideRoute;
	}

	private List<FlowUserModel> nodeGuide(String flowCode, String instanceId, String instanceStateId, String userId,
			BusGuideRoute busGuideRoute) {
		BusGuideNode busGuideNode = nodeDealRuleEngine.guideNode(busGuideRoute);
		String type = busGuideNode.getType();
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		switch (type) {
		case "user"://
			list.addAll(busGuideNode.getFlowUserList());
			break;
		case "nodeId":
			guide(flowCode, instanceId, instanceStateId, busGuideNode.getNodeId(), null, null, userId);
			break;
		}
		return list;
	}

	@Override
	public String submit(String flowId, String objId, String instanceId, String nodeId, String preDealId, String remark,
			String routeId, List<FlowInstanceState4Deal> flowRouteDealList, FlowTodoVo flowTodoVo) {
		if (StrUtil.isEmpty(flowId) || StrUtil.isEmpty(nodeId) || StrUtil.isEmpty(preDealId) || StrUtil.isEmpty(routeId)
				|| flowRouteDealList == null || flowRouteDealList.size() < 1) {
			System.err.println("service submit 参数不合法");
			return "";
		}
		instanceId = flowInstanceEngine.submit(flowId, objId, instanceId, nodeId, preDealId, remark, routeId,
				flowRouteDealList, flowTodoVo);
		System.out.println("1----" + instanceId);
		return (StrUtil.isEmpty(instanceId)) ? "" : instanceId;
	}
}
