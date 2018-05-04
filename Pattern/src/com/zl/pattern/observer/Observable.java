package com.zl.pattern.observer;

/**
 * 抽象被观察者类
 */
public interface Observable {

	/**
	 * 推送消息
	 * 
	 * @param message 内容
	 */
	void push(String message);

	/**
	 * 订阅
	 * 
	 * @param observer 订阅者
	 */
	void register(Observer observer);
}
