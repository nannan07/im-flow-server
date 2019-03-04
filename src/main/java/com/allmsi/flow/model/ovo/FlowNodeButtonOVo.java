package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowNodeButton;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowNodeButtonOVo {

	private String id;

	private String nodeId;

	private String routeId;

	private String buttonName;

	private String buttonType;

	private Date cTime;

	private FlowUserModel user;

	public FlowNodeButtonOVo() {

	}

	public FlowNodeButtonOVo(FlowNodeButton flowNodeButtonPo) {
		if (flowNodeButtonPo != null) {
			this.id = flowNodeButtonPo.getId();
			this.nodeId = flowNodeButtonPo.getNodeId();
			this.buttonName = flowNodeButtonPo.getButtonName();
			this.buttonType = flowNodeButtonPo.getButtonType();
			this.cTime = flowNodeButtonPo.getcTime();
			getClass();
		}
	}

	public String getRouteId() {
		return routeId;
	}

	public void setRouteId(String routeId) {
		this.routeId = routeId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
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

}
