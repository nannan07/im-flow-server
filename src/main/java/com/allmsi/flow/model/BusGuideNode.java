package com.allmsi.flow.model;

import java.util.List;

import com.allmsi.flow.model.external.FlowUserModel;

public class BusGuideNode {

	private String type;

	private String msg;

	private String nodeId;

	private List<FlowUserModel> flowUserList;

	public BusGuideNode() {
	}
	
	public BusGuideNode(String type, String msg, List<FlowUserModel> flowUserList) {
		this.type = type;
		this.msg = msg;
		this.flowUserList = flowUserList;
	}
	
	public BusGuideNode(String type, String msg, String nodeId) {
		this.type = type;
		this.msg = msg;
		this.nodeId = nodeId;
	}

	public BusGuideNode(String type, String msg, String nodeId, List<FlowUserModel> flowUserList) {
		this.type = type;
		this.msg = msg;
		this.nodeId = nodeId;
		this.flowUserList = flowUserList;
	}

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

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

}
