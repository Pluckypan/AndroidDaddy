package com.zl.pattern.builder;

public class Client {
	public static void main(String[] args) {
		ConcreteBuilder builder = new ConcreteBuilder();
		Director director = new Director( builder ); 

		director.construct(); 
		//Product product = builder.getResult();
		
	}
}
