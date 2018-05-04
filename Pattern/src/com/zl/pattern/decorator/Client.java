package com.zl.pattern.decorator;

public class Client {
	public static void main(String[] args) {
		//首先有一个男孩
		Person person = new Boy();
		
		//在家
		PersonCloth personCloth = new HomeCloth(person);
		personCloth.dressed();
		System.out.println("--------------");
		//出门
		PersonCloth personCloth1 = new OutsideCloth(person);
		personCloth1.dressed();
		
	}
}
