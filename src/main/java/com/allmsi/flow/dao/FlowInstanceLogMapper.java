package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowInstanceLog;

public interface FlowInstanceLogMapper {
    int insertSelective(FlowInstanceLog record);

	int selectCountInstanceLog(String instanceId);

	List<FlowInstanceLog> selectByInstanceId(Map<String, Object> map);

	int insertBantch(List<FlowInstanceLog> logList);
}