package com.zl.pattern.strategy;

public class PrimaryMemberStrategy implements MemberStrategy{
	/**
     * ������Ա�ۿ�
     */
	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("���ڳ�����Ա��û���ۿ�");
        return booksPrice;
	}
}
