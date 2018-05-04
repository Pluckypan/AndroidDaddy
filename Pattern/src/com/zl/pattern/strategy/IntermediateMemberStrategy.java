package com.zl.pattern.strategy;

public class IntermediateMemberStrategy implements MemberStrategy{
	/**
     * 中级会员折扣
     */
	@Override
	public double calcPrice(double booksPrice) {
		System.out.println("对于中级会员的折扣为10%");
        return booksPrice * 0.9;
	}
}