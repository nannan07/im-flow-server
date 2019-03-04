package com.allmsi.flow.third.model.vo;

public class AuthRoleVo {

	private String id;

	private String roleName;
	
	public AuthRoleVo() {

	}

	public AuthRoleVo(String id, String roleName) {
		this.id = id;
		this.roleName = roleName;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
