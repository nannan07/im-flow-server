package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowIVo;
import com.allmsi.flow.service.FlowService;
import com.allmsi.flow.model.ListBean;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flow")
public class FlowController {

	@Resource
	private FlowService flowService;
	
	@RequestMapping(value = "/my/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectMyFlowList(String flowName, String userId, Integer page,Integer pageSize) {
		if(StrUtil.isEmpty(userId)){
			return new WarnProtocol();
		}
		int total = flowService.selectMyFlowListCount(userId, flowName);
		return new SuccessProtocol(new ListBean(total,flowService.selectMyFlowList(userId, flowName, page, pageSize)));	
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowList(String flowType, Integer page, Integer pageSize) {
		int total = flowService.selectConut(flowType);
		return new SuccessProtocol(new ListBean(total, flowService.selectFlowList(flowType, page, pageSize)));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlow(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("",flowService.selectFlowInfo(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveFlow(FlowIVo flowVo) {
		if (flowVo == null) {
			return new WarnProtocol();
		}
		String id = flowVo.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowService.addFlow(flowVo);
		} else {
			id = flowService.updateFlow(flowVo);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功", id);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delFlow(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowService.delFlow(id, uUserId));
	}

}
