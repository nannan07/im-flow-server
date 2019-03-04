package com.allmsi.flow.model;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowRouteOVo;

public class BusGuide {

	private String type;

	private String msg;

	private List<FlowUserModel> flowUserList;

	private List<FlowRouteOVo> flowRouteList;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<FlowUserModel> getFlowUserList() {
		return flowUserList;
	}

	public void setFlowUserList(List<FlowUserModel> flowUserList) {
		this.flowUserList = flowUserList;
	}

	public List<FlowRouteOVo> getFlowRouteList() {
		return flowRouteList;
	}

	public void setFlowRouteList(List<FlowRouteOVo> flowRouteList) {
		this.flowRouteList = flowRouteList;
	}

}
