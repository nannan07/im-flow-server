package com.allmsi.flow.model.ovo;

import com.allmsi.flow.model.Flow;

public class Flow4ListOVo {

	private String id;

	private String flowCode;

	private String flowName;

	private String flowType;

	private String authType;
	
	public Flow4ListOVo() {

	}

	public Flow4ListOVo(Flow flow) {
		if (flow != null) {
			this.id = flow.getId();
			this.flowCode = flow.getFlowCode();
			this.flowName = flow.getFlowName();
			this.flowType = flow.getFlowType();
			this.authType = flow.getAuthType();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
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
