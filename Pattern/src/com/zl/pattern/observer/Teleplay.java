package com.zl.pattern.observer;

import java.util.ArrayList;
import java.util.List;


/**
 *  具体的被观察者类，也就是订阅的节目
 */
public class Teleplay implements Observable{

	private List<Observer> list = new ArrayList<Observer>();//储存订阅者
	
	@Override
	public void push(String message) {
		for(Observer observer:list){
			observer.update(message);
		}
	}

	@Override
	public void register(Observer observer) {
		list.add(observer);
	}

}
