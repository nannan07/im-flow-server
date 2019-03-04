package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowInstanceStateIVo;
import com.allmsi.flow.model.ovo.FlowInstanceStateOVo;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;

public interface FlowInstanceStateService {

	List<FlowInstanceStateOVo> selectFlowInstanceState(String instanceId);

	String addFlowInstanceState(FlowInstanceStateIVo flowInstanceStateIVo);

	String updateFlowInstanceState(FlowInstanceStateIVo flowInstanceStateIVo);

	int addFlowInstanceStates(List<FlowInstanceStateIVo> list);

	List<String> updateFlowInstanceStates(List<FlowInstanceStateIVo> list);

	FlowInstanceStateOVo selectFlowInstanceStateByObj(String objectId, String userId);

	List<FlowInstanceStateOVo> selectFlowInstanceStateByObjectId(String objId);

	FlowInstenceCurrencyOVO flowInstenceStateInfo(String objId, String userId);
}
