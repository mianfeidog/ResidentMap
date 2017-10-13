package com.ydl.residentmap.model.vo;

import com.ydl.residentmap.model.User;

public class UserVo extends User{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5704164568507414998L;
	//验证码
	private String validatecode;

	public String getValidatecode() {
		return validatecode;
	}

	public void setValidatecode(String validatecode) {
		this.validatecode = validatecode;
	}
	
	
}
