package com.zl.pattern.cursor;


public class CompanyHui implements Company{

	private Employee[] array = new Employee[3];
	
	public CompanyHui(){
		array[0] = new Employee("»Ô¸ç", 28, "ÄĞ", "³ÌĞòÔ³");
		array[1] = new Employee("Ğ¡ºì", 23, "ÄĞ", "³ÌĞòÔ³");
		array[2] = new Employee("Ğ¡»Ô", 25, "ÄĞ", "³ÌĞòÔ³");
	}

	public Employee[] getEmployees(){
		return array;
	}

	@Override
	public Iterator iterator() {
		return new HuiIterator(array);
	}
}
