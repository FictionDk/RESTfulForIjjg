package org.fictio.shop.ijjg.pojo;

public class ExceptionResult {
	
	private int resultCode;
	private String msg;
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "ExceptionResult [resultCode=" + resultCode + ", msg=" + msg + "]";
	}

}
