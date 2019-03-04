package com.allmsi.flow.model.external;

public class FlowDealIdATypeModel {

	private String dealId;

	private String dealType;

	public FlowDealIdATypeModel() {

	}

	public FlowDealIdATypeModel(String dealId, String dealType) {
		this.dealId = dealId;
		this.dealType = dealType;
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
}
