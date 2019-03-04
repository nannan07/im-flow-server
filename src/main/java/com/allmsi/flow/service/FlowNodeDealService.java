package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowNodeDealIVo;
import com.allmsi.flow.model.ovo.FlowNodeDealOVo;

public interface FlowNodeDealService {

	List<FlowNodeDealOVo> selectFlowNodeDealList(String nodeId);

	FlowNodeDealOVo selectFlowNodeDealInfo(String id);
	
	String addFlowNodeDeal(FlowNodeDealIVo flowNodeDealIVO);

	String updateFlowNodeDeal(FlowNodeDealIVo flowNodeDealIVO);

	boolean delFlowNodeDeal(String id, String uUserId);

	List<FlowNodeDealOVo> selectFlowNodeDealListByNodeId(String nodeId);

}
