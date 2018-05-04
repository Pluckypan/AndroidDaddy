package com.zl.pattern.visitor;

import java.util.Random;

/**
 * Ա�����ࣨElement�� 
 */
public abstract class Staff {
	//Ա������
	public String name;
	//Ա��KPI
	public int kpi;
	
	public Staff(String name) {
		super();
		this.name = name;
		this.kpi = new Random().nextInt(10);
	}
	//����Visitor�ķ���
	public abstract void accept(Visitor visitor);
	
}
