package com.allmsi.flow.model.external;

import com.allmsi.flow.model.FlowInstance;
import com.allmsi.flow.model.FlowNodeDeal;

public class FlowUserModel {

	private String userId;
	
	private String name;

	private String type;
	
	public FlowUserModel() {
		
	}
	
	public FlowUserModel(String userId, String name, String type) {
		this.userId = userId;
		this.name = name;
		this.type = type;
	}

	public FlowUserModel(FlowInstance flowInstancePo) {
		this.userId = flowInstancePo.getcUserId();
	}
	
	public FlowUserModel(FlowNodeDeal flowNodeDeal2) {
		this.userId = flowNodeDeal2.getNodeDealId();
		this.type = flowNodeDeal2.getNodeDealType();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
