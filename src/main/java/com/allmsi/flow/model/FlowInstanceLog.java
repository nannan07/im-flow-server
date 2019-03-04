package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowInstanceLogIVo;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVo;

public class FlowInstanceLog {
	private String id;

	private String instanceId;

	private String nodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private String remark;

	private Date cTime;

	private String cUserId;

	public FlowInstanceLog() {
	}

	public FlowInstanceLog(FlowInstanceLogIVo flowInstanceLogIVo) {
		if (flowInstanceLogIVo != null) {
			this.instanceId = flowInstanceLogIVo.getInstanceId();
			this.nodeId = flowInstanceLogIVo.getNodeId();
			this.routeId = flowInstanceLogIVo.getRouteId();
			this.preDealId = flowInstanceLogIVo.getPreDealId();
			this.sufDealId = flowInstanceLogIVo.getSufDealId();
			this.sufAuthType = flowInstanceLogIVo.getSufAuthType();
			this.sufDealType = flowInstanceLogIVo.getSufDealType();
			this.cUserId = flowInstanceLogIVo.getcUserId();
		}
	}

	public FlowInstanceLog(FlowInstanceStateIVo flowInstanceStateIVo) {
		this.instanceId = flowInstanceStateIVo.getInstanceId();
		this.nodeId = flowInstanceStateIVo.getNodeId();
		this.routeId = flowInstanceStateIVo.getRouteId();
		this.preDealId = flowInstanceStateIVo.getPreDealId();
		this.sufDealId = flowInstanceStateIVo.getSufDealId();
		this.sufAuthType = flowInstanceStateIVo.getSufAuthType();
		this.sufDealType = flowInstanceStateIVo.getSufDealType();
		this.cUserId = flowInstanceStateIVo.getuUserId();
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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
}