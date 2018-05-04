package com.zl.pattern.abfactory;

public class Client {
	public static void main(String[] args) {
		//A车厂
		CarFactory factoryA = new AFactory();
		factoryA.createTire().tire();
		factoryA.createEngine().engine();
		factoryA.createBrake().brake();
		System.out.println("---------------");
		//B车厂
		CarFactory factoryB = new BFactory();
		factoryB.createTire().tire();
		factoryB.createEngine().engine();
		factoryB.createBrake().brake();
	}
}
