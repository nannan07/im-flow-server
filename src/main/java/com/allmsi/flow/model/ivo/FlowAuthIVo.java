package com.allmsi.flow.model.ivo;

public class FlowAuthIVo {

	private String id;

    private String flowId;

    private String authType;

    private String authDealId;

    private String authDealType;

    private String cUserId;

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

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthDealId() {
		return authDealId;
	}

	public void setAuthDealId(String authDealId) {
		this.authDealId = authDealId;
	}

	public String getAuthDealType() {
		return authDealType;
	}

	public void setAuthDealType(String authDealType) {
		this.authDealType = authDealType;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}
}
