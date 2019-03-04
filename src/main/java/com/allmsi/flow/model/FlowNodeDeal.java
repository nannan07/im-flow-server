package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowNodeDealIVo;

public class FlowNodeDeal {
	private String id;

	private String nodeId;

	private String nodeDealId;

	private String nodeDealType;

	private Date cTime;

	private String cUserId;

	private Date uTime;

	private String uUserId;
	
	private String continuation;

	private Boolean del;

	private String routeId;

	private String nodeType;
	
	public FlowNodeDeal() {

	}

	public FlowNodeDeal(FlowNodeDealIVo flowNodeDealIVO) {
		if (flowNodeDealIVO != null) {
			this.id = flowNodeDealIVO.getId();
			this.nodeId = flowNodeDealIVO.getNodeId();
			this.nodeDealId = flowNodeDealIVO.getNodeDealId();
			this.nodeDealType = flowNodeDealIVO.getNodeDealType();
			this.continuation = flowNodeDealIVO.getContinuation();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId == null ? null : nodeId.trim();
	}

	public String getNodeDealId() {
		return nodeDealId;
	}

	public void setNodeDealId(String nodeDealId) {
		this.nodeDealId = nodeDealId == null ? null : nodeDealId.trim();
	}

	public String getNodeDealType() {
		return nodeDealType;
	}

	public void setNodeDealType(String nodeDealType) {
		this.nodeDealType = nodeDealType == null ? null : nodeDealType.trim();
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

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getContinuation() {
		return continuation;
	}

	public void setContinuation(String continuation) {
		this.continuation = continuation;
	}
}