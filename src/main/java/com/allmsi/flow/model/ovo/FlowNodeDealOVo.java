package com.allmsi.flow.model.ovo;

import com.allmsi.flow.model.FlowNodeDeal;
import com.allmsi.flow.model.external.FlowUserModel;

public class FlowNodeDealOVo {

	private String id;

    private String nodeId;
    
    private String nodeDealId;

	private String nodeDealType;
	
	private String nodeDealName;
	
	private String continuation;
    
    private FlowUserModel user;

    public FlowNodeDealOVo(){
    	
    }
    
    public FlowNodeDealOVo(String id, String nodeId, String nodeDealId, String nodeDealType, String nodeDealName) {
		this.id = id;
		this.nodeId = nodeId;
		this.nodeDealId = nodeDealId;
		this.nodeDealType = nodeDealType;
		this.nodeDealName = nodeDealName;
	}



	public FlowNodeDealOVo(FlowNodeDeal flowNodeDeal){
    	if(flowNodeDeal != null){
    		this.id = flowNodeDeal.getId();
    		this.nodeId = flowNodeDeal.getNodeId();
    		this.nodeDealId = flowNodeDeal.getNodeDealId();
    		this.nodeDealType = flowNodeDeal.getNodeDealType();
    		this.continuation = flowNodeDeal.getContinuation();
    	}
    }
    
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

	public String getNodeDealName() {
		return nodeDealName;
	}

	public void setNodeDealName(String nodeDealName) {
		this.nodeDealName = nodeDealName;
	}

	public FlowUserModel getUser() {
		return user;
	}

	public void setUser(FlowUserModel user) {
		this.user = user;
	}

	public String getContinuation() {
		return continuation;
	}

	public void setContinuation(String continuation) {
		this.continuation = continuation;
	}
}
