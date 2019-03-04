package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.service.FlowInstenceCurrencyService;

@Controller
@RequestMapping(value = "/flowInstenceCurrency")
public class FlowInstenceCurrencyController {
	
	@Resource
	private FlowInstenceCurrencyService flowInstenceCurrencyService;
	
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public Object flowInstenceStateInfo(String objId,String userId) {
		return flowInstenceCurrencyService.flowInstenceStateInfo(objId,userId);
	}

}
