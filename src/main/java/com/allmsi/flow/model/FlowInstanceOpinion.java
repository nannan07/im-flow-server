package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowInstanceOpinionIVo;

public class FlowInstanceOpinion {
	private String id;

	private String instanceLogId;

	private String opinion;

	private Date cTime;

	private String cUserId;

	private Date uTime;

	private Boolean del;

	public FlowInstanceOpinion() {

	}

	public FlowInstanceOpinion(FlowInstanceOpinionIVo flowInstanceOpinionIVo) {
		if (flowInstanceOpinionIVo != null) {
			this.id = flowInstanceOpinionIVo.getId();
			this.instanceLogId = flowInstanceOpinionIVo.getInstanceLogId();
			this.opinion = flowInstanceOpinionIVo.getOpinion();
			this.cUserId = flowInstanceOpinionIVo.getcUserId();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getInstanceLogId() {
		return instanceLogId;
	}

	public void setInstanceLogId(String instanceLogId) {
		this.instanceLogId = instanceLogId == null ? null : instanceLogId.trim();
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion == null ? null : opinion.trim();
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

	public Boolean getDel() {
		return del;
	}

	public void setDel(Boolean del) {
		this.del = del;
	}
}