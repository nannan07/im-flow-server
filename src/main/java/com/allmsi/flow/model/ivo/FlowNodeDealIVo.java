package com.allmsi.flow.model.ivo;

public class FlowNodeDealIVo {
	
	private String id;

    private String nodeId;

    private String nodeDealId;

    private String nodeDealType;
    
    private String continuation;
    
    private String cUserId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getNodeDealId() {
		return nodeDealId;
	}

	public void setNodeDealId(String nodeDealId) {
		this.nodeDealId = nodeDealId;
	}

	public String getNodeDealType() {
		return nodeDealType;
	}

	public void setNodeDealType(String nodeDealType) {
		this.nodeDealType = nodeDealType;
	}

	public String getContinuation() {
		return continuation;
	}

	public void setContinuation(String continuation) {
		this.continuation = continuation;
	}

	public String getcUserId() {
		return cUserId;
	}

	public void setcUserId(String cUserId) {
		this.cUserId = cUserId;
	}

}
