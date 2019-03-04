package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowRouteDealIVo;
import com.allmsi.flow.service.FlowRouteDealService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowRouteDeal")
public class FlowRouteDealController {

	@Resource
	private FlowRouteDealService flowRouteDealService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowRouteDealList(String routeId) {
		if (StrUtil.isEmpty(routeId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowRouteDealService.selectFlowRouteDealList(routeId));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectByPrimaryKey(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("", flowRouteDealService.selectFlowRouteDealInfo(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveFlowRouteDeal(FlowRouteDealIVo flowRouteDealIVo) {
		if (flowRouteDealIVo == null) {
			return new WarnProtocol();
		}
		String id = flowRouteDealIVo.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowRouteDealService.addFlowRouteDeal(flowRouteDealIVo);
		} else {
			id = flowRouteDealService.updateFlowRouteDeal(flowRouteDealIVo);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功", id);
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delFlowRouteDeal(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowRouteDealService.delFlowRouteDeal(id, uUserId));
	}
}
