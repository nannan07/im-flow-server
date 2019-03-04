package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowNodeButtonIVo;
import com.allmsi.flow.model.ovo.FlowNodeButtonOVo;
import com.allmsi.flow.model.ovo.FlowNodeButtonSimpleOVO;

public interface FlowNodeButtonService {
	
	List<FlowNodeButtonOVo> selectNodeButtonByNodeId(String nodeId);

	FlowNodeButtonOVo selectByPrimaryKey(String id);

	String addFlowNodeButton(FlowNodeButtonIVo flowNodeButtonIVo);

	String updateFlowNodeButton(FlowNodeButtonIVo flowNodeButtonIVo);

	boolean delFlowNodeButton(String id, String uUserId);
	
	List<FlowNodeButtonOVo> getNodeButtonByInstanceId(String instanceId);
	
	List<FlowNodeButtonOVo> getNodeButtonByFlowId(String flowId);

	List<FlowNodeButtonSimpleOVO> selectNodeButtonForNodeId(String nodeId);
	
}
