package com.zl.pattern.strategy;

public class AdvancedMemberStrategy implements MemberStrategy{
	/**
     * �߼���Ա�ۿ�
     */
	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("���ڸ߼���Ա���ۿ�Ϊ20%");
        return booksPrice * 0.8;
	}
}
