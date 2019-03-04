package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowInstanceOpinion;

public interface FlowInstanceOpinionMapper {
    int selectCountFlowInstanceOpinion(String instanceId);

	List<FlowInstanceOpinion> selectFlowInstanceOpinionList(Map<String, Object> map);

	int insertSelective(FlowInstanceOpinion record);

    FlowInstanceOpinion selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(FlowInstanceOpinion record);

	int deleteByPrimaryKey(String id);
}