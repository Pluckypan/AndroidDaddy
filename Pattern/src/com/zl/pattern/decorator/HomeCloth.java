package com.zl.pattern.decorator;

public class HomeCloth extends PersonCloth{

	public HomeCloth(Person mPerson) {
		super(mPerson);
	}

	/**
	 * ´©¶Ì¿ã
	 */
	private void dressShorts(){
		System.out.println("´©¶Ì¿ã");//ÔÚ¼ÒÀïËæ±ãµã
	}
	
	@Override
	public void dressed() {
		super.dressed();
		dressShorts();
	}
	
}
