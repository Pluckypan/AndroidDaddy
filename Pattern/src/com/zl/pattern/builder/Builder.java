package com.zl.pattern.builder;

public interface Builder {

	//��������A�������紴����������
	void buildPartA(); 

	//��������B   ���紴������������
	void buildPartB(); 

	//��������C   ���紴������������
	void buildPartC();

	//���������װ��Ʒ��� (�������װ��õ�����)
	Product getResult();

}