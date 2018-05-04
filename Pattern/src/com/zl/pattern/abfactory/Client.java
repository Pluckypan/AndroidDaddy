package com.zl.pattern.abfactory;

public class Client {
	public static void main(String[] args) {
		//A����
		CarFactory factoryA = new AFactory();
		factoryA.createTire().tire();
		factoryA.createEngine().engine();
		factoryA.createBrake().brake();
		System.out.println("---------------");
		//B����
		CarFactory factoryB = new BFactory();
		factoryB.createTire().tire();
		factoryB.createEngine().engine();
		factoryB.createBrake().brake();
	}
}
