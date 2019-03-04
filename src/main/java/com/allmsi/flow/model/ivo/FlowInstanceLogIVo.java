package com.allmsi.flow.model.ivo;

public class FlowInstanceLogIVo {

	private String instanceId;

	private String nodeId;

	private String routeId;

	private String preDealId;

	private String sufDealId;

	private String sufDealType;

	private String sufAuthType;

	private String remark;

	private String cUserId;

	public FlowInstanceLogIVo() {

	}

	public FlowInstanceLogIVo(String instanceId, String nodeId, String routeId, String preDealId, String sufDealId,
			String sufDealType, String sufAuthType, String remark, String cUserId) {
		this.instanceId = instanceId;
		this.nodeId = nodeId;
		this.routeId = routeId;
		this.preDealId = preDealId;
		this.sufDealId = sufDealId;
		this.sufDealType = sufDealType;
		this.sufAuthType = sufAuthType;
		this.remark = remark;
		this.cUserId = cUserId;
	}

	public FlowInstanceLogIVo(FlowInstanceStateIVo flowInstanceStateIVo) {
		if (flowInstanceStateIVo != null) {
			this.instanceId = flowInstanceStateIVo.getInstanceId();
			this.nodeId = flowInstanceStateIVo.getNodeId();
			this.routeId = flowInstanceStateIVo.getRouteId();
			this.preDealId = flowInstanceStateIVo.getPreDealId();
			this.sufDealId = flowInstanceStateIVo.getSufDealId();
			this.sufDealType = flowInstanceStateIVo.getSufDealType();
			this.sufAuthType = flowInstanceStateIVo.getSufAuthType();
			this.cUserId = flowInstanceStateIVo.getuUserId();
			this.remark = flowInstanceStateIVo.getRemark();
		}
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}
}
