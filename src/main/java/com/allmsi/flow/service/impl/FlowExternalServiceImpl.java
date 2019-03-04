package com.allmsi.flow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allmsi.flow.model.external.FlowDealIdATypeModel;
import com.allmsi.flow.model.external.FlowUserDeptVo;
import com.allmsi.flow.model.external.FlowUserModel;
import com.allmsi.flow.model.external.FlowUserRoleVo;
import com.allmsi.flow.service.FlowExternalService;
import com.allmsi.flow.third.model.vo.AuthDeptVo;
import com.allmsi.flow.third.model.vo.AuthRoleVo;
import com.allmsi.flow.third.model.vo.AuthUserInfoVo;
import com.allmsi.flow.third.server.AuthFlowService;
import com.allmsi.sys.util.StrUtil;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

@Service("defaultFlowExternalService")
public class FlowExternalServiceImpl implements FlowExternalService {

	@Autowired
	private AuthFlowService authFlowService;

	@Override
	public Map<String, FlowUserModel> getUserInfo(String id) {
		Map<String, FlowUserModel> map = new HashMap<String, FlowUserModel>();
		if (StrUtil.isEmpty(id)) {
			return map;
		}
		String json = authFlowService.selectUserInfo(id);
		Gson gson = new Gson();
		AuthUserInfoVo userInfo = gson.fromJson(json, AuthUserInfoVo.class);
		FlowUserModel fu = new FlowUserModel();
		fu.setUserId(userInfo.getId());
		fu.setName(userInfo.getUserName());
		fu.setType("01");
		map.put(id, fu);
		return map;
	}

	public Map<String, FlowUserModel> getFlowUserInfoList(List<String> ids) {
		Map<String, FlowUserModel> map = new HashMap<String, FlowUserModel>();
		if (ids != null && ids.size() > 0) {
			String json = authFlowService.selectUserList(ids);
			Gson gson = new Gson();
			List<AuthUserInfoVo> udInfoList = gson.fromJson(json, new TypeToken<List<AuthUserInfoVo>>() {
			}.getType());
			for (AuthUserInfoVo userInfoVo : udInfoList) {
				FlowUserModel flowUserModel = new FlowUserModel();
				flowUserModel.setName(userInfoVo.getUserName());
				flowUserModel.setUserId(userInfoVo.getId());
				flowUserModel.setType("01");
				map.put(userInfoVo.getId(), flowUserModel);
			}
			return map;
		}

		return map;
	}

	public Map<String, String> getDealNameByDealIdAndType(List<FlowDealIdATypeModel> dealIdAndDealType) {
		Map<String, String> map = new HashMap<String, String>();

		List<String> userList = new ArrayList<String>();
		List<String> deptList = new ArrayList<String>();
		List<String> roleList = new ArrayList<String>();

		for (FlowDealIdATypeModel model : dealIdAndDealType) {
			if ("01".equals(model.getDealType())) {
				userList.add(model.getDealId());
			}
			if ("02".equals(model.getDealType())) {
				deptList.add(model.getDealId());
			}
			if ("03".equals(model.getDealType())) {
				roleList.add(model.getDealId());
			}
		}

		// 批量查询用户
		if (userList != null && userList.size() > 0) {
			Map<String, FlowUserModel> map1 = getFlowUserInfoList(userList);
			for (String key : map1.keySet()) {
				FlowUserModel value = map1.get(key);
				map.put(key, value.getName());
			}
		}

		Gson gson = new Gson();
		// 批量查询部门
		if (deptList != null && deptList.size() > 0) {
			String dJson = authFlowService.selectDeptList(deptList);
			List<AuthDeptVo> udInfoList = gson.fromJson(dJson, new TypeToken<List<AuthDeptVo>>() {
			}.getType());
			for (AuthDeptVo deptVo : udInfoList) {
				map.put(deptVo.getId(), deptVo.getDeptName());
			}
		}
		if (roleList != null && roleList.size() > 0) {
			// 批量查询角色
			String rJson = authFlowService.selectRoleList(roleList);
			List<AuthRoleVo> rInfoList = gson.fromJson(rJson, new TypeToken<List<AuthRoleVo>>() {
			}.getType());
			for (AuthRoleVo roleVo : rInfoList) {
				map.put(roleVo.getId(), roleVo.getRoleName());
			}
		}
		return map;
	}

