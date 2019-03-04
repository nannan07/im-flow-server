package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowRouteIVo;

public class FlowRoute {
	private String id;

	private String flowId;
	
	private String flowCode;

	private String routeName;

	private String preNode;

	private String sufNode;

	private Integer isBack;

	private Date cTime;

	private String cUserId;

	private Date uTime;

	private String uUserId;

	private Boolean del;

	private String preNodeName;

	private String preNodeType;

	private String sufNodeName;

	private String sufNodeType;
	
	//路由处理
	private String routeDealType;

	private String query;

	public FlowRoute() {

	}

	public FlowRoute(FlowRouteIVo flowRouteIVo) {
		if (flowRouteIVo != null) {
			this.id = flowRouteIVo.getId();
			this.flowId = flowRouteIVo.getFlowId();
			this.routeName = flowRouteIVo.getRouteName();
			this.preNode = flowRouteIVo.getPreNode();
			this.sufNode = flowRouteIVo.getSufNode();
			this.isBack = flowRouteIVo.getIsBack();
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

	public String getRouteName() {
		return routeName;
	}

	public void setRouteName(String routeName) {
		this.routeName = routeName == null ? null : routeName.trim();
	}

	public String getPreNode() {
		return preNode;
	}

	public void setPreNode(String preNode) {
		this.preNode = preNode == null ? null : preNode.trim();
	}

	public String getSufNode() {
		return sufNode;
	}

	public void setSufNode(String sufNode) {
		this.sufNode = sufNode == null ? null : sufNode.trim();
	}

	public Integer getIsBack() {
		return isBack;
	}

	public void setIsBack(Integer isBack) {
		this.isBack = isBack;
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

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public String getPreNodeName() {
		return preNodeName;
	}

	public void setPreNodeName(String preNodeName) {
		this.preNodeName = preNodeName;
	}

	public String getPreNodeType() {
		return preNodeType;
	}

	public void setPreNodeType(String preNodeType) {
		this.preNodeType = preNodeType;
	}

	public String getSufNodeName() {
		return sufNodeName;
	}

	public void setSufNodeName(String sufNodeName) {
		this.sufNodeName = sufNodeName;
	}

	public String getSufNodeType() {
		return sufNodeType;
	}

	public void setSufNodeType(String sufNodeType) {
		this.sufNodeType = sufNodeType;
	}

	public String getRouteDealType() {
		return routeDealType;
	}

	public void setRouteDealType(String routeDealType) {
		this.routeDealType = routeDealType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}
}