package com.allmsi.flow.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.allmsi.flow.model.external.FlowUserRoleVo;
import com.allmsi.flow.model.external.FlowDealIdATypeModel;
import com.allmsi.flow.model.external.FlowUserDeptVo;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.service.FlowExternalService;
import com.allmsi.flow.service.FlowUserService;
import com.allmsi.sys.config.SpringContextRegister;
import com.allmsi.sys.util.StrUtil;

@Service
public class FlowUserServiceImpl implements FlowUserService {

	@Value("${im.flow.userList.value:defaultFlowExternalService}")
	private String imFlowUserList;

	@Autowired
	private SpringContextRegister springContextRegister;

	private final String DEFAULT_FLOW_SERVICE = "defaultFlowExternalService";

	@Override
	public FlowUserModel selectUserInfo(String nodeDealId) {
		return getUserInfo(nodeDealId).get(nodeDealId);
	}

	
	@Override
	public Map<String, FlowUserModel> getUserInfo(String id) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.getUserInfo(id);
	}

	@Override
	public Map<String, FlowUserModel> getFlowUserList(List<String> ids) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.getFlowUserInfoList(ids);
	}

	@Override
	public String getFlowDealInfo(String dealId, String dealType) {
		if (StrUtil.isEmpty(dealId) || StrUtil.isEmpty(dealType)) {
			return null;
		}
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.getDealNameByDealIdAndType(dealId, dealType);
	}

	@Override
	public Map<String, String> getFlowDealList(List<FlowDealIdATypeModel> dealIds) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.getDealNameByDealIdAndType(dealIds);
	}

	@Override
	public Map<String, List<String>> getUserAuthIdSort(String userId) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.getUserAuthIdSort(userId);
	}

	private FlowExternalService getFlowExternalService() {
		FlowExternalService flowExternalService = null;
		String className = imFlowUserList;
		if (StrUtil.isEmpty(className)) {
			flowExternalService = springContextRegister.getServiceImpl(FlowExternalService.class, DEFAULT_FLOW_SERVICE);
		} else {
			flowExternalService = springContextRegister.getServiceImpl(FlowExternalService.class, className);
		}
		return flowExternalService;
	}

	@Override
	public List<FlowUserDeptVo>  selectUserDept(String userId) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.selectUserDept(userId);
	}

	@Override
	public List<FlowUserRoleVo> selectUserRoleList(String userId) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.selectUserRoleList(userId);
	}

	@Override
	public List<FlowUserModel> selectUserByDeptId(String deptId) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.selectUserByDeptId(deptId);
	}

	@Override
	public List<FlowUserModel> selectUserByRoleId(String roleId) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.selectUserByRoleId(roleId);
	}

	@Override
	public List<FlowUserModel> deptUserByUser(String userId) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.deptUserByUser(userId);
	}

	@Override
	public List<FlowUserModel> allDeptUserByUser(String userId) {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.allDeptUserByUser(userId);
	}


	@Override
	public List<FlowUserModel> selectUserList() {
		FlowExternalService flowExternalService = getFlowExternalService();
		return flowExternalService.selectUserList();
	}

	
}
