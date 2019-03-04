package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.Flow;

public interface FlowMapper {

	int selectMyFlowListCount(Map<String, Object> map);

	List<Flow> selectMyFlowList(Map<String, Object> map);

	List<String> selectByPId(String pId);

	Flow selectByPrimaryKey(String id);

	int selectConut(String flowType);

	List<Flow> selectFlowInfoList(Map<String, Object> map);

	int insertSelective(Flow record);

	int updateByPrimaryKeySelective(Flow record);

	int deleteByPrimaryKey(Flow flow);

	int deleteByPId(String pId);
}