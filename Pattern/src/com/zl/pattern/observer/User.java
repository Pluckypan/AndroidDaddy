package com.zl.pattern.observer;

/**
 * ����Ĺ۲����࣬Ҳ���Ƕ�����
 */
public class User implements Observer {

	@Override
	public void update(String message) {
		System.out.println(name + "," + message + "�����ˣ�");

	}

	// �����ߵ�����
	private String name;

	public User(String name) {
		this.name = name;
	}
}
