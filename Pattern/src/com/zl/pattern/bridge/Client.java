package com.zl.pattern.bridge;

public class Client {

	public static void main(String[] args) {
		//ԭζ
		Ordinary implOrdinary = new Ordinary();
		
		//����
		Sugar implSugar = new Sugar();
		
		//�󱭿���  ԭζ
		LargeCoffee largeCoffeeOrdinary = new LargeCoffee(implOrdinary);
		largeCoffeeOrdinary.makeCoffee();
		
		//С������  ԭζ
		SmallCoffee smallCoffeeOrdinary = new SmallCoffee(implOrdinary);
		smallCoffeeOrdinary.makeCoffee();
				
		//�󱭿���  ����
		LargeCoffee largeCoffeeSugar = new LargeCoffee(implSugar);
		largeCoffeeSugar.makeCoffee();
		
		//С������  ����
		SmallCoffee smallCoffeeSugar = new SmallCoffee(implSugar);
		smallCoffeeSugar.makeCoffee();
	}

}
