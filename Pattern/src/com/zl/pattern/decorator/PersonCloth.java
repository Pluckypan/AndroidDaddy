package com.zl.pattern.decorator;

public class PersonCloth extends Person{
	
	protected Person mPerson; //保持一个Person类的引用
	
	public PersonCloth(Person mPerson) {
		super();
		this.mPerson = mPerson;
	}

	@Override
	public void dressed() {
		mPerson.dressed();
	}
}
