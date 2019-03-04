package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowNodeDealMapper;
import com.allmsi.flow.model.FlowNodeDeal;
import com.allmsi.flow.model.external.FlowDealIdATypeModel;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowNodeDealIVo;
import com.allmsi.flow.model.ovo.FlowNodeDealOVo;
import com.allmsi.flow.service.FlowNodeDealService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowNodeDealServiceImpl implements FlowNodeDealService {

	@Resource
	private FlowNodeDealMapper flowNodeDealDao;

	@Resource
	private FlowUserService flowUserService;

	@Override
	public List<FlowNodeDealOVo> selectFlowNodeDealList(String nodeId) {
		List<FlowNodeDealOVo> list = new ArrayList<FlowNodeDealOVo>();
		if (StrUtil.isEmpty(nodeId)) {
			return list;
		}
		List<FlowNodeDeal> fndList = flowNodeDealDao.selectByNodeId(nodeId);

		List<String> userIds = new ArrayList<String>();
		List<FlowDealIdATypeModel> dealIds = new ArrayList<FlowDealIdATypeModel>();
		for (FlowNodeDeal fnd : fndList) {
			if (StrUtil.notEmpty(fnd.getcUserId())) {
				userIds.add(fnd.getcUserId());
			}
			FlowDealIdATypeModel deal = new FlowDealIdATypeModel();
			deal.setDealId(fnd.getNodeDealId());
			deal.setDealType(fnd.getNodeDealType());
			dealIds.add(deal);
		}
		Map<String, FlowUserModel> flowUserMap = flowUserService.getFlowUserList(userIds);
		Map<String, String> flowDealMap = flowUserService.getFlowDealList(dealIds);
		for (FlowNodeDeal fnd : fndList) {
			FlowNodeDealOVo flowNodeDeal = new FlowNodeDealOVo(fnd);
			if (StrUtil.notEmpty(fnd.getcUserId())) {
				flowNodeDeal.setUser(flowUserMap.get(fnd.getcUserId()));
			}
			flowNodeDeal.setNodeDealName(flowDealMap.get(fnd.getNodeDealId()));
			list.add(flowNodeDeal);
		}
		return list;
	}

	@Override
	public FlowNodeDealOVo selectFlowNodeDealInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		FlowNodeDeal fnd = flowNodeDealDao.selectByPrimaryKey(id);
		if (fnd == null) {
			return null;
		} else {
			FlowNodeDealOVo fndVo = new FlowNodeDealOVo(fnd);
			if (StrUtil.notEmpty(fnd.getcUserId())) {
				Map<String, FlowUserModel> flowUserMap = flowUserService.getUserInfo(fnd.getcUserId());
				fndVo.setUser(flowUserMap.get(fnd.getcUserId()));
			}
			String nodeDealName = flowUserService.getFlowDealInfo(fnd.getNodeDealId(), fnd.getNodeDealType());
			fndVo.setNodeDealName(nodeDealName);
			return fndVo;
		}
	}

	@Override
	public String addFlowNodeDeal(FlowNodeDealIVo flowNodeDealIVO) {
		if (flowNodeDealIVO == null) {
			return null;
		}
		FlowNodeDeal fnd = new FlowNodeDeal(flowNodeDealIVO);
		fnd.setId(UUIDUtil.getUUID());
		fnd.setcUserId(flowNodeDealIVO.getcUserId());
		return (flowNodeDealDao.insertSelective(fnd) == 0) ? null : fnd.getId();
	}

	@Override
	public String updateFlowNodeDeal(FlowNodeDealIVo flowNodeDealIVO) {
		if (flowNodeDealIVO == null || StrUtil.isEmpty(flowNodeDealIVO.getId())) {
			return null;
		}
		FlowNodeDeal fnd = new FlowNodeDeal(flowNodeDealIVO);
		fnd.setuUserId(flowNodeDealIVO.getcUserId());
		return (flowNodeDealDao.updateByPrimaryKeySelective(fnd) == 0) ? null : fnd.getId();
	}

	@Override
	public boolean delFlowNodeDeal(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		FlowNodeDeal fnd = new FlowNodeDeal();
		fnd.setId(id);
		fnd.setuUserId(uUserId);
		flowNodeDealDao.deleteByPrimaryKey(fnd);
		return true;
	}

	@Override
	public List<FlowNodeDealOVo> selectFlowNodeDealListByNodeId(String nodeId) {
		List<FlowNodeDealOVo> list = new ArrayList<FlowNodeDealOVo>();
		List<FlowNodeDeal> fndList = flowNodeDealDao.selectByNodeId(nodeId);
		for (FlowNodeDeal flowNodeDeal : fndList) {
			list.add(new FlowNodeDealOVo(flowNodeDeal));
		}
		return list;
	}

}
