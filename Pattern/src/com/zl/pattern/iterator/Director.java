package com.zl.pattern.iterator;

public class Director extends Leader{

	@Override
	public int limit() {
		return 5000;
	}

	@Override
	public void handle(int money) {
		System.out.println("������������"+ money +"Ԫ");
	}

	@Override
	public String getLeader() {
		return "��ǰ������";
	}

}

