package com.zl.pattern.decorator;

public class OutsideCloth extends PersonCloth{

	public OutsideCloth(Person mPerson) {
		super(mPerson);
	}

	/**
	 * ´©¶ÌÐä 
	 */
	private void dressShirt(){
		System.out.println("´©¼þ¶ÌÐä");
	}
	
	/**
	 * ´©Å£×Ð¿ã 
	 */
	private void dressJean(){
		System.out.println("´©Å£×Ð¿ã");
	}
	
	/**
	 * ´©Ð¬×Ó 
	 */
	private void dressShoes(){
		System.out.println("´©Ð¬×Ó ");
	}
	
	@Override
	public void dressed() {
		super.dressed();
		dressShirt();
		dressJean();
		dressShoes();
	}
	
}
