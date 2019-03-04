package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowNodeButtonIVo;

public class FlowNodeButton {
	private String id;

	private String nodeId;

	private String buttonName;

	private String buttonType;

	private Date cTime;

	private String cUserId;

	private Date uTime;

	private String uUserId;

	private Boolean del;

	public FlowNodeButton() {

	}

	public FlowNodeButton(FlowNodeButtonIVo flowNodeButtonVo) {
		this.id = flowNodeButtonVo.getId();
		this.nodeId = flowNodeButtonVo.getNodeId();
		this.buttonName = flowNodeButtonVo.getButtonName();
		this.buttonType = flowNodeButtonVo.getButtonType();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId == null ? null : nodeId.trim();
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName == null ? null : buttonName.trim();
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType == null ? null : buttonType.trim();
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