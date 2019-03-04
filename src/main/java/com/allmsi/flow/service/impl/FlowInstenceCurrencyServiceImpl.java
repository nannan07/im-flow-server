package com.allmsi.flow.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.model.ovo.FlowNodeButtonSimpleOVO;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.flow.service.FlowInstenceCurrencyService;
import com.allmsi.flow.service.FlowNodeButtonService;

@Service
public class FlowInstenceCurrencyServiceImpl implements FlowInstenceCurrencyService {
	
	@Resource
	private FlowInstanceStateService flowInstanceStateService;
	
	@Resource
	private FlowNodeButtonService flowNodeButtonService;
	

	@Override
	public FlowInstenceCurrencyOVO flowInstenceStateInfo(String objId,String userId) {
		FlowInstenceCurrencyOVO flowInstenceCurrencyOVO =  flowInstanceStateService.flowInstenceStateInfo(objId, userId);
		if(flowInstenceCurrencyOVO != null ) {
			String nodeId = flowInstenceCurrencyOVO.getNodeId();
			List<FlowNodeButtonSimpleOVO> list = flowNodeButtonService.selectNodeButtonForNodeId(nodeId);
			flowInstenceCurrencyOVO.setList(list);
		}
		return flowInstenceCurrencyOVO;
	}
	
	

}
