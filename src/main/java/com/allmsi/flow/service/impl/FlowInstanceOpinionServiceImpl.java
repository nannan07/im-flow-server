package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceOpinionMapper;
import com.allmsi.flow.model.FlowInstanceOpinion;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowInstanceOpinionIVo;
import com.allmsi.flow.model.ovo.FlowInstanceOpinionOVo;
import com.allmsi.flow.service.FlowInstanceOpinionService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceOpinionServiceImpl implements FlowInstanceOpinionService {

	@Resource
	private FlowInstanceOpinionMapper flowInstanceOpinionDao;
	
	@Resource
	private FlowUserService flowUserService;

	@Override
	public int selectCountFlowInstanceOpinion(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return 0;
		}
		return flowInstanceOpinionDao.selectCountFlowInstanceOpinion(instanceId);
	}

	@Override
	public List<FlowInstanceOpinionOVo> selectFlowInstanceOpinionList(String instanceId, Integer page,
			Integer pageSize) {
		List<FlowInstanceOpinionOVo> list = new ArrayList<FlowInstanceOpinionOVo>();
		if (StrUtil.isEmpty(instanceId)) {
			return list;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("instanceId", instanceId);
		if (page != null && pageSize != null) {
			map.put("page", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		
		List<FlowInstanceOpinion> fipList = flowInstanceOpinionDao.selectFlowInstanceOpinionList(map);
		List<String> userIds = new ArrayList<String>();
		for (FlowInstanceOpinion flowInstanceOpinion : fipList) {
			String userId = flowInstanceOpinion.getcUserId();
			if(StrUtil.isEmpty(userId)){
				continue;
			}
			userIds.add(userId);
		}
		Map<String, FlowUserModel> flowUserMap = flowUserService.getFlowUserList(userIds);
		for (FlowInstanceOpinion flowInstanceOpinion : fipList) {
			FlowInstanceOpinionOVo flowInstanceOpinionOVo = new FlowInstanceOpinionOVo(flowInstanceOpinion);
			String userId = flowInstanceOpinion.getcUserId();
			if(StrUtil.isEmpty(userId)){
				list.add(flowInstanceOpinionOVo);
				continue;
			}
			flowInstanceOpinionOVo.setUser(flowUserMap.get(userId));
			list.add(flowInstanceOpinionOVo);
		}
		return list;
	}

	@Override
	public String addFlowInstanceOpinion(FlowInstanceOpinionIVo flowInstanceOpinionIVo) {
		if (flowInstanceOpinionIVo == null) {
			return null;
		}
		FlowInstanceOpinion FlowInstanceOpinion = new FlowInstanceOpinion(flowInstanceOpinionIVo);
		String id = UUIDUtil.getUUID();
		FlowInstanceOpinion.setId(id);
		return (flowInstanceOpinionDao.insertSelective(FlowInstanceOpinion) == 0) ? null : id;
	}

	@Override
	public String updateFlowInstanceOpinion(FlowInstanceOpinionIVo flowInstanceOpinionIVo) {
		if (flowInstanceOpinionIVo == null || StrUtil.isEmpty(flowInstanceOpinionIVo.getId())) {
			return null;
		}
		FlowInstanceOpinion FlowInstanceOpinion = new FlowInstanceOpinion(flowInstanceOpinionIVo);
		return (flowInstanceOpinionDao.updateByPrimaryKeySelective(FlowInstanceOpinion) == 0) ? null
				: FlowInstanceOpinion.getId();
	}

	@Override
	public boolean delFlowInstanceOpinion(String id) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		flowInstanceOpinionDao.deleteByPrimaryKey(id);
		return true;
	}

}
