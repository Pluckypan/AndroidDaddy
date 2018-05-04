package com.zl.pattern.facade;

public class PhoneImpl implements Phone{

	@Override
	public void dail() {
		System.out.println("´òµç»°");
	}

	@Override
	public void hangup() {
		System.out.println("¹Ò¶Ï");
	}

}
