package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.model.ivo.FlowInstanceIVo;
import com.allmsi.flow.model.ivo.FlowInstanceLogIVo;
import com.allmsi.flow.model.ivo.FlowInstanceState4Deal;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVo;
import com.allmsi.flow.model.ivo.FlowTodoVo;
import com.allmsi.flow.model.ovo.FlowRouteOVo;
import com.allmsi.flow.service.FlowInstanceEngine;
import com.allmsi.flow.service.FlowInstanceLogService;
import com.allmsi.flow.service.FlowInstanceService;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.flow.service.FlowTodoService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceEngineImpl implements FlowInstanceEngine {

	@Resource
	private FlowInstanceStateService flowInstanceStateService;

	@Resource
	private FlowInstanceService flowInstanceService;

	@Resource
	private FlowInstanceLogService flowInstanceLogService;

	@Resource
	private FlowRouteService flowRouteService;

	@Resource
	private FlowTodoService flowTodoService;

	@Override
	public String submit(String flowId, String objId, String instanceId, String nodeId, String preDealId, String remark,
			String routeId, List<FlowInstanceState4Deal> flowRouteDealList, FlowTodoVo flowTodoVo) {
		if (StrUtil.isEmpty(flowId) || StrUtil.isEmpty(objId) || StrUtil.isEmpty(nodeId) || StrUtil.isEmpty(preDealId)
				|| StrUtil.isEmpty(routeId) || flowRouteDealList == null || flowRouteDealList.size() < 1
				|| flowTodoVo == null) {
			System.err.println("submit 参数不合法");
			return "";
		}
		flowTodoVo.setObjId(objId);
		if (StrUtil.isEmpty(instanceId)) {
			// 先添加流程
			instanceId = flowInstanceService.insertFlowInstance(new FlowInstanceIVo(flowId, objId, preDealId));
			// 添加流程日志
			List<FlowRouteOVo> frList = flowRouteService.selectRouteListBySufNode(flowId, nodeId);
			String preNodeId = "";
			String preRouteId = "";
			if (frList != null && frList.size() > 0) {
				FlowRouteOVo flowRouteOVo = frList.get(0);
				preNodeId = flowRouteOVo.getPreNode();
				preRouteId = flowRouteOVo.getId();
			}
			flowInstanceLogService.addFlowInstanceLog(new FlowInstanceLogIVo(instanceId, preNodeId, preRouteId, "",
					preDealId, "01", "", remark, preDealId));
		}
		List<FlowInstanceStateIVo> list = new ArrayList<FlowInstanceStateIVo>();
		for (FlowInstanceState4Deal flowInstanceState4Deal : flowRouteDealList) {
			String sufDealId = flowInstanceState4Deal.getSufDealId();
			String sufDealType = flowInstanceState4Deal.getSufDealType();
			String sufAuthType = flowInstanceState4Deal.getSufAuthType();
			list.add(new FlowInstanceStateIVo(UUIDUtil.getUUID(), instanceId, nodeId, routeId, preDealId, sufDealId,
					sufDealType, sufAuthType, preDealId, remark));
		}
		List<String> stateIdList = new ArrayList<String>();
		if (list != null && list.size() > 0) {
			List<String> str = flowInstanceStateService.updateFlowInstanceStates(list);
			if (str != null && str.size() > 0) {
				stateIdList.addAll(str);
			}
		}
		boolean flag = false;
		if (stateIdList != null && stateIdList.size() > 0) {
			List<FlowTodoVo> ftdList = new ArrayList<FlowTodoVo>();
			for (String string : stateIdList) {
				FlowTodoVo flowTodoVo1 = new FlowTodoVo(objId, flowTodoVo.getObjName(), flowTodoVo.getObjValue1(),
						flowTodoVo.getObjValue2(), flowTodoVo.getObjValue3(), flowTodoVo.getObjValue4(), flowTodoVo.getObjValue5());
				flowTodoVo1.setId(UUIDUtil.getUUID());
				flowTodoVo1.setStateId(string);
				System.out.println("getStateId---" + flowTodoVo1.getStateId());
				System.out.println("ObjName---" + flowTodoVo1.getObjName());
				ftdList.add(flowTodoVo1);
			}
			flag = flowTodoService.insertBatch(ftdList);
		}
		return (flag) ? instanceId : "";
	}

}
