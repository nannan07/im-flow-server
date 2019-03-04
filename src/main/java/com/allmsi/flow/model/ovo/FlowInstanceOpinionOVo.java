package com.allmsi.flow.model.ovo;

import com.allmsi.flow.model.FlowInstanceOpinion;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowInstanceOpinionOVo {

	private String id;

	private String instanceLogId;

	private String opinion;

	private FlowUserModel user;

	public FlowInstanceOpinionOVo() {

	}

	public FlowInstanceOpinionOVo(FlowInstanceOpinion flowInstanceOpinion) {
		if (flowInstanceOpinion != null) {
			this.id = flowInstanceOpinion.getId();
			this.instanceLogId = flowInstanceOpinion.getInstanceLogId();
			this.opinion = flowInstanceOpinion.getOpinion();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInstanceLogId() {
		return instanceLogId;
	}

	public void setInstanceLogId(String instanceLogId) {
		this.instanceLogId = instanceLogId;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}
}
