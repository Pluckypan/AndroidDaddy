package com.zl.pattern.adapter;
//ÀàÊÊÅäÆ÷Ä£Ê½
public class VoltAdapter extends Volt220 implements FiveVolt{

	@Override
	public int getVolt5() {
		return 5;
	}
	
}
