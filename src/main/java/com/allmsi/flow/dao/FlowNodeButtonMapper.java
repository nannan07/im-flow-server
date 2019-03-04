package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowNodeButton;

public interface FlowNodeButtonMapper {

    List<FlowNodeButton> selectByNodeId(String nodeId);

	FlowNodeButton selectByPrimaryKey(String id);

	int insertSelective(FlowNodeButton record);

    int updateByPrimaryKeySelective(FlowNodeButton record);

	int deleteByPrimaryKey(FlowNodeButton fb);

	int deleteByNodeId(String nodeId);

	List<FlowNodeButton> getNodeButtonByInstanceId(String instanceId);
}