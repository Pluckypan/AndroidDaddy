package com.zl.pattern.state;

public class Client {
	public static void main(String[] args) {
		TVController tvController = new TVController();
		//���ÿ���״̬
		tvController.powerOn();
		//��һƵ��
		tvController.nextChannel();
		//��������
		tvController.turnUp();
		//�ػ�
		tvController.powerOff();
		//������������ʱ������Ч
		tvController.turnDown();
	}
}
