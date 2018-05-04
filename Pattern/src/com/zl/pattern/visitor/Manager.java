package com.zl.pattern.visitor;

import java.util.Random;

/**
 * ����
 */
public class Manager extends Staff{

	private int products;//��Ʒ����
	
	public Manager(String name) {
		super(name);
		products = new Random().nextInt(10);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	//һ�������Ĳ�Ʒ����
	public int getProducts(){
		return products;
	}
}
