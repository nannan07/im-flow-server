package com.allmsi.flow.service;

import java.util.List;
import java.util.Map;

import com.allmsi.flow.model.external.FlowUserRoleVo;
import com.allmsi.flow.model.external.FlowDealIdATypeModel;
import com.allmsi.flow.model.external.FlowUserDeptVo;
import com.allmsi.flow.model.external.FlowUserModel;

public interface FlowExternalService {

	Map<String, FlowUserModel> getUserInfo(String id);

	/**
	 * key:userId value:FlowUserModel
	 */
	Map<String, FlowUserModel> getFlowUserInfoList(List<String> userIds);

	String getDealNameByDealIdAndType(String dealId, String dealType);

	/**
	 * key:dealId value:dealName
	 */
	Map<String, String> getDealNameByDealIdAndType(List<FlowDealIdATypeModel> dealIdAndDealType);

	/**
	 * key:type(01/02/03) value:dealIdList
	 */
	Map<String, List<String>> getUserAuthIdSort(String userId);

	List<FlowUserDeptVo> selectUserDept(String userId);

	List<FlowUserRoleVo> selectUserRoleList(String userId);

	List<FlowUserModel> selectUserByDeptId(String deptId);

	List<FlowUserModel> selectUserByRoleId(String roleId);

	List<FlowUserModel> deptUserByUser(String userId);

	List<FlowUserModel> allDeptUserByUser(String userId);

	List<FlowUserModel> selectUserList();

}
