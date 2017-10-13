package com.ydl.residentmap.model;

public class ResponseResult {

	private String code;
	private Object data;
	private String desc;
	private String error;
	private String error_description;
	
	public static ResponseResult create(String status, Object data, String desc, String error, String error_description) {
		return new ResponseResult(status, data, desc, error, error_description);
	}

	private ResponseResult(String code, Object data, String desc, String error, String error_description) {
		super();
		this.code = code;
		this.data = data;
		this.desc = desc;
		this.error = error;
		this.error_description = error_description;
	}

	public ResponseResult() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getError_description() {
		return error_description;
	}

	public void setError_description(String error_description) {
		this.error_description = error_description;
	}
	
}
