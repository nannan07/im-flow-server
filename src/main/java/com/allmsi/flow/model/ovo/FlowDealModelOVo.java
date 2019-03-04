package com.allmsi.flow.model.ovo;

import com.allmsi.flow.model.FlowNodeDeal;

public class FlowDealModelOVo {

	private String nodelId;

	private String dealId;

	private String dealType;

	private String dealName;

	public FlowDealModelOVo() {
	}

	public FlowDealModelOVo(String nodelId, String dealId, String dealType, String dealName) {
		this.nodelId = nodelId;
		this.dealId = dealId;
		this.dealType = dealType;
		this.dealName = dealName;
	}



	public FlowDealModelOVo(FlowNodeDeal flowNodeDeal2) {
		if (flowNodeDeal2 != null) {
			this.dealId = flowNodeDeal2.getNodeDealId();
			this.dealType = flowNodeDeal2.getNodeDealType();
		}
	}

	public String getNodelId() {
		return nodelId;
	}

	public void setNodelId(String nodelId) {
		this.nodelId = nodelId;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getDealName() {
		return dealName;
	}

	public void setDealName(String dealName) {
		this.dealName = dealName;
	}
}
