package com.allmsi.flow.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowRouteIVo;
import com.allmsi.flow.model.ovo.FlowRouteOVo;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowRoute")
public class FlowRouteController {

	@Resource
	private FlowRouteService flowRouteService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectRouteList(String flowId) {
		if (StrUtil.isEmpty(flowId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowRouteService.selectRouteList(flowId));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectRouteInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("", flowRouteService.selectRouteInfo(id));
	}

	@RequestMapping(value = "/next/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectsRouteList(String flowCode, String preNode) {
		if (StrUtil.isEmpty(preNode) || StrUtil.isEmpty(flowCode)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowRouteService.selectRouteListByPreNode(flowCode, preNode));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveRouteList(FlowRouteIVo flowRouteIVo) {
		if (flowRouteIVo == null) {
			return new WarnProtocol();
		}
		String id = flowRouteIVo.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowRouteService.addFlowRoute(flowRouteIVo);
		} else {
			id = flowRouteService.updateFlowRoute(flowRouteIVo);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("", id);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delRouteList(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowRouteService.delFlowRoute(id, uUserId));
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public Object getRouteInfo(String routeId) {
		if (StrUtil.isEmpty(routeId)) {
			return null;
		}
		return flowRouteService.selectRouteInfo(routeId);
	}

	@RequestMapping(value = "/node", method = RequestMethod.GET)
	@ResponseBody
	public List<FlowRouteOVo> getRouteListBynodeId(String flowId, String nodeId) {
		List<FlowRouteOVo> list = new ArrayList<FlowRouteOVo>();
		if (StrUtil.isEmpty(flowId) || StrUtil.isEmpty(nodeId)) {
			return list;
		}
		list.addAll(flowRouteService.selectRouteListByPreNode(flowId, nodeId));
		return list;
	}

}
