package com.allmsi.flow.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowInstance4ListIVo;
import com.allmsi.flow.model.ivo.FlowInstanceIVo;
import com.allmsi.flow.service.FlowInstanceService;
import com.allmsi.flow.model.ListBean;
import com.allmsi.sys.model.protocol.SuccessProtocol;
import com.allmsi.sys.model.protocol.WarnProtocol;
import com.allmsi.sys.util.StrUtil;

@Controller
@RequestMapping("/flowInstance")
public class FlowInstanceController {

	@Resource
	private FlowInstanceService flowInstanceService;
	
	@RequestMapping(value = "/selectInstanceByObjId", method = RequestMethod.GET)
	@ResponseBody
	public Object selectInstanceByObjId(String objId) {
		return flowInstanceService.selectInstanceByObjId(objId);
	}

	/**
	 * 我发起的流程
	 * 
	 * @param flowInstance
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectInstanceList(FlowInstance4ListIVo flowInstance, Integer page, Integer pageSize) {
		if (flowInstance == null || StrUtil.isEmpty(flowInstance.getUserId())) {
			return new WarnProtocol();
		}
		int total = flowInstanceService.selectCountInstanceList(flowInstance);
		return new SuccessProtocol(
				new ListBean(total, flowInstanceService.selectInstanceList(flowInstance, page, pageSize)));
	}

	/**
	 * 我待办的
	 * 
	 * @param flowInstance
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/my/select", method = RequestMethod.GET)
	@ResponseBody
	public Object selectInstanceMyList(FlowInstance4ListIVo flowInstance, Integer page, Integer pageSize) {
		if (flowInstance == null || StrUtil.isEmpty(flowInstance.getUserId())) {
			return new WarnProtocol();
		}
		int total = flowInstanceService.selectCountInstanceMyList(flowInstance);
		return new SuccessProtocol(
				new ListBean(total, flowInstanceService.selectInstanceMyList(flowInstance, page, pageSize)));
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public Object addFlowInstance(@RequestBody FlowInstanceIVo flowInstanceIVo) {
		if (flowInstanceIVo == null || StrUtil.isEmpty(flowInstanceIVo.getFlowId())) {
			return null;
		}
		return flowInstanceService.addFlowInstance(flowInstanceIVo);
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	@ResponseBody
	public Object delFlowInstance(String id) {
		if (StrUtil.isEmpty(id)) {
			return new WarnProtocol();
		}
		return new SuccessProtocol(flowInstanceService.delFlowInstance(id));
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public Object getFlowInstanceInfoByObjectId(String objId) {
		if (StrUtil.isEmpty(objId)) {
			return null;
		}
		return flowInstanceService.getFlowInstanceInfoByObjectId(objId);
	}
	
	@RequestMapping(value = "/selectById", method = RequestMethod.GET)
	@ResponseBody
	public Object getFlowInstanceInfoById(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return null;
		}
		return flowInstanceService.selectById(instanceId);
	}

}
