package org.fictio.shop.ijjg.common;

import java.util.concurrent.ConcurrentHashMap;

public class CacheManager {
	
	private static CacheManager instance = new CacheManager();
	private static ConcurrentHashMap<String,String> cacheMap = new ConcurrentHashMap<>(); 
	
	private CacheManager(){};
	public static CacheManager getInstance(){
		return instance;
	}
	
	public boolean hasCache(String key){
		return cacheMap.containsKey(key);
	}
	
	public void setValue(String key,String value){
		cacheMap.put(key, value);
	}
	
	public String getValue(String key){
		return cacheMap.get(key);
	}
	
	public void clearCache(){
		cacheMap.clear();
	}
	
	public void cleanValue(String key){
		cacheMap.remove(key);
	}

}
