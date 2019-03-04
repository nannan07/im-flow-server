package com.allmsi.flow.service;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.external.FlowDealIdATypeModel;
import com.allmsi.flow.model.external.FlowUserDeptVo;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.external.FlowUserRoleVo;

public interface FlowUserService {

	Map<String, FlowUserModel> getUserInfo(String id);

	Map<String, FlowUserModel> getFlowUserList(List<String> ids);

	String getFlowDealInfo(String dealId, String dealType);

	Map<String, String> getFlowDealList(List<FlowDealIdATypeModel> dealIds);

	Map<String, List<String>> getUserAuthIdSort(String userId);

	List<FlowUserDeptVo> selectUserDept(String userId);

	List<FlowUserRoleVo> selectUserRoleList(String userId);
	
	List<FlowUserModel> selectUserByDeptId(String deptId);
	
	List<FlowUserModel> selectUserByRoleId(String roleId);
	
	List<FlowUserModel> deptUserByUser(String userId);
	
	List<FlowUserModel> allDeptUserByUser(String userId);

	FlowUserModel selectUserInfo(String nodeDealId);

	List<FlowUserModel> selectUserList();

}
