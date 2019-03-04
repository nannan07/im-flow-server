package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowAuthIVo;
import com.allmsi.flow.service.FlowAuthService;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowAuth")
public class FlowAuthController {

	@Resource
	private FlowAuthService flowAuthService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowAuthList(String flowId) {
		if (StrUtil.isEmpty(flowId)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowAuthService.selectFlowAuthList(flowId));
	}

	@RequestMapping(value = "/info/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectFlowAuthInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol("", flowAuthService.selectByPrimaryKey(id));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveFlowAuth(FlowAuthIVo flowAuthIVo) {
		if (flowAuthIVo == null) {
			return new WarnProtocol();
		}
		String id = flowAuthIVo.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowAuthService.addFlowAuth(flowAuthIVo);
		} else {
			id = flowAuthService.updateFlowAuth(flowAuthIVo);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功", id);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delFlowAuth(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowAuthService.delFlowAuth(id, uUserId));
	}

}
