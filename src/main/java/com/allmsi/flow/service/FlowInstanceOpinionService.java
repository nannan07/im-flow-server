package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowInstanceOpinionIVo;
import com.allmsi.flow.model.ovo.FlowInstanceOpinionOVo;

public interface FlowInstanceOpinionService {

	int selectCountFlowInstanceOpinion(String instanceId);

	List<FlowInstanceOpinionOVo> selectFlowInstanceOpinionList(String instanceId, Integer page, Integer pageSize);

	String addFlowInstanceOpinion(FlowInstanceOpinionIVo flowInstanceOpinionIVo);

	String updateFlowInstanceOpinion(FlowInstanceOpinionIVo flowInstanceOpinionIVo);

	boolean delFlowInstanceOpinion(String id);

}
