package com.allmsi.flow.model.ovo;

import java.util.Date;
import java.util.List;

import com.allmsi.flow.model.FlowInstanceState;

public class FlowInstenceCurrencyOVO {

	private String stateId;

	private String instanceId;

	private String nodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private Date uTime;

	private String uUserId;

	// node
	private String nodeName;

	private String nodeType;

	// route
	private String routeName;

	// flow
	private String flowId;

	private String flowCode;

	private String objectId;
	
	
	//button
	private List<FlowNodeButtonSimpleOVO> list;

	public FlowInstenceCurrencyOVO() {
	}

	public FlowInstenceCurrencyOVO(FlowInstanceState flowInstanceState) {
		if (flowInstanceState != null) {
			this.stateId = flowInstanceState.getId();
			this.instanceId = flowInstanceState.getInstanceId();
			this.nodeId = flowInstanceState.getNodeId();
			this.routeId = flowInstanceState.getRouteId();
			this.preDealId = flowInstanceState.getPreDealId();
			this.sufDealId = flowInstanceState.getSufDealId();
			this.sufDealType = flowInstanceState.getSufDealType();
			this.sufAuthType = flowInstanceState.getSufAuthType();
			this.uTime = flowInstanceState.getuTime();
			this.uUserId = flowInstanceState.getuUserId();

			this.nodeName = flowInstanceState.getNodeName();
			this.nodeType = flowInstanceState.getNodeType();
			this.routeName = flowInstanceState.getRouteName();

			this.flowId = flowInstanceState.getFlowId();
			this.flowCode = flowInstanceState.getFlowCode();
			this.objectId = flowInstanceState.getObjectId();
		}
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getPreDealId() {
		return preDealId;
	}

	public void setPreDealId(String preDealId) {
		this.preDealId = preDealId;
	}

	public String getSufDealId() {
		return sufDealId;
	}

	public void setSufDealId(String sufDealId) {
		this.sufDealId = sufDealId;
	}

	public String getSufDealType() {
		return sufDealType;
	}

	public void setSufDealType(String sufDealType) {
		this.sufDealType = sufDealType;
	}

	public String getSufAuthType() {
		return sufAuthType;
	}

	public void setSufAuthType(String sufAuthType) {
		this.sufAuthType = sufAuthType;
	}

	public Date getuTime() {
		return uTime;
	}

	public void setuTime(Date uTime) {
		this.uTime = uTime;
	}

	public String getuUserId() {
		return uUserId;
	}

	public void setuUserId(String uUserId) {
		this.uUserId = uUserId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public List<FlowNodeButtonSimpleOVO> getList() {
		return list;
	}

	public void setList(List<FlowNodeButtonSimpleOVO> list) {
		this.list = list;
	}
}
