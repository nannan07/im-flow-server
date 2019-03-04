package com.allmsi.flow.service;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.ivo.FlowIVo;
import com.allmsi.flow.model.ovo.Flow4ListOVo;
import com.allmsi.flow.model.ovo.FlowOVo;

public interface FlowService {
	
	public String selectFlowCodeById(String id);

	int selectMyFlowListCount(String userId, String flowName);

	int selectMyFlowListCount(Map<String, List<String>> authMap, String flowName);

	List<Flow4ListOVo> selectMyFlowList(String userId, String flowName, Integer page, Integer pageSize);

	List<Flow4ListOVo> selectMyFlowList(Map<String, List<String>> authMap, String flowName, Integer page,
			Integer pageSize);

	int selectConut(String flowType);

	List<FlowOVo> selectFlowList(String flowType, Integer page, Integer pageSize);

	FlowOVo selectFlowInfo(String id);

	String addFlow(FlowIVo flowVo);

	String updateFlow(FlowIVo flowVo);

	boolean delFlow(String id, String uUserId);

}
