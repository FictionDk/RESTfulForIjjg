package org.fictio.shop.ijjg.common;

import java.util.UUID;

import org.springframework.util.StringUtils;

public class DefaultTokenManager implements TokenManager {
	
	@Override
	public String createToken(String username) {
		String token = UUID.randomUUID().toString();
		CacheManager.getInstance().setValue(token, username);
		return token;
	}

	@Override
	public boolean checkToken(String token) {
		return !StringUtils.isEmpty(token) && CacheManager.getInstance().hasCache(token);
	}
	
	public String getUsername(String token){
		return CacheManager.getInstance().getValue(token);
	}

}
