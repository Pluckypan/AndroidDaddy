package com.zl.pattern.bridge;

public class Client {

	public static void main(String[] args) {
		//‘≠Œ∂
		Ordinary implOrdinary = new Ordinary();
		
		//º”Ã«
		Sugar implSugar = new Sugar();
		
		//¥Û±≠øß∑»  ‘≠Œ∂
		LargeCoffee largeCoffeeOrdinary = new LargeCoffee(implOrdinary);
		largeCoffeeOrdinary.makeCoffee();
		
		//–°±≠øß∑»  ‘≠Œ∂
		SmallCoffee smallCoffeeOrdinary = new SmallCoffee(implOrdinary);
		smallCoffeeOrdinary.makeCoffee();
				
		//¥Û±≠øß∑»  º”Ã«
		LargeCoffee largeCoffeeSugar = new LargeCoffee(implSugar);
		largeCoffeeSugar.makeCoffee();
		
		//–°±≠øß∑»  º”Ã«
		SmallCoffee smallCoffeeSugar = new SmallCoffee(implSugar);
		smallCoffeeSugar.makeCoffee();
	}

}
