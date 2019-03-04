package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowNodeDealIVo;
import com.allmsi.flow.service.FlowNodeDealService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowNodeDeal")
public class FlowNodeDealController {

	@Resource
	private FlowNodeDealService flowNodeDealService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowNodeDealList(String nodeId) {
		if (StrUtil.isEmpty(nodeId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowNodeDealService.selectFlowNodeDealList(nodeId));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowNodeDealInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("", flowNodeDealService.selectFlowNodeDealInfo(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveFlowNodeDeal(FlowNodeDealIVo flowNodeDealIVO) {
		if (flowNodeDealIVO == null) {
			return new WarnProtocol();
		}
		String id = flowNodeDealIVO.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowNodeDealService.addFlowNodeDeal(flowNodeDealIVO);
		} else {
			id = flowNodeDealService.updateFlowNodeDeal(flowNodeDealIVO);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功", id);
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delFlowNodeDeal(String id,String uUserId) {
		if (StrUtil.isEmpty(id)) {
		return	new WarnProtocol();
		}
		return new SuccessProtocol(flowNodeDealService.delFlowNodeDeal(id, uUserId));
	}

}
