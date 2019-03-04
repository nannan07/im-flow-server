package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowInstanceStateOVo {
	private String id;

	private String instanceId;

	// node
	private String nodeId;

	private String nodeName;

	private String nodeType;

	// route
	private String routeId;

	private String routeName;

	// dealId
	private String preDealId;

	private String preDealName;

	// dealId
	private String sufDealId;

	private String sufDealType;

	private String sufDealName;

	private String sufAuthType;

	private Date uTime;

	private FlowUserModel user;

	public FlowInstanceStateOVo() {

	}

	public FlowInstanceStateOVo(FlowInstanceState flowInstanceState) {
		if (flowInstanceState != null) {
			this.id = flowInstanceState.getId();
			this.instanceId = flowInstanceState.getInstanceId();
			this.nodeId = flowInstanceState.getNodeId();
			this.nodeName = flowInstanceState.getNodeName();
			this.nodeType = flowInstanceState.getNodeType();
			this.routeId = flowInstanceState.getRouteId();
			this.routeName = flowInstanceState.getRouteName();
			this.preDealId = flowInstanceState.getPreDealId();
			this.sufDealId = flowInstanceState.getSufDealId();
			this.sufDealType = flowInstanceState.getSufDealType();
			this.sufAuthType = flowInstanceState.getSufAuthType();
			this.uTime = flowInstanceState.getuTime();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPreDealId() {
		return preDealId;
	}

	public void setPreDealId(String preDealId) {
		this.preDealId = preDealId;
	}

	public String getPreDealName() {
		return preDealName;
	}

	public void setPreDealName(String preDealName) {
		this.preDealName = preDealName;
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

	public String getSufDealName() {
		return sufDealName;
	}

	public void setSufDealName(String sufDealName) {
		this.sufDealName = sufDealName;
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

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}

}
