package com.allmsi.flow.model;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ovo.FlowRouteOVo;

public class BusGuideRoute {

	private String type;

	private String msg;
	
	private boolean flag;

	private List<FlowRouteOVo> flowRouteList;
	
	private List<FlowUserModel> routeUserList;

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


	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<FlowRouteOVo> getFlowRouteList() {
		return flowRouteList;
	}

	public void setFlowRouteList(List<FlowRouteOVo> flowRouteList) {
		this.flowRouteList = flowRouteList;
	}

	public List<FlowUserModel> getRouteUserList() {
		return routeUserList;
	}

	public void setRouteUserList(List<FlowUserModel> routeUserList) {
		this.routeUserList = routeUserList;
	}

}
