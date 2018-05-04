package com.zl.pattern.explain;

public class Client {
	public static void main(String[] args) {
		Calculator c = new Calculator("22 + 553 + 83 + 5");
		System.out.println("¼ÆËã½á¹û£º"+c.calculate());
	}
}
