package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowInstanceIVo;
import com.allmsi.flow.model.ovo.FlowInstanceOVo;

public class FlowInstance {
	private String id;

	private String flowId;

	private String objectId;

	private Date cTime;

	private String cUserId;
	
	//flow
	private String flowCode;

	private String flowName;

	private String flowType;
	
	//flowNode
	private String nodeId;

	private String nodeName;

	private String routeId;

	private String routeName;
	
	public FlowInstance() {

	}
	
	public FlowInstance(FlowInstanceIVo flowInstanceIVo){
		if(flowInstanceIVo != null){
			this.id = flowInstanceIVo.getId();
			this.flowId = flowInstanceIVo.getFlowId();
			this.objectId = flowInstanceIVo.getObjectId();
			this.cUserId = flowInstanceIVo.getcUserId();
		}
		
	}

	public FlowInstance(FlowInstanceOVo flowInstanceVo) {
		if(flowInstanceVo != null){
			this.id = flowInstanceVo.getId();
			this.flowId = flowInstanceVo.getFlowId();
			this.objectId = flowInstanceVo.getObjectId();
			this.nodeId = flowInstanceVo.getNodeId();
			this.routeId = flowInstanceVo.getRouteId();
			this.nodeName = flowInstanceVo.getNodeName();
			this.routeName = flowInstanceVo.getRouteName();
			this.cTime = flowInstanceVo.getcTime();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId == null ? null : flowId.trim();
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId == null ? null : objectId.trim();
	}

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId == null ? null : cUserId.trim();
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
	
}