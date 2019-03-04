package com.allmsi.flow.model.ovo;

import java.util.Date;

import com.allmsi.flow.model.FlowNode;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowNodeOVo {
	private String id;

	private String flowId;

	private String nodeName;

	private String nodeType;

	private Date cTime;

	private FlowUserModel user;

	public FlowNodeOVo() {

	}

	public FlowNodeOVo(FlowNode flowNode) {
		if (flowNode != null) {
			this.id = flowNode.getId();
			this.flowId = flowNode.getFlowId();
			this.nodeName = flowNode.getNodeName();
			this.nodeType = flowNode.getNodeType();
			this.cTime = flowNode.getcTime();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId == null ? null : flowId.trim();
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName == null ? null : nodeName.trim();
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType == null ? null : nodeType.trim();
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