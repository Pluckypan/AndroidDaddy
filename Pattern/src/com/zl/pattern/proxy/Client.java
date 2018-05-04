package com.zl.pattern.proxy;

import java.lang.reflect.Proxy;


public class Client {
	public static void main(String[] args) {
		//�����������С��
		ILawsuit xiaomin = new XiaoMin();
		
		//1.��̬����
		//����һ��������ʦ������С�񴫵ݽ�ȥ
		//ILawsuit lawyer = new Lawyer(xiaomin);
		
		//--------------------------------------
		//2.��̬����
		//����һ����̬����
		DynamicPorxy proxy = new DynamicPorxy(xiaomin);
		
		//��ȡ��������С���ClassLoader
		ClassLoader loader = xiaomin.getClass().getClassLoader();
		
		//��̬����һ����������ʦ
		ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader, new Class[]{ ILawsuit.class }, proxy);
		
		//��ʦ�ύ����
		lawyer.submit();
		
		//��ʦ���о�֤
		lawyer.burden();
		
		//��ʦ��С��绤
		lawyer.defend();
		
		//�������
		lawyer.finish();
	}
}
