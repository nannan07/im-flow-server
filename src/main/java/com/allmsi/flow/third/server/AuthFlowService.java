package com.allmsi.flow.third.server;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "auth-server-1")
public interface AuthFlowService {

	/**
	 * 用户基本信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/auth/select/userInfo", method = RequestMethod.GET)
	String selectUserInfo(@RequestParam("userId") String userId);

	/**
	 * 部门基本信息
	 * 
	 * @param deptId
	 * @return
	 */
	@RequestMapping(value = "/auth/select/deptInfo", method = RequestMethod.GET)
	String selectDeptInfo(@RequestParam("deptId") String deptId);

	/**
	 * 角色基本信息
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/auth/select/roleInfo", method = RequestMethod.GET)
	String selectRoleInfo(@RequestParam("roleId") String roleId);

	/**
	 * 用户所在部门列表信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/auth/select/userDept", method = RequestMethod.GET)
	String selectUserDept(@RequestParam("userId") String userId);

	/**
	 * 用户角色列表信息
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/auth/select/user/role", method = RequestMethod.GET)
	String selectUserRoleList(@RequestParam("userId") String userId);

	/**
	 * 批量查询用户信息
	 * 
	 * @param userIds
	 * @return
	 */
	@RequestMapping(value = "/auth/select/userList", method = RequestMethod.GET)
	String selectUserList(@RequestParam("userIds") List<String> userIds);

	/**
	 * 批量查询部门信息
	 * 
	 * @param deptIds
	 * @return
	 */
	@RequestMapping(value = "/auth/select/deptList", method = RequestMethod.GET)
	String selectDeptList(@RequestParam("deptIds") List<String> deptIds);

	/**
	 * 批量查询角色信息
	 * 
	 * @param roleIds
	 * @return
	 */
	@RequestMapping(value = "/auth/select/roleList", method = RequestMethod.GET)
	String selectRoleList(@RequestParam("roleIds") List<String> roleIds);

	/**
	 * 部门下的用户信息
	 * 
	 * @param deptId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/auth/select/deptUser", method = RequestMethod.GET)
	String selectUserByDeptId(@RequestParam("deptId") String deptId);

	/**
	 * 部门下的用户信息（根据用户信息查询）
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/auth/select/deptUserByUser", method = RequestMethod.GET)
	String deptUserByUser(@RequestParam("userId") String userId);

	/**
	 * 角色下的用户信息
	 * 
	 * @param roleId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/auth/select/roleUser", method = RequestMethod.GET)
	String selectUserByRoleId(@RequestParam("roleId") String roleId, @RequestParam("page") Integer page,
			@RequestParam("pageSize") Integer pageSize);

	/**
	 * 包含子部门下的用户信息
	 * 
	 * @param deptId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/auth/select/subDeptUser", method = RequestMethod.GET)
	String selectSubDeptUser(@RequestParam("deptId") String deptId);

	/**
	 * 大部门下的用户信息（根据用户信息查询）
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/auth/select/allDeptUserByUser", method = RequestMethod.GET)
	String allDeptUserByUser(@RequestParam("userId") String userId);

	@RequestMapping(value = "/auth/select/allUser", method = RequestMethod.GET)
	String selectUserList(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize);
}
