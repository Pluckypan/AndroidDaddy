package com.zl.pattern.observer;

/**
 *  ����۲����࣬Ϊ���о���۲��߶���һ���ӿڣ��ڵõ�֪ͨʱ�����Լ�
 */
public interface Observer {
	/**
	 *  �и���
	 *  
	 *  @param message ��Ϣ
	 */
	public void update(String message);
	
}