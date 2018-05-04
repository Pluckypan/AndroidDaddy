package com.zl.pattern.bridge;

public class SmallCoffee extends Coffee{

	public SmallCoffee(CoffeeAdditives impl) {
		super(impl);
	}

	@Override
	public void makeCoffee() {
		System.out.println("Ð¡±­µÄ" + impl.addSomething() + "¿§·È");
	}
}
