package com.allmsi.flow.model.ovo;

import java.util.Date;
import java.util.List;

import com.allmsi.flow.model.Flow;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowOVo {
	private String id;

	private String flowCode;

	private String flowName;

	private String flowType;

	private String pId;

	private Date cTime;
	
	private FlowUserModel user;
	
	private List<FlowOVo> childList;

	public FlowOVo() {

	}

	public FlowOVo(Flow flow) {
		if (flow != null) {
			this.id = flow.getId();
			this.flowCode = flow.getFlowCode();
			this.flowName = flow.getFlowName();
			this.flowType = flow.getFlowType();
			this.pId = flow.getpId();
			this.cTime = flow.getcTime();
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

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}

	public List<FlowOVo> getChildList() {
		return childList;
	}

	public void setChildList(List<FlowOVo> childList) {
		this.childList = childList;
	}

}