	public String getDealNameByDealIdAndType(String dealId, String dealType) {
		String dealName = null;

		if ("01".equals(dealType)) {// 查询用户信息
			String json = authFlowService.selectUserInfo(dealId);
			Gson gson = new Gson();
			AuthUserInfoVo userInfo = gson.fromJson(json, AuthUserInfoVo.class);
			if (userInfo != null) {
				dealName = userInfo.getUserName();
			}

		}
		if ("02".equals(dealType)) {
			String json = authFlowService.selectDeptInfo(dealId);
			Gson gson = new Gson();
			AuthDeptVo deptInfo = gson.fromJson(json, AuthDeptVo.class);
			dealName = deptInfo.getDeptName();
		}
		if ("03".equals(dealType)) {
			String json = authFlowService.selectRoleInfo(dealId);
			Gson gson = new Gson();
			AuthRoleVo roleInfo = gson.fromJson(json, AuthRoleVo.class);
			dealName = roleInfo.getRoleName();
		}
		return dealName;
	}

	public Map<String, List<String>> getUserAuthIdSort(String userId) {
		Map<String, List<String>> authMap = new HashMap<String, List<String>>();
		// 查询用户的基本信息
		String userJson = authFlowService.selectUserInfo(userId);
		Gson gson = new Gson();
		AuthUserInfoVo userInfo = gson.fromJson(userJson, AuthUserInfoVo.class);
		List<String> userList = new ArrayList<String>();
		userList.add(userInfo.getId());
		if (userList != null && userList.size() > 0) {
			authMap.put("01", userList);
		}

		// 查询用户部门信息
		String udJson = authFlowService.selectUserDept(userId);
		List<FlowUserDeptVo> udInfoList = gson.fromJson(udJson, new TypeToken<List<FlowUserDeptVo>>() {
		}.getType());
		List<String> udList = new ArrayList<String>();
		if (udInfoList != null && udInfoList.size() > 0) {
			for (FlowUserDeptVo userDeptVo : udInfoList) {
				udList.add(userDeptVo.getDeptId());
			}
			authMap.put("02", udList);
		}

		// 查询用户角色信息
		String urJson = authFlowService.selectUserRoleList(userId);
		List<FlowUserRoleVo> uRInfoList = gson.fromJson(urJson, new TypeToken<List<FlowUserRoleVo>>() {
		}.getType());
		List<String> urList = new ArrayList<String>();
		if (uRInfoList != null && uRInfoList.size() > 0) {
			for (FlowUserRoleVo userRoleVo : uRInfoList) {
				urList.add(userRoleVo.getRoleId());
			}
			authMap.put("03", urList);
		}
		return authMap;
	}

	@Override
	public List<FlowUserDeptVo> selectUserDept(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		Gson gson = new Gson();
		String udJson = authFlowService.selectUserDept(userId);
		List<FlowUserDeptVo> udInfoList = gson.fromJson(udJson, new TypeToken<List<FlowUserDeptVo>>() {
		}.getType());
		return udInfoList;
	}

	@Override
	public List<FlowUserRoleVo> selectUserRoleList(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		Gson gson = new Gson();
		String urJson = authFlowService.selectUserRoleList(userId);
		List<FlowUserRoleVo> uRInfoList = gson.fromJson(urJson, new TypeToken<List<FlowUserRoleVo>>() {
		}.getType());
		return uRInfoList;
	}

