package com.zl.pattern.builder;

//Director �࣬��������
public class Director {

	private Builder builder;

	public Director( Builder builder ) { 
		this.builder = builder; 
	}	// ������partA partB partC�����ɸ��Ӷ���

	// �����ǽ����� �����̺ͷ�������װ�������Ĺ���
	public void construct() { 
		
		builder.buildPartA();
	    builder.buildPartB();
		builder.buildPartC();

	}
}