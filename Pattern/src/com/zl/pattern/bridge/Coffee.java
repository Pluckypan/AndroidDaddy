package com.zl.pattern.bridge;

public abstract class Coffee{
	
	protected CoffeeAdditives impl;

	public Coffee(CoffeeAdditives impl) {
		this.impl = impl;
	}
	
	/**
	 * 咖啡具体什么样由子类决定 
	 */
	public abstract void makeCoffee();
}