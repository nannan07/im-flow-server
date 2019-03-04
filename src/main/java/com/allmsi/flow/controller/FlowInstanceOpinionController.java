package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowInstanceOpinionIVo;
import com.allmsi.flow.service.FlowInstanceOpinionService;
import com.allmsi.flow.model.ListBean;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowOpinion")
public class FlowInstanceOpinionController {

	@Resource
	private FlowInstanceOpinionService flowInstanceOpinionService;

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectOpinionList(String instanceId, Integer page, Integer pageSize) {
		if (StrUtil.isEmpty(instanceId)) {
			return new WarnProtocol();
		}
		int total = flowInstanceOpinionService.selectCountFlowInstanceOpinion(instanceId);
		return new SuccessProtocol(new ListBean(total,
				flowInstanceOpinionService.selectFlowInstanceOpinionList(instanceId, page, pageSize)));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Object saveOpinion(FlowInstanceOpinionIVo flowInstanceOpinionIVo) {
		if (flowInstanceOpinionIVo == null) {
			return new WarnProtocol();
		}
		String id = flowInstanceOpinionIVo.getId();
		if (StrUtil.isEmpty(id)) {
			id = flowInstanceOpinionService.addFlowInstanceOpinion(flowInstanceOpinionIVo);
		} else {
			id = flowInstanceOpinionService.updateFlowInstanceOpinion(flowInstanceOpinionIVo);
		}
		return (StrUtil.isEmpty(id)) ? new WarnProtocol() : new SuccessProtocol("成功", id);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delOpinion(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowInstanceOpinionService.delFlowInstanceOpinion(id));
	}
}
