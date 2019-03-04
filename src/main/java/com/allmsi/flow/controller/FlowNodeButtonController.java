package com.allmsi.flow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowNodeButtonIVo;
import com.allmsi.flow.model.ovo.FlowNodeButtonOVo;
import com.allmsi.flow.service.FlowNodeButtonService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowNodeButton")
public class FlowNodeButtonController {

	@Resource
	private FlowNodeButtonService flowNodeButtonService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectNodeButtonList(String nodeId) {
		if (StrUtil.isEmpty(nodeId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowNodeButtonService.selectNodeButtonByNodeId(nodeId));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectNodeButtonInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("", flowNodeButtonService.selectByPrimaryKey(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveNodeButton(FlowNodeButtonIVo flowNodeButtonIVo) {
		if (flowNodeButtonIVo == null) {
			return new WarnProtocol();
		}
		String id = flowNodeButtonIVo.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowNodeButtonService.addFlowNodeButton(flowNodeButtonIVo);
		} else {
			id = flowNodeButtonService.updateFlowNodeButton(flowNodeButtonIVo);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功", id);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delNodeButton(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowNodeButtonService.delFlowNodeButton(id, uUserId));
	}

	@RequestMapping(value = "/instanceId", method = RequestMethod.GET)
	@ResponseBody
	public List<FlowNodeButtonOVo> getNodeButtonByInstanceId(String instanceId) {
		List<FlowNodeButtonOVo> list = new ArrayList<FlowNodeButtonOVo>();
		if (StrUtil.isEmpty(instanceId)) {
			return list;
		}
		list.addAll(flowNodeButtonService.getNodeButtonByInstanceId(instanceId));
		return list;
	}

	@RequestMapping(value = "/flowId", method = RequestMethod.GET)
	@ResponseBody
	public List<FlowNodeButtonOVo> getNodeButtonByFlowId(String flowId) {
		if (StrUtil.isEmpty(flowId)) {
			return null;
		}
		return flowNodeButtonService.getNodeButtonByFlowId(flowId);
	}

}
