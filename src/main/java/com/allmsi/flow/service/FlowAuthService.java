package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowAuthIVo;
import com.allmsi.flow.model.ovo.FlowAuthOVo;

public interface FlowAuthService {

	List<FlowAuthOVo> selectFlowAuthList(String flowId);

	FlowAuthOVo selectByPrimaryKey(String id);

	String addFlowAuth(FlowAuthIVo flowAuthIVo);

	String updateFlowAuth(FlowAuthIVo flowAuthIVo);

	boolean delFlowAuth(String id, String uUserId);

}
