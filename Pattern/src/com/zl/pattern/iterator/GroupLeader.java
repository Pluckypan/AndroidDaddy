package com.zl.pattern.iterator;

public class GroupLeader extends Leader{

	@Override
	public int limit() {
		return 1000;
	}

	@Override
	public void handle(int money) {
		System.out.println("�鳤��������"+ money +"Ԫ");
	}

	@Override
	public String getLeader() {
		return "��ǰ���鳤";
	}

} 
