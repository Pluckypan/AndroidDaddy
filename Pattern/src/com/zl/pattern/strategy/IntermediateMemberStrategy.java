package com.zl.pattern.strategy;

public class IntermediateMemberStrategy implements MemberStrategy{
	/**
     * �м���Ա�ۿ�
     */
	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("�����м���Ա���ۿ�Ϊ10%");
        return booksPrice * 0.9;
	}
}