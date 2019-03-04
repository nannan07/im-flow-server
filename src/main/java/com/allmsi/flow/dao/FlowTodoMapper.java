package com.allmsi.flow.dao;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.FlowTodo;

public interface FlowTodoMapper {

    int insertBatch(List<FlowTodo> list);

    FlowTodo selectByPrimaryKey(String id);
    
    List<FlowTodo> selectTodoList(Map<String,Object> map);

	List<FlowTodo> selectDoneList(Map<String, Object> map);
}