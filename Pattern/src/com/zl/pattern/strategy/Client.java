package com.zl.pattern.strategy;

public class Client {

    public static void main(String[] args) {
        //ѡ�񲢴�����Ҫʹ�õĲ��Զ���
        MemberStrategy strategy1 = new AdvancedMemberStrategy();
        //��������
        Price price = new Price(strategy1);
        //����۸�
        double quote = price.quote(300);
        System.out.println("ͼ������ռ۸�Ϊ��" + quote);
    }

}