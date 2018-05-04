package com.zl.pattern.bridge;

public class LargeCoffee extends Coffee{

	public LargeCoffee(CoffeeAdditives impl) {
		super(impl);
	}

	@Override
	public void makeCoffee() {
		System.out.println("´ó±­µÄ" + impl.addSomething() + "¿§·È");
	}

}
