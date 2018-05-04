package com.zl.pattern.observer;

import java.util.ArrayList;
import java.util.List;


/**
 *  ����ı��۲����࣬Ҳ���Ƕ��ĵĽ�Ŀ
 */
public class Teleplay implements Observable{

	private List<Observer> list = new ArrayList<Observer>();//���涩����
	
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
