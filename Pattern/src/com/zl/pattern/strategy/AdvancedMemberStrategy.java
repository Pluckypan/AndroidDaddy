package com.zl.pattern.strategy;

public class AdvancedMemberStrategy implements MemberStrategy{
	/**
     * 高级会员折扣
     */
	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("对于高级会员的折扣为20%");
        return booksPrice * 0.8;
	}
}
