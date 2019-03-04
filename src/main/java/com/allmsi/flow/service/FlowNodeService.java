package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowNodeIVo;
import com.allmsi.flow.model.ovo.FlowNodeOVo;

public interface FlowNodeService {

	FlowNodeOVo selectTheNextOneToStart(String flowCode);

	List<FlowNodeOVo> selectFlowNodeByFlowId(String flowId);

	FlowNodeOVo selectByPrimaryKey(String id);

	String addFlowNode(FlowNodeIVo FlowNodeIVo);

	String updateFlowNode(FlowNodeIVo FlowNodeIVo);

	boolean delFlowNode(String id, String uUserId);

}
