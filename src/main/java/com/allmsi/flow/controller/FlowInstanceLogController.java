package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.service.FlowInstanceLogService;
import com.allmsi.flow.model.ListBean;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowInstanceLog")
public class FlowInstanceLogController {

	@Resource
	private FlowInstanceLogService flowInstanceLogService;
	
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectInstanceList(String instanceId, Integer page, Integer pageSize) {
		if (StrUtil.isEmpty(instanceId)) {
			return new WarnProtocol();
		}
		int total = flowInstanceLogService.selectCountInstanceLog(instanceId);
		return new SuccessProtocol(new ListBean(total,flowInstanceLogService.selectFlowInstanceLogList(instanceId, page, pageSize)));
	}

}
