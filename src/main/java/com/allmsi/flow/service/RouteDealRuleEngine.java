package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.BusGuideRoute;
import com.allmsi.flow.model.ovo.FlowRouteOVo;

public interface RouteDealRuleEngine {

	public BusGuideRoute ruleJudgment(List<FlowRouteOVo> frList, String userId, String nodeId, String query);

	public List<FlowRouteOVo> guideRoute(String flowId, String nodeId, String query, String userId);

	public FlowRouteOVo getFlowRoute(String routeId);

	public BusGuideRoute getFlowDealModelList(FlowRouteOVo flowRouteOVo, String userId);

}
