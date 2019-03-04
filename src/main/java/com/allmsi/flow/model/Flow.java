package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowIVo;

public class Flow {
	private String id;

	private String flowCode;

	private String flowName;

	private String flowType;

	private String pId;

	private Date cTime;

	private String cUserId;

	private Date uTime;

	private String uUserId;

	private Boolean del;

	private String authType;

	public Flow() {

	}

	public Flow(FlowIVo flowVo) {
		if (flowVo != null) {
			this.id = flowVo.getId();
			this.flowCode = flowVo.getFlowCode();
			this.flowName = flowVo.getFlowName();
			this.flowType = flowVo.getFlowType();
			this.pId = flowVo.getpId();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
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
		this.cUserId = cUserId;
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
		this.uUserId = uUserId;
	}

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

}