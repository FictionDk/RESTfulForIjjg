package org.fictio.shop.ijjg.common;

public interface TokenManager {
	String createToken(String username);
	boolean checkToken(String token);
}
