package com.zl.pattern.observer;

/**
 * 具体的观察者类，也就是订阅者
 */
public class User implements Observer {

	@Override
	public void update(String message) {
		System.out.println(name + "," + message + "更新了！");

	}

	// 订阅者的名字
	private String name;

	public User(String name) {
		this.name = name;
	}
}
