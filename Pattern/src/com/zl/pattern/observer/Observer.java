package com.zl.pattern.observer;

/**
 *  抽象观察者类，为所有具体观察者定义一个接口，在得到通知时更新自己
 */
public interface Observer {
	/**
	 *  有更新
	 *  
	 *  @param message 消息
	 */
	public void update(String message);
	
}