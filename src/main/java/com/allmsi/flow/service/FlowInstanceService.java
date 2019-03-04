package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowInstanceIVo;
import com.allmsi.flow.model.ivo.FlowInstance4ListIVo;
import com.allmsi.flow.model.ovo.FlowInstanceOVo;

public interface FlowInstanceService {

	int selectCountInstanceList(FlowInstance4ListIVo flowInstanceIVo);

	List<FlowInstanceOVo> selectInstanceList(FlowInstance4ListIVo flowInstanceIVo, Integer page, Integer pageSize);

	int selectCountInstanceMyList(FlowInstance4ListIVo flowInstanceIVo);

	List<FlowInstanceOVo> selectInstanceMyList(FlowInstance4ListIVo flowInstanceIVo, Integer page, Integer pageSize);

	String addFlowInstance(FlowInstanceIVo flowInstanceIVo);

	boolean delFlowInstance(String id);

	List<FlowInstanceOVo>  getFlowInstanceInfoByObjectId(String objId);

	List<String> selectInstanceByObjId(String objId);

	String insertFlowInstance(FlowInstanceIVo flowInstanceIVo);

	FlowInstanceOVo selectById(String instanceId);

}
