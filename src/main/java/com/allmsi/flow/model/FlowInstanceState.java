package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowInstanceStateIVo;

public class FlowInstanceState {
	private String id;

	private String instanceId;

	private String nodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private Date uTime;

	private String uUserId;
	
	private int del;

	// node
	private String nodeName;

	private String nodeType;

	// route
	private String routeName;
	
	//flow
	private String flowId;
	
	private String flowCode;
	
	private String objectId;

	public FlowInstanceState() {
	}

	public FlowInstanceState(FlowInstanceStateIVo flowInstanceStateIVo) {
		if (flowInstanceStateIVo != null) {
			this.id = flowInstanceStateIVo.getId();
			this.instanceId = flowInstanceStateIVo.getInstanceId();
			this.nodeId = flowInstanceStateIVo.getNodeId();
			this.routeId = flowInstanceStateIVo.getRouteId();
			this.preDealId = flowInstanceStateIVo.getPreDealId();
			this.sufDealId = flowInstanceStateIVo.getSufDealId();
			this.sufDealType = flowInstanceStateIVo.getSufDealType();
			this.sufAuthType = flowInstanceStateIVo.getSufAuthType();
			this.uUserId = flowInstanceStateIVo.getuUserId();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId == null ? null : instanceId.trim();
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId == null ? null : nodeId.trim();
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId == null ? null : routeId.trim();
	}

	public String getPreDealId() {
		return preDealId;
	}

	public void setPreDealId(String preDealId) {
		this.preDealId = preDealId == null ? null : preDealId.trim();
	}

	public String getSufDealId() {
		return sufDealId;
	}

	public void setSufDealId(String sufDealId) {
		this.sufDealId = sufDealId == null ? null : sufDealId.trim();
	}

	public String getSufDealType() {
		return sufDealType;
	}

	public void setSufDealType(String sufDealType) {
		this.sufDealType = sufDealType == null ? null : sufDealType.trim();
	}

	public String getSufAuthType() {
		return sufAuthType;
	}

	public void setSufAuthType(String sufAuthType) {
		this.sufAuthType = sufAuthType == null ? null : sufAuthType.trim();
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
		this.uUserId = uUserId == null ? null : uUserId.trim();
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

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
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
}