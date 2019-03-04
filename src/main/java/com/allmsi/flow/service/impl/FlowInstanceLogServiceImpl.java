package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceLogMapper;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowInstanceLogIVo;
import com.allmsi.flow.model.ovo.FlowInstanceLogOVo;
import com.allmsi.flow.service.FlowInstanceLogService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceLogServiceImpl implements FlowInstanceLogService {

	@Resource
	private FlowInstanceLogMapper flowInstanceLogDao;

	@Resource
	private FlowUserService flowUserService;

	@Override
	public int selectCountInstanceLog(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return 0;
		}
		return flowInstanceLogDao.selectCountInstanceLog(instanceId);
	}

	@Override
	public List<FlowInstanceLogOVo> selectFlowInstanceLogList(String instanceId, Integer page, Integer pageSize) {
		List<FlowInstanceLogOVo> list = new ArrayList<FlowInstanceLogOVo>();
		if (StrUtil.isEmpty(instanceId)) {
			return list;
		}
		Map<String,Object>  map = new HashMap<String,Object>();
		map.put("instanceId", instanceId);
		if (page != null && pageSize != null) {
			map.put("page", (page - 1) * pageSize);
			map.put("pageSize", pageSize);
		}
		List<FlowInstanceLog> filList = flowInstanceLogDao.selectByInstanceId(map);
		List<String> userIds = new ArrayList<String>();
		for (FlowInstanceLog flowInstanceLog : filList) {
			String userId = flowInstanceLog.getcUserId();
			if (StrUtil.isEmpty(userId)) {
				continue;
			}
			userIds.add(userId);
		}
		Map<String, FlowUserModel> flowUserMap = flowUserService.getFlowUserList(userIds);
		for (FlowInstanceLog flowInstanceLog : filList) {
			FlowInstanceLogOVo flowInstanceLogOVo = new FlowInstanceLogOVo(flowInstanceLog);

			String userId = flowInstanceLog.getcUserId();
			if (StrUtil.isEmpty(userId)) {
				list.add(flowInstanceLogOVo);
				continue;
			}
			flowInstanceLogOVo.setUser(flowUserMap.get(userId));
			list.add(flowInstanceLogOVo);
		}
		return list;
	}

	@Override
	public String addFlowInstanceLog(FlowInstanceLogIVo flowInstanceLogIVo) {
		if (flowInstanceLogIVo == null || StrUtil.isEmpty(flowInstanceLogIVo.getInstanceId())) {
			return null;
		}
		FlowInstanceLog flowInstanceLog = new FlowInstanceLog(flowInstanceLogIVo);
		String id = UUIDUtil.getUUID();
		flowInstanceLog.setId(id);
		return (flowInstanceLogDao.insertSelective(flowInstanceLog) == 0) ? null : id;
	}

}
