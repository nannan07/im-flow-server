package com.allmsi.flow.model.ivo;

public class FlowInstanceIVo {

	private String id;

	private String flowId;

	private String objectId;

	private String cUserId;

	public FlowInstanceIVo(String id, String flowId, String objectId, String cUserId) {
		this.id = id;
		this.flowId = flowId;
		this.objectId = objectId;
		this.cUserId = cUserId;
	}

	public FlowInstanceIVo(String flowId, String objectId, String cUserId) {
		this.flowId = flowId;
		this.objectId = objectId;
		this.cUserId = cUserId;
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

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}
}
