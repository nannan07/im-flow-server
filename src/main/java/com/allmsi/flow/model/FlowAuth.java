package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowAuthIVo;

public class FlowAuth {
	private String id;

	private String flowId;

	private String authType;

	private String authDealId;

	private String authDealType;

	private Date cTime;

	private String cUserId;

	private Date uTime;

	private String uUserId;

	private Boolean del;

	public FlowAuth() {

	}

	public FlowAuth(FlowAuthIVo flowAuthIVo) {
		if (flowAuthIVo != null) {
			this.id = flowAuthIVo.getId();
			this.flowId = flowAuthIVo.getFlowId();
			this.authType = flowAuthIVo.getAuthType();
			this.authDealId = flowAuthIVo.getAuthDealId();
			this.authDealType = flowAuthIVo.getAuthDealType();
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

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType == null ? null : authType.trim();
	}

	public String getAuthDealId() {
		return authDealId;
	}

	public void setAuthDealId(String authDealId) {
		this.authDealId = authDealId == null ? null : authDealId.trim();
	}

	public String getAuthDealType() {
		return authDealType;
	}

	public void setAuthDealType(String authDealType) {
		this.authDealType = authDealType == null ? null : authDealType.trim();
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
}