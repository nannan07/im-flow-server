package com.allmsi.flow.service;

import java.util.List;

import com.allmsi.flow.model.ivo.FlowTodoVo;
import com.allmsi.sys.model.ListBean;

public interface FlowTodoService {

	ListBean selectTodoList(String userId,String flowCode,Integer page,Integer pageSize);
	
	ListBean selectDoneList(String userId,String flowCode,Integer page,Integer pageSize);
	
	boolean insertBatch(List<FlowTodoVo> list);
}
