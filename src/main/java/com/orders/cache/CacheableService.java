package com.orders.cache;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.orders.model.Cacheable;

@Component
public class CacheableService<T extends Cacheable> {

	private BumexMemcached memcached;
	
	@Autowired
	public CacheableService(BumexMemcached memcached) {
		this.memcached = memcached;
	}
	
	public void set(T obj,Consumer<T> cons){
		cons.accept(obj);
		this.memcached.set(((Cacheable) obj).getKey(), obj);
	}
	
	@SuppressWarnings("unchecked")
	public T get(String key,Supplier<T> supp){
		return (T) Optional.ofNullable(this.memcached.get(key)).orElseGet(supp);
	}
	
	public void delete(T obj,Consumer<T> cons){
		cons.accept(obj);
		this.memcached.delete(((Cacheable) obj).getKey());
	}
	
}
