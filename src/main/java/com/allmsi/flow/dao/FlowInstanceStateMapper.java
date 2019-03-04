package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowInstanceState;

public interface FlowInstanceStateMapper {

    int insertSelective(FlowInstanceState record);

    List<FlowInstanceState> selectByInstanceId(String instanceId);

    int updateByPrimaryKeySelective(FlowInstanceState record);

	int insertBantch(List<FlowInstanceState> fiList);

	int deleteBantch(String instanceId);

	List<FlowInstanceState> selectFlowInstanceStateByObj(String objectId);

	FlowInstanceState flowInstenceStateInfo(Map<String, String> map);

}