	@Override
	public List<FlowUserModel> selectUserByDeptId(String deptId) {
		if (StrUtil.isEmpty(deptId)) {
			return null;
		}
		Gson gson = new Gson();
		String uJson = authFlowService.selectUserByDeptId(deptId);
		JsonElement je = new JsonParser().parse(uJson);
		JsonElement json = je.getAsJsonObject().get("list");
		List<AuthUserInfoVo> uInfoList = gson.fromJson(json, new TypeToken<List<AuthUserInfoVo>>() {
		}.getType());
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (AuthUserInfoVo authUserInfoVo : uInfoList) {
			FlowUserModel flowUserModel = new FlowUserModel();
			flowUserModel.setUserId(authUserInfoVo.getId());
			flowUserModel.setName(authUserInfoVo.getUserName());
			list.add(flowUserModel);
		}
		return list;
	}

	@Override
	public List<FlowUserModel> selectUserByRoleId(String roleId) {
		if (StrUtil.isEmpty(roleId)) {
			return null;
		}
		Gson gson = new Gson();
		String uJson = authFlowService.selectUserByRoleId(roleId, 1, Integer.MAX_VALUE);
		JsonElement je = new JsonParser().parse(uJson);
		JsonElement json = je.getAsJsonObject().get("list");
		List<AuthUserInfoVo> uInfoList = gson.fromJson(json, new TypeToken<List<AuthUserInfoVo>>() {
		}.getType());
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (AuthUserInfoVo authUserInfoVo : uInfoList) {
			FlowUserModel flowUserModel = new FlowUserModel();
			flowUserModel.setUserId(authUserInfoVo.getId());
			flowUserModel.setName(authUserInfoVo.getUserName());
			list.add(flowUserModel);
		}
		return list;
	}

	@Override
	public List<FlowUserModel> deptUserByUser(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		Gson gson = new Gson();
		String json = authFlowService.deptUserByUser(userId);
		List<AuthUserInfoVo> uInfoList = gson.fromJson(json, new TypeToken<List<AuthUserInfoVo>>() {
		}.getType());
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (AuthUserInfoVo authUserInfoVo : uInfoList) {
			FlowUserModel flowUserModel = new FlowUserModel();
			flowUserModel.setUserId(authUserInfoVo.getId());
			flowUserModel.setName(authUserInfoVo.getUserName());
			list.add(flowUserModel);
		}
		return list;
	}

	@Override
	public List<FlowUserModel> allDeptUserByUser(String userId) {
		if (StrUtil.isEmpty(userId)) {
			return null;
		}
		Gson gson = new Gson();
		String uJson = authFlowService.allDeptUserByUser(userId);
		JsonElement je = new JsonParser().parse(uJson);
		JsonElement json = je.getAsJsonObject().get("list");
		List<AuthUserInfoVo> uInfoList = gson.fromJson(json, new TypeToken<List<AuthUserInfoVo>>() {
		}.getType());
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (AuthUserInfoVo authUserInfoVo : uInfoList) {
			FlowUserModel flowUserModel = new FlowUserModel();
			flowUserModel.setUserId(authUserInfoVo.getId());
			flowUserModel.setName(authUserInfoVo.getUserName());
			list.add(flowUserModel);
		}
		return list;
	}

	@Override
	public List<FlowUserModel> selectUserList() {
		String uJson = authFlowService.selectUserList(1, Integer.MAX_VALUE);
		JsonElement je = new JsonParser().parse(uJson);
		JsonElement json = je.getAsJsonObject().get("list");
		Gson gson = new Gson();
		List<AuthUserInfoVo> uInfoList = gson.fromJson(json, new TypeToken<List<AuthUserInfoVo>>() {
		}.getType());
		List<FlowUserModel> list = new ArrayList<FlowUserModel>();
		for (AuthUserInfoVo authUserInfoVo : uInfoList) {
			FlowUserModel flowUserModel = new FlowUserModel();
			flowUserModel.setUserId(authUserInfoVo.getId());
			flowUserModel.setName(authUserInfoVo.getUserName());
			list.add(flowUserModel);
		}
		return list;
	}

}
