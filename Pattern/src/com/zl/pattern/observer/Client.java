package com.zl.pattern.observer;

public class Client {
	public static void main(String[] args) {
		//���۲��ߣ���������û����ĵĵ��Ӿ�
		Teleplay teleplay = new Teleplay();
		//�۲��ߣ�������Ƕ����û�
		User user1 = new User("С��");
		User user2 = new User("С��");
		User user3 = new User("С��");
		//����
		teleplay.register(user1);
		teleplay.register(user2);
		teleplay.register(user3);
		//��������Ϣ
		teleplay.push("xxx���Ӿ�");
	}
}
