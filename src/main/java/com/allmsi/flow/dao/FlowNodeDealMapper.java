package com.allmsi.flow.dao;

import java.util.List;

import com.allmsi.flow.model.FlowNodeDeal;

public interface FlowNodeDealMapper {
    List<FlowNodeDeal> selectByNodeId(String nodeId);

	FlowNodeDeal selectByPrimaryKey(String id);

    int insertSelective(FlowNodeDeal record);

	int updateByPrimaryKeySelective(FlowNodeDeal record);

	int deleteByPrimaryKey(FlowNodeDeal fnd);

	int deleteByNodeId(String id);

	List<FlowNodeDeal> receiverSelect(FlowNodeDeal flowNodeDeal);

}