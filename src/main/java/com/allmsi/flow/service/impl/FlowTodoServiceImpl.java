package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowTodoMapper;
import com.allmsi.flow.model.FlowTodo;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowTodoVo;
import com.allmsi.flow.model.ovo.FlowTodoOVo;
import com.allmsi.flow.service.FlowTodoService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.model.ListBean;
import com.allmsi.sys.util.UUIDUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class FlowTodoServiceImpl implements FlowTodoService {

	@Resource
	private FlowTodoMapper flowTodoDao;
	
	@Resource
	private FlowUserService flowUserService;

	@Override
	public ListBean selectTodoList(String userId, String flowCode, Integer page, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FlowTodoOVo> flowVoList = new ArrayList<>();
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		map.put("userId", userId);
		map.put("flowCode", flowCode);
		PageHelper.startPage(page, pageSize);
		List<FlowTodo> flowList = flowTodoDao.selectTodoList(map);
		PageInfo<FlowTodo> pageInfo = new PageInfo<FlowTodo>(flowList);
		int total = (int) pageInfo.getTotal();
		for (FlowTodo flowTodo : flowList) {
			FlowTodoOVo ftdVo = new FlowTodoOVo(flowTodo);
			String preUserId = flowTodo.getPreDealId();
			FlowUserModel senderUserModel = flowUserService.selectUserInfo(preUserId);
			if(senderUserModel != null ) {
				ftdVo.setSenderId(preUserId);
				ftdVo.setSenderName(senderUserModel.getName());
			}
			flowVoList.add(ftdVo);
		}
		return new ListBean(total, flowVoList);
	}

	@Override
	public ListBean selectDoneList(String userId, String flowCode, Integer page, Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FlowTodoOVo> flowVoList = new ArrayList<>();
		if (page == null) {
			page = 1;
		}
		if (pageSize == null) {
			pageSize = 10;
		}
		map.put("userId", userId);
		map.put("flowCode", flowCode);
		PageHelper.startPage(page, pageSize);
		List<FlowTodo> flowList = flowTodoDao.selectDoneList(map);
		PageInfo<FlowTodo> pageInfo = new PageInfo<FlowTodo>(flowList);
		int total = (int) pageInfo.getTotal();
		for (FlowTodo flowTodo : flowList) {
			flowVoList.add(new FlowTodoOVo(flowTodo));
		}
		return new ListBean(total, flowVoList);
	}

	@Override
	public boolean insertBatch(List<FlowTodoVo> ftdList) {
		for (FlowTodoVo flowTodoVo : ftdList) {
			System.out.println("1----" + flowTodoVo.getStateId());
		}

		List<FlowTodo> list = new ArrayList<FlowTodo>();
		for (FlowTodoVo flowTodoVo : ftdList) {
			flowTodoVo.setId(UUIDUtil.getUUID());
			System.out.println(flowTodoVo.getStateId());
			list.add(new FlowTodo(flowTodoVo));
		}
		int msg = 0;
		if (list != null && list.size() > 0) {

			msg = flowTodoDao.insertBatch(list);
		}
		return (msg == 0) ? false : true;
	}

}
