package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowNodeIVo;
import com.allmsi.flow.service.FlowNodeService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowNode")
public class FlowNodeController {

	@Resource
	private FlowNodeService flowNodeService;

	@RequestMapping(value = "/startNode", method = RequestMethod.GET)
	@ResponseBody
	public Object selectNodeByStartNode(String flowId) {
		return flowNodeService.selectTheNextOneToStart(flowId);
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowNodeByFlowId(String flowId) {
		if (StrUtil.isEmpty(flowId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowNodeService.selectFlowNodeByFlowId(flowId));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectByPrimaryKey(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("", flowNodeService.selectByPrimaryKey(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveFlowNode(FlowNodeIVo flowNodeIVo) {
		if (flowNodeIVo == null) {
			return new WarnProtocol();
		}
		String id = flowNodeIVo.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowNodeService.addFlowNode(flowNodeIVo);
		} else {
			id = flowNodeService.updateFlowNode(flowNodeIVo);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功", id);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delFlowNode(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowNodeService.delFlowNode(id, uUserId));
	}
}
