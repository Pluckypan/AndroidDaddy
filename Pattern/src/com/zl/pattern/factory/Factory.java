package com.zl.pattern.factory;

public abstract class Factory {
	/**
	 * 抽象工厂方法
	 * 具体由子类实现
	 * 
	 * @param clz 产品对象类类型
	 * 
	 * @return 具体的产品对象
	 * */
	public abstract <T extends Product> T createProduct(Class<T> clz);
}
