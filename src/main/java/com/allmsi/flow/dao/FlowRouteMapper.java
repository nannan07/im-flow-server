package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowRoute;

public interface FlowRouteMapper {

	List<FlowRoute> selectRouteList(String flowId);

	int deleteByPrimaryKey(FlowRoute flowRoute);

	int insertSelective(FlowRoute record);

	FlowRoute selectByPrimaryKey(String id);

	int updateByPrimaryKeySelective(FlowRoute record);

	List<FlowRoute> selectRouteListByPreNode(FlowRoute flowRoute);

	int deleteByNodeId(String nodeId);

	int deleteByFlowId(String flowId);

	String selectSufNode(String routeId);
	
	FlowRoute selectsufNodeByFlowId(String flowId);

	FlowRoute selectSufNodeByPreNode(String sufNode);

	List<FlowRoute> selectRouteListBySufNode(FlowRoute flowRoute);
}