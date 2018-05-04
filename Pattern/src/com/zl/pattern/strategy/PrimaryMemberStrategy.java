package com.zl.pattern.strategy;

public class PrimaryMemberStrategy implements MemberStrategy{
	/**
     * 初级会员折扣
     */
	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("对于初级会员的没有折扣");
        return booksPrice;
	}
}
