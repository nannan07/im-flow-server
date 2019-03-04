package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowRouteIVo;
import com.allmsi.flow.model.ovo.FlowRouteOVo;

public interface FlowRouteService {

	List<FlowRouteOVo> selectRouteList(String flowId);
	
	FlowRouteOVo selectRouteInfo(String id);

	String addFlowRoute(FlowRouteIVo flowRouteIVo);

	String updateFlowRoute(FlowRouteIVo flowRouteIVo);

	boolean delFlowRoute(String id, String uUserId);

	List<FlowRouteOVo> selectRouteListByPreNode(String flowCode, String preNode);

	String selectSufNode(String routeId);

	List<FlowRouteOVo> selectRouteListBySufNode(String flowId,String nodeId);

}
