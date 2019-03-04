package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowNode;

public interface FlowNodeMapper {
 
	List<FlowNode> selectFlowNodeByFlowId(String flowId);
	
    FlowNode selectByPrimaryKey(String id);

	int insertSelective(FlowNode record);

    int updateByPrimaryKeySelective(FlowNode record);

	int deleteByPrimaryKey(FlowNode flowNode);

	int deleteByFlowId(String flowId);

	FlowNode selectTheNextOneToStart(String flowId);
}