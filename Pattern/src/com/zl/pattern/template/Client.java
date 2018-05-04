package com.zl.pattern.template;

public class Client {
	public static void main(String[] args) {
		AbstractComputer comp = new WindowsComputer();
		comp.startUp();
		
		comp = new MacComputer();
		comp.startUp();
	}
}
