package org.fictio.shop.ijjg.pojo;


public class SignUser {
	
	private String username;
	private String usermobile;
	private String password;
	private String remeberMe;
	private String code;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemeberMe() {
		return remeberMe;
	}
	public void setRemeberMe(String remeberMe) {
		this.remeberMe = remeberMe;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsermobile() {
		return usermobile;
	}
	public void setUsermobile(String usermobile) {
		this.usermobile = usermobile;
	}
	@Override
	public String toString() {
		return "SignUser [username=" + username + ", usermobile=" + usermobile + ", password=" + password
				+ ", remeberMe=" + remeberMe + ", code=" + code + "]";
	}
}
