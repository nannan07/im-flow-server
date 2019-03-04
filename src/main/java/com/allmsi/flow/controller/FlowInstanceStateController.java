package com.allmsi.flow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowInstanceStateIVo;
import com.allmsi.flow.model.ovo.FlowInstanceStateOVo;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowInstanceState")
public class FlowInstanceStateController {

	@Resource
	private FlowInstanceStateService flowInstanceStateService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowInstanceState(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("", flowInstanceStateService.selectFlowInstanceState(instanceId));
	}

	// @RequestMapping(value = "/save", method = RequestMethod.POST)
	// @ResponseBody
	// public Object saveFlowInstanceState(FlowInstanceStateIVo
	// flowInstanceStateIVo) {
	// if (flowInstanceStateIVo == null ||
	// StrUtil.isEmpty(flowInstanceStateIVo.getInstanceId())) {
	// return new WarnProtocol();
	// }
	// String id = flowInstanceStateIVo.getId();
	// if (StrUtil.isEmpty(id)) {
	// id = flowInstanceStateService.addFlowInstanceState(flowInstanceStateIVo);
	// } else {
	// id = flowInstanceStateService.updateFlowInstanceState(flowInstanceStateIVo);
	// }
	// return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功",
	// id);
	// }

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addFlowInstanceState(List<FlowInstanceStateIVo> flowInstanceStateIVoList) {
		if (flowInstanceStateIVoList == null || flowInstanceStateIVoList.size() < 1) {
			return new WarnProtocol();
		}
		List<FlowInstanceStateIVo> list = new ArrayList<FlowInstanceStateIVo>();
		for (FlowInstanceStateIVo flowInstanceStateIVo2 : flowInstanceStateIVoList) {
			if (StrUtil.notEmpty(flowInstanceStateIVo2.getInstanceId())) {
				list.add(flowInstanceStateIVo2);
			}
		}
		if (list == null || list.size() < 1) {
			return new WarnProtocol();
		}
		int id = flowInstanceStateService.addFlowInstanceStates(list);
		return new SuccessProtocol("成功", id);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public Object updateFlowInstanceState(@RequestBody List<FlowInstanceStateIVo> flowInstanceStateIVoList) {
		if (flowInstanceStateIVoList == null || flowInstanceStateIVoList.size() < 1) {
			return null;
		}
		List<FlowInstanceStateIVo> list = new ArrayList<FlowInstanceStateIVo>();
		for (FlowInstanceStateIVo flowInstanceStateIVo2 : flowInstanceStateIVoList) {
			if (StrUtil.notEmpty(flowInstanceStateIVo2.getInstanceId())) {
				list.add(flowInstanceStateIVo2);
			}
		}
		if (list == null || list.size() < 1) {
			return null;
		}
		return flowInstanceStateService.updateFlowInstanceStates(list);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public FlowInstanceStateOVo instanceState(String objectId, String userId) {
		if (StrUtil.isEmpty(objectId)) {
			return null;
		}
		return flowInstanceStateService.selectFlowInstanceStateByObj(objectId, userId);
	}

}
