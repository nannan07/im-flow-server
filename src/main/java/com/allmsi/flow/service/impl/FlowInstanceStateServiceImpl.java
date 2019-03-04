package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowInstanceLogMapper;
import com.allmsi.flow.dao.FlowInstanceStateMapper;
import com.allmsi.flow.model.FlowInstanceLog;
import com.allmsi.flow.model.FlowInstanceState;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowInstanceLogIVo;
import com.allmsi.flow.model.ivo.FlowInstanceStateIVo;
import com.allmsi.flow.model.ovo.FlowInstanceStateOVo;
import com.allmsi.flow.model.ovo.FlowInstenceCurrencyOVO;
import com.allmsi.flow.service.FlowInstanceLogService;
import com.allmsi.flow.service.FlowInstanceStateService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowInstanceStateServiceImpl implements FlowInstanceStateService {

	@Resource
	private FlowInstanceStateMapper flowInstanceStateDao;

	@Resource
	private FlowUserService flowUserService;

	@Resource
	private FlowInstanceLogService flowInstanceLogService;

	@Resource
	private FlowInstanceLogMapper flowInstanceLogDao;

	@Override
	public List<FlowInstanceStateOVo> selectFlowInstanceState(String instanceId) {
		if (StrUtil.isEmpty(instanceId)) {
			return null;
		}
		List<FlowInstanceStateOVo> list = new ArrayList<FlowInstanceStateOVo>();
		List<FlowInstanceState> fisList = flowInstanceStateDao.selectByInstanceId(instanceId);
		for (FlowInstanceState flowInstanceState : fisList) {
			FlowInstanceStateOVo flowInstanceStateOVo = new FlowInstanceStateOVo(flowInstanceState);
			String preDealId = flowInstanceState.getPreDealId();
			String preDealName = flowUserService.getUserInfo(preDealId).get(preDealId).getName();
			flowInstanceStateOVo.setPreDealName(preDealName);
			String userId = flowInstanceState.getuUserId();
			if (StrUtil.notEmpty(userId)) {
				FlowUserModel user = flowUserService.getUserInfo(userId).get(userId);
				flowInstanceStateOVo.setUser(user);
			}
			String sufDealId = flowInstanceState.getSufDealId();
			String sufDealType = flowInstanceState.getSufDealType();
			String sufDealName = flowUserService.getFlowDealInfo(sufDealId, sufDealType);
			flowInstanceStateOVo.setSufDealName(sufDealName);
			list.add(flowInstanceStateOVo);
		}
		return list;
	}

	@Override
	public String addFlowInstanceState(FlowInstanceStateIVo flowInstanceStateIVo) {
		if (flowInstanceStateIVo == null || StrUtil.isEmpty(flowInstanceStateIVo.getInstanceId())) {
			return null;
		}
		FlowInstanceState fis = new FlowInstanceState(flowInstanceStateIVo);
		String id = UUIDUtil.getUUID();
		fis.setId(id);
		int msg = flowInstanceStateDao.insertSelective(fis);
		flowInstanceLogService.addFlowInstanceLog(new FlowInstanceLogIVo(flowInstanceStateIVo));
		return (msg == 0) ? null : id;
	}

	@Override
	public String updateFlowInstanceState(FlowInstanceStateIVo flowInstanceStateIVo) {
		if (flowInstanceStateIVo == null || StrUtil.isEmpty(flowInstanceStateIVo.getId())
				|| StrUtil.isEmpty(flowInstanceStateIVo.getInstanceId())) {
			return null;
		}
		FlowInstanceState fis = new FlowInstanceState(flowInstanceStateIVo);
		int msg = flowInstanceStateDao.updateByPrimaryKeySelective(fis);
		flowInstanceLogService.addFlowInstanceLog(new FlowInstanceLogIVo(flowInstanceStateIVo));
		return (msg == 0) ? null : fis.getId();
	}

	@Override
	public int addFlowInstanceStates(List<FlowInstanceStateIVo> list) {
		List<FlowInstanceState> fiList = new ArrayList<FlowInstanceState>();
		List<FlowInstanceLog> logList = new ArrayList<FlowInstanceLog>();
		for (FlowInstanceStateIVo flowInstanceStateIVo : list) {
			FlowInstanceState fis = new FlowInstanceState(flowInstanceStateIVo);
			String id = UUIDUtil.getUUID();
			fis.setId(id);
			fiList.add(fis);
			FlowInstanceLog ivo = new FlowInstanceLog(flowInstanceStateIVo);
			ivo.setId(UUIDUtil.getUUID());
			logList.add(ivo);
		}
		int msg = 0;
		if (fiList != null && fiList.size() > 0) {
			msg = flowInstanceStateDao.insertBantch(fiList);
			flowInstanceLogDao.insertBantch(logList);
		}
		return msg;
	}

	@Override
	public List<String> updateFlowInstanceStates(List<FlowInstanceStateIVo> list) {
		if (list == null || list.size() < 1) {
			return null;
		}
		String instanceId = list.get(0).getInstanceId();
		flowInstanceStateDao.deleteBantch(instanceId);
		List<FlowInstanceState> fiList = new ArrayList<FlowInstanceState>();
		List<FlowInstanceLog> logList = new ArrayList<FlowInstanceLog>();
		List<String> strList = new ArrayList<String>();
		for (FlowInstanceStateIVo flowInstanceStateIVo : list) {
			FlowInstanceState fis = new FlowInstanceState(flowInstanceStateIVo);
			String stateId = UUIDUtil.getUUID();
			fis.setId(stateId);
			fiList.add(fis);
			strList.add(stateId);
			FlowInstanceLog ivo = new FlowInstanceLog(flowInstanceStateIVo);
			ivo.setId(UUIDUtil.getUUID());
			logList.add(ivo);
		}
		int msg = 0;
		if (fiList != null && fiList.size() > 0) {
			msg = flowInstanceStateDao.insertBantch(fiList);
			flowInstanceLogDao.insertBantch(logList);
		}
		return (msg == 0) ? null : strList;
	}

	@Override
	public FlowInstanceStateOVo selectFlowInstanceStateByObj(String objectId, String userId) {
		List<FlowInstanceStateOVo> list = new ArrayList<FlowInstanceStateOVo>();
		List<FlowInstanceState> fisList = flowInstanceStateDao.selectFlowInstanceStateByObj(objectId);// 流程实例的所有办理人
		Map<String, List<String>> map = flowUserService.getUserAuthIdSort(userId);
		for (FlowInstanceState flowInstanceState : fisList) {
			FlowInstanceStateOVo flowInstanceStateOVo = new FlowInstanceStateOVo(flowInstanceState);
			String preDealId = flowInstanceState.getPreDealId();
			String preDealName = null;
			if (StrUtil.notEmpty(preDealId)) {
				preDealName = flowUserService.getUserInfo(preDealId).get(preDealId).getName();
			}
			flowInstanceStateOVo.setPreDealName(preDealName);
			String sufDealType = flowInstanceState.getSufDealType();
			List<String> dealIds = new ArrayList<>();
			dealIds.addAll(map.get(sufDealType));
			String sufDealId = flowInstanceState.getSufDealId();

			if (dealIds.contains(sufDealId)) {
				String sufDealName = flowUserService.getFlowDealInfo(sufDealId, sufDealType);
				flowInstanceStateOVo.setSufDealName(sufDealName);
				list.add(flowInstanceStateOVo);
			}
		}
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<FlowInstanceStateOVo> selectFlowInstanceStateByObjectId(String objId) {
		List<FlowInstanceStateOVo> list = new ArrayList<FlowInstanceStateOVo>();
		List<FlowInstanceState> fisList = flowInstanceStateDao.selectFlowInstanceStateByObj(objId);
		for (FlowInstanceState flowInstanceState : fisList) {
			list.add(new FlowInstanceStateOVo(flowInstanceState));
		}
		return list;
	}

	@Override
	public FlowInstenceCurrencyOVO flowInstenceStateInfo(String objId, String userId) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("objId", objId);
		map.put("sufDealId", userId);
		map.put("sufDealType", "01");
		FlowInstanceState flowInstanceState = flowInstanceStateDao.flowInstenceStateInfo(map);
		FlowInstenceCurrencyOVO flowInstenceCurrencyOVO = new FlowInstenceCurrencyOVO(flowInstanceState);
		return flowInstenceCurrencyOVO;
	}
}
