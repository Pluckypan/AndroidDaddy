package com.zl.pattern.cursor;

import java.util.List;


public class MinIterator implements Iterator{

	private List<Employee> list;
	private int position;
	
	public MinIterator(List<Employee> list){
		this.list = list;
	}
	
	
	@Override
	public boolean hasNext() {
		return !(position > list.size() - 1 || list.get(position) == null);
	}

	@Override
	public Object next() {
		Employee e = list.get(position);
		position++;
		return e;
	}

}
