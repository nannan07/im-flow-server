package com.allmsi.flow.model.ovo;

public class FlowNodeButtonSimpleOVO {
	private String buttonName;

	private String buttonType;

	public FlowNodeButtonSimpleOVO() {
	}

	public FlowNodeButtonSimpleOVO(String buttonName, String buttonType) {
		this.buttonName = buttonName;
		this.buttonType = buttonType;
	}

	public String getButtonName() {
		return buttonName;
	}

	public void setButtonName(String buttonName) {
		this.buttonName = buttonName;
	}

	public String getButtonType() {
		return buttonType;
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

}
