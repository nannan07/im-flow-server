package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowInstance;

public interface FlowInstanceMapper {
	int deleteByPrimaryKey(String id);

	int insertSelective(FlowInstance record);

	FlowInstance selectByPrimaryKey(String id);

	List<FlowInstance> selectInstanceList(Map<String, Object> map);

	int countFlowInstanceList(Map<String, Object> map);

	List<FlowInstance> selectInstanceMyList(Map<String, Object> map);

	int countFlowInstanceMyList(Map<String, Object> map);

	int deleteByFlowId(String flowId);

	List<FlowInstance> getFlowInstanceInfoByObjectId(String objId);

	List<FlowInstance> selectInstanceByObjId(String objId);
}