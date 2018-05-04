package com.zl.pattern.abfactory;

public class SeniorBrake implements IBrake{
	@Override
	public void brake() {
		System.out.println("高级制动");
	}
}
