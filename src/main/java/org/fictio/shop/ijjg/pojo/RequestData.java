package org.fictio.shop.ijjg.pojo;

/**
 * 接受请求的格式
 * @author dk
 * @param <T>
 *
 */
public class RequestData<T> {
	
	private String token;
	private String action;
	private T body;
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
}
