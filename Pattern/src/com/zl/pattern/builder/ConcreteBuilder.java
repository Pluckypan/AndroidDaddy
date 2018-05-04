package com.zl.pattern.builder;

public class ConcreteBuilder implements Builder {

	Part partA, partB, partC;
	
	@Override
	public void buildPartA() {
		//�����Ǿ�����ι���partA�Ĵ���
		System.out.println("����partA");
	}

	@Override
	public void buildPartB() {
		//�����Ǿ�����ι���partB�Ĵ���
		System.out.println("����partB");
	}

	@Override
	public void buildPartC() {
		//�����Ǿ�����ι���partC�Ĵ���
		System.out.println("����partC");
	}

	@Override
	public Product getResult() {
		//���������װ��Ʒ���
		return null;
	} 
	
}
