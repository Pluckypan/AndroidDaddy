package com.zl.pattern.abfactory;

public abstract class CarFactory {
	/**
	 * ������̥
	 * 
	 * @return ��̥
	 * */
	public abstract ITire createTire();
	
	/**
	 * ����������
	 * 
	 * @return ������
	 * */
	public abstract IEngine createEngine();
	
	/**
	 * �����ƶ�ϵͳ
	 * 
	 * @return �ƶ�ϵͳ
	 * */
	public abstract IBrake createBrake();
	
}
