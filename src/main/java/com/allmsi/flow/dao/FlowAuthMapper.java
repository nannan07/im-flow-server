package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowAuth;

public interface FlowAuthMapper {
	
    List<FlowAuth> selectByFlowId(String flowId);

	FlowAuth selectByPrimaryKey(String id);

	int insertSelective(FlowAuth record);

    int updateByPrimaryKeySelective(FlowAuth record);

	int deleteByPrimaryKey(FlowAuth fa);

	int deleteByFlowId(String id);

}