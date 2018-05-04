package com.zl.pattern.visitor;

import java.util.Random;

/**
 * ����ʨ 
 */
public class Engineer extends Staff{

	private int codeLines;//��������
	
	public Engineer(String name) {
		super(name);
		codeLines = new Random().nextInt(10 * 10000);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);
	}

	//����ʨ��һ��д�Ĵ�������
	public int getCodeLines(){
		return codeLines;
	}
}
