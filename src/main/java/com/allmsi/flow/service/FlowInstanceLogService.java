package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowInstanceLogIVo;
import com.allmsi.flow.model.ovo.FlowInstanceLogOVo;

public interface FlowInstanceLogService {
	
	List<FlowInstanceLogOVo> selectFlowInstanceLogList(String instanceId,Integer page, Integer pageSize);

	String addFlowInstanceLog(FlowInstanceLogIVo flowInstanceLogIVo);

	int selectCountInstanceLog(String instanceId);
	
}
