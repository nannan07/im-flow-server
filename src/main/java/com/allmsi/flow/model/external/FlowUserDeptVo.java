package com.allmsi.flow.model.external;

public class FlowUserDeptVo {

	private String deptId;

	private String udType;

	private String deptName;
	
	private Integer deptType;

	public FlowUserDeptVo() {

	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getUdType() {
		return udType;
	}

	public void setUdType(String udType) {
		this.udType = udType;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getDeptType() {
		return deptType;
	}

	public void setDeptType(Integer deptType) {
		this.deptType = deptType;
	}
}