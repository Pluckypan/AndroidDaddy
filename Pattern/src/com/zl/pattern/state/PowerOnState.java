package com.zl.pattern.state;

/**
 * 
 * ����״̬��������Ч
 * 
 * */
public class PowerOnState implements TVState{

	@Override
	public void nextChannel() {
		System.out.println("��һƵ��");
	}

	@Override
	public void prevChannel() {
		System.out.println("��һƵ��");
	}

	@Override
	public void turnUp() {
		System.out.println("��������");
	}

	@Override
	public void turnDown() {
		System.out.println("��������");
	}
}
