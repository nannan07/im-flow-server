package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowInstanceLogOVo {

	private String id;

	private String instanceId;

	private String remark;

	private Date cTime;

	private FlowUserModel user;

	public FlowInstanceLogOVo() {
	}

	public FlowInstanceLogOVo(FlowInstanceLog flowInstanceLog) {
		if (flowInstanceLog != null) {
			this.id = flowInstanceLog.getId();
			this.instanceId = flowInstanceLog.getInstanceId();
			this.cTime = flowInstanceLog.getcTime();
			this.remark = flowInstanceLog.getRemark();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
