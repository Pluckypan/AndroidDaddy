package com.zl.pattern.cursor;

public interface Iterator {

	/**
	 * �Ƿ�����һ��Ԫ�� 
	 * 
	 * @return true��ʾ�У�false��ʾû��
	 */
	boolean hasNext();
	
	/**
	 * ���ص�ǰԪ�أ�����λ��������һλ
	 */
	Object next();
}
