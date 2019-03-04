package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowRouteDealMapper;
import com.allmsi.flow.model.FlowRouteDeal;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowRouteDealIVo;
import com.allmsi.flow.model.ovo.FlowRouteDealOVo;
import com.allmsi.flow.service.FlowNodeService;
import com.allmsi.flow.service.FlowRouteDealService;
import com.allmsi.flow.service.FlowRouteService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowRouteDealServiceImpl implements FlowRouteDealService {

	@Resource
	private FlowRouteDealMapper flowRouteDealDao;

	@Resource
	private FlowUserService flowUserService;

	@Resource
	private FlowNodeService flowNodeService;

	@Resource
	private FlowRouteService flowRouteService;

	@Override
	public List<FlowRouteDealOVo> selectFlowRouteDealList(String routeId) {
		List<FlowRouteDealOVo> list = new ArrayList<FlowRouteDealOVo>();
		if (StrUtil.isEmpty(routeId)) {
			return list;
		}
		List<FlowRouteDeal> frdList = flowRouteDealDao.selectFlowRouteDealList(routeId);
		List<String> ids = new ArrayList<String>();
		for (FlowRouteDeal frd : frdList) {
			String cUserId = frd.getcUserId();
			if (StrUtil.isEmpty(cUserId)) {
				continue;
			}
			ids.add(cUserId);
		}
		Map<String, FlowUserModel> map = flowUserService.getFlowUserList(ids);
		for (FlowRouteDeal frd : frdList) {
			FlowRouteDealOVo FlowRouteDealOVo = new FlowRouteDealOVo(frd);
			String cUserId = frd.getcUserId();
			if (StrUtil.isEmpty(cUserId)) {
				list.add(FlowRouteDealOVo);
				continue;
			}
			FlowRouteDealOVo.setUser(map.get(cUserId));
			list.add(FlowRouteDealOVo);
		}
		return list;
	}

	@Override
	public FlowRouteDealOVo selectFlowRouteDealInfo(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		FlowRouteDeal frd = flowRouteDealDao.selectByPrimaryKey(id);
		if (frd != null) {
			FlowRouteDealOVo flowRouteDealOVo = new FlowRouteDealOVo(frd);
			String cUserId = frd.getcUserId();
			if (StrUtil.notEmpty(cUserId)) {
				Map<String, FlowUserModel> map = flowUserService.getUserInfo(cUserId);
				FlowUserModel fu = map.get(cUserId);
				flowRouteDealOVo.setUser(fu);
			}
			return flowRouteDealOVo;
		}
		return null;
	}

	@Override
	public String addFlowRouteDeal(FlowRouteDealIVo flowRouteDealIVo) {
		if (flowRouteDealIVo == null) {
			return null;
		}
		FlowRouteDeal frd = new FlowRouteDeal(flowRouteDealIVo);
		frd.setcUserId(flowRouteDealIVo.getcUserId());
		String id = UUIDUtil.getUUID();
		frd.setId(id);
		return (flowRouteDealDao.insertSelective(frd) == 0) ? null : id;
	}

	@Override
	public String updateFlowRouteDeal(FlowRouteDealIVo flowRouteDealIVo) {
		if (flowRouteDealIVo == null || StrUtil.isEmpty(flowRouteDealIVo.getId())) {
			return null;
		}
		FlowRouteDeal frd = new FlowRouteDeal(flowRouteDealIVo);
		frd.setuUserId(flowRouteDealIVo.getcUserId());
		return (flowRouteDealDao.updateByPrimaryKeySelective(frd) == 0) ? null : flowRouteDealIVo.getId();
	}

	@Override
	public boolean delFlowRouteDeal(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		FlowRouteDeal frd = new FlowRouteDeal();
		frd.setId(id);
		frd.setuUserId(uUserId);
		flowRouteDealDao.deleteByPrimaryKey(frd);
		return true;
	}

}
