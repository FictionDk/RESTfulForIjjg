package org.fictio.shop.ijjg.common;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StringUtils;

public class DefaultTokenManager implements TokenManager {
	
	private static Map<String,String> tokenMap = new ConcurrentHashMap<String,String>();

	@Override
	public String createToken(String username) {
		String token = UUID.randomUUID().toString();
		int i = 0;
		if(checkToken(token)){
			token = createToken(username+i);
			i++;
		}else{
			tokenMap.put(token, username);
		}
		return token;
	}

	@Override
	public boolean checkToken(String token) {
		return !StringUtils.isEmpty(token) && tokenMap.containsKey(token);
	}
	
	public String getUsername(String token){
		return tokenMap.get(token);
	}
	
	

}
