package com.allmsi.flow.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allmsi.flow.model.ivo.FlowInstanceState4Deal;
import com.allmsi.flow.model.ivo.FlowTodoVo;
import com.allmsi.flow.service.OperationBusService;

@Controller
@RequestMapping("/bus")
public class OperationBusController {

	@Resource
	private OperationBusService operationBusService;

	/**
	 * 提交流程示例状态
	 * 
	 * @param flowInstanceStateIVoList
	 * @return
	 */
	@RequestMapping(value = "/submit", method = RequestMethod.POST)
	@ResponseBody
	public Object submit(String flowId, String objId, String instanceId, String nodeId, String preDealId, String remark,
			String routeId,String objName, String objValue1, String objValue2, String objValue3,
			String objValue4, String objValue5, @RequestBody List<FlowInstanceState4Deal> flowRouteDealList) {
		
		System.out.println("objName---"+objName);
		FlowTodoVo flowTodoVo = new FlowTodoVo(objId, objName, objValue1, objValue2, objValue3, objValue4, objValue5);
	
		return operationBusService.submit(flowId, objId, instanceId, nodeId, preDealId, remark, routeId,
				flowRouteDealList, flowTodoVo);
	}

	/**
	 * 获取节点后续路由
	 * 
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	@RequestMapping(value = "/guide", method = RequestMethod.GET)
	@ResponseBody
	public Object guide(String flowCode, String instanceId, String instanceStateId, String nodeId, String routeId,
			String query, String userId) {
		return operationBusService.guide(flowCode, instanceId, instanceStateId, nodeId, routeId, query, userId);
	}

}
