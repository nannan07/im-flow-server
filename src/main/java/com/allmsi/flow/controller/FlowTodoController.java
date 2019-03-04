package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.service.FlowTodoService;

@Controller
@RequestMapping(value = "/flow")
public class FlowTodoController {

	@Resource
	private FlowTodoService flowTodoService;
	
	@RequestMapping(value = "/todo/select", method = RequestMethod.GET)
	@ResponseBody
	public Object todoList(String userId,String flowCode, Integer page, Integer pageSize) {
		return flowTodoService.selectTodoList(userId, flowCode, page, pageSize);
	}
	
	@RequestMapping(value = "/done/select", method = RequestMethod.GET)
	@ResponseBody
	public Object doneList(String userId,String flowCode, Integer page, Integer pageSize) {
		return flowTodoService.selectDoneList(userId, flowCode, page, pageSize);
	}
}
