package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowAuth;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowAuthOVo {

	private String id;

	private String flowId;

	private String authType;

	private String authDealId;

	private String authDealType;
	
	private String authDealName;

	private Date cTime;

	private FlowUserModel user;

	public FlowAuthOVo() {

	}

	public FlowAuthOVo(FlowAuth fa) {
		if (fa != null) {
			this.id = fa.getId();
			this.flowId = fa.getFlowId();
			this.authType = fa.getAuthType();
			this.authDealId = fa.getAuthDealId();
			this.authDealType = fa.getAuthDealType();
			this.cTime = fa.getcTime();
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

	public Date getcTime() {
		return cTime;
	}

	public void setcTime(Date cTime) {
		this.cTime = cTime;
	}

	public String getAuthDealName() {
		return authDealName;
	}

	public void setAuthDealName(String authDealName) {
		this.authDealName = authDealName;
	}

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}

}
