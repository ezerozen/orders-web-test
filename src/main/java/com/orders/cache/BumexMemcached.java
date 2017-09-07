package com.orders.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class BumexMemcached {
	
	private Map<String,Object> cache = new HashMap<String,Object>();
	
	public void set(String key, Object value){
		this.cache.put(key, value);
	}
	
	public Object get(String key){
		return this.cache.get(key);
	}
	
	public void delete(String key){
		this.cache.remove(key);
	}
}
