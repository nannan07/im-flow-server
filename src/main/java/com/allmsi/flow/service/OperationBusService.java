package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.BusGuide;
import com.allmsi.flow.model.ivo.FlowInstanceState4Deal;
import com.allmsi.flow.model.ivo.FlowTodoVo;

public interface OperationBusService {

	BusGuide guide(String flowCode, String instanceId, String instanceStateId, String nodeId, String routeId,
			String query, String userId);

	String submit(String flowId, String objId, String instanceId, String nodeId, String preDealId, String remark,
			String routeId, List<FlowInstanceState4Deal> flowRouteDealList,FlowTodoVo flowTodoVo);

}
