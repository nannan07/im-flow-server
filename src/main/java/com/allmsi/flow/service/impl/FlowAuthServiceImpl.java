package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.allmsi.flow.dao.FlowAuthMapper;
import com.allmsi.flow.model.FlowAuth;
import com.allmsi.flow.model.external.FlowDealIdATypeModel;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.ivo.FlowAuthIVo;
import com.allmsi.flow.model.ovo.FlowAuthOVo;
import com.allmsi.flow.service.FlowAuthService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.util.StrUtil;
import com.allmsi.sys.util.UUIDUtil;

@Service
public class FlowAuthServiceImpl implements FlowAuthService {

	@Resource
	private FlowAuthMapper flowAuthDao;

	@Resource
	private FlowUserService flowUserService;

	@Override
	public List<FlowAuthOVo> selectFlowAuthList(String flowId) {
		List<FlowAuthOVo> list = new ArrayList<FlowAuthOVo>();
		if(StrUtil.isEmpty(flowId)){
			return list;
		}
		List<FlowAuth> faList = flowAuthDao.selectByFlowId(flowId);
		List<String> ids = new ArrayList<String>();
		List<FlowDealIdATypeModel> dealIds = new ArrayList<FlowDealIdATypeModel>();
		for (FlowAuth fa : faList) {
			FlowDealIdATypeModel fdm = new FlowDealIdATypeModel();
			fdm.setDealId(fa.getAuthDealId());
			fdm.setDealType(fa.getAuthDealType());
			dealIds.add(fdm);
			
			String cUserId = fa.getcUserId();
			if(StrUtil.isEmpty(cUserId)){
				continue;
			}
			ids.add(cUserId);
		}
		
		Map<String, FlowUserModel> map = flowUserService.getFlowUserList(ids);
		Map<String, String> dealNameMap =  flowUserService.getFlowDealList(dealIds);
		for (FlowAuth fa : faList) {
			FlowAuthOVo flowAuthOVo = new FlowAuthOVo(fa);
			flowAuthOVo.setAuthDealName(dealNameMap.get(fa.getAuthDealId()));
			
			String cUserId = fa.getcUserId();
			if(StrUtil.isEmpty(cUserId)){
				list.add(flowAuthOVo);
				continue;
			}
			flowAuthOVo.setUser(map.get(cUserId));
			list.add(flowAuthOVo);
		}
		return list;
	}

	@Override
	public FlowAuthOVo selectByPrimaryKey(String id) {
		if (StrUtil.isEmpty(id)) {
			return null;
		}
		FlowAuth fa = flowAuthDao.selectByPrimaryKey(id);
		if (fa != null) {
			FlowAuthOVo flowAuthOVo = new FlowAuthOVo(fa);
			String authDealName = flowUserService.getFlowDealInfo(fa.getAuthDealId(), fa.getAuthDealType());
			flowAuthOVo.setAuthDealName(authDealName);
			String cUserId = fa.getcUserId();
			if (StrUtil.notEmpty(cUserId)) {
				Map<String, FlowUserModel> map = flowUserService.getUserInfo(cUserId);
				flowAuthOVo.setUser(map.get(cUserId));
			}
			return flowAuthOVo;
		}
		return null;
	}

	@Override
	public String addFlowAuth(FlowAuthIVo flowAuthIVo) {
		if (flowAuthIVo == null) {
			return null;
		}
		FlowAuth fa = new FlowAuth(flowAuthIVo);
		fa.setcUserId(flowAuthIVo.getcUserId());
		String id = UUIDUtil.getUUID();
		fa.setId(id);
		return (flowAuthDao.insertSelective(fa) == 0) ? null : id;
	}

	@Override
	public String updateFlowAuth(FlowAuthIVo flowAuthIVo) {
		if (flowAuthIVo == null || StrUtil.isEmpty(flowAuthIVo.getId())) {
			return null;
		}
		FlowAuth fa = new FlowAuth(flowAuthIVo);
		fa.setuUserId(flowAuthIVo.getcUserId());
		return (flowAuthDao.updateByPrimaryKeySelective(fa) == 0) ? null : fa.getId();
	}

	@Override
	public boolean delFlowAuth(String id, String uUserId) {
		if (StrUtil.isEmpty(id)) {
			return false;
		}
		FlowAuth fa = new FlowAuth();
		fa.setId(id);
		fa.setuUserId(uUserId);
		flowAuthDao.deleteByPrimaryKey(fa);
		return true;
	}

}
