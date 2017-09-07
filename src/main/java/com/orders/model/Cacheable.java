package com.orders.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class Cacheable {

	@JsonIgnore
	public abstract String getKey();
	
}
