package com.zl.pattern.bridge;

public abstract class Coffee{
	
	protected CoffeeAdditives impl;

	public Coffee(CoffeeAdditives impl) {
		this.impl = impl;
	}
	
	/**
	 * ���Ⱦ���ʲô����������� 
	 */
	public abstract void makeCoffee();
}