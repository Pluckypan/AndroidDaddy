package com.zl.pattern.cursor;


public class Boss {
	public static void main(String[] args) {
		CompanyHui hui = new CompanyHui();
		check(hui.iterator());
		
		CompanyMin min = new CompanyMin();
		check(min.iterator());
	}
	
	private static void check(Iterator iterator){
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
	}
}
