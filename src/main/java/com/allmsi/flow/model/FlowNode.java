package com.allmsi.flow.model;

import java.util.Date;

import com.allmsi.flow.model.ivo.FlowNodeIVo;

public class FlowNode {
    private String id;

    private String flowId;

    private String nodeName;

    private String nodeType;

    private Date cTime;

    private String cUserId;

    private Date uTime;

    private String uUserId;

    private Boolean del;

    public FlowNode() {
	}
    
    public FlowNode(FlowNodeIVo flowNodeIVo) {
    	if(flowNodeIVo != null){
    		this.id = flowNodeIVo.getId();
    		this.flowId = flowNodeIVo.getFlowId();
    		this.nodeName = flowNodeIVo.getNodeName();
    		this.nodeType = flowNodeIVo.getNodeType();
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

    public String getcUserId() {
        return cUserId;
    }

    public void setcUserId(String cUserId) {
        this.cUserId = cUserId == null ? null : cUserId.trim();
    }

    public Date getuTime() {
        return uTime;
    }

    public void setuTime(Date uTime) {
        this.uTime = uTime;
    }

    public String getuUserId() {
        return uUserId;
    }

    public void setuUserId(String uUserId) {
        this.uUserId = uUserId == null ? null : uUserId.trim();
    }

    public Boolean getDel() {
        return del;
    }

    public void setDel(Boolean del) {
        this.del = del;
    }
}