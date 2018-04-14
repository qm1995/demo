package com.dragonsoftbravo.util;



/**
 * 用于和前台交互的工具类
 * @author DBC
 *
 */
public class ActionResult {
	
	private Integer statusCode;
	
	private String message;
	
	private Object result;

	private ActionResult(Integer state, String message, Object result) {
		super();
		this.statusCode = state;
		this.message = message;
		this.result = result;
	}
	
	public static ActionResult Ok(Object result) {
		return new ActionResult(200, "success", result);
	}
	
	public static ActionResult build(Integer state,String message) {
		return new ActionResult(state, message, null);
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
	
	
}
