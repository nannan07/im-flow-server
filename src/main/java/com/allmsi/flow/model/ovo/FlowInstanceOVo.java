package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowInstance;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowInstanceOVo {

	private String id;

	private String flowId;

	private String objectId;

	private Date cTime;

	// flow
	private String flowCode;

	private String flowName;

	private String flowType;
	

	private String nodeId;

	private String nodeName;

	private String routeId;

	private String routeName;

	private FlowUserModel user;

	public FlowInstanceOVo() {

	}

	public FlowInstanceOVo(FlowInstance flowInstancePo) {
		if (flowInstancePo != null) {
			this.id = flowInstancePo.getId();
			this.flowId = flowInstancePo.getFlowId();
			this.objectId = flowInstancePo.getObjectId();
			this.cTime = flowInstancePo.getcTime();
			this.flowCode = flowInstancePo.getFlowCode();
			this.flowName = flowInstancePo.getFlowName();
			this.flowType = flowInstancePo.getFlowType();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		this.flowType = flowType;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName;
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}
}
