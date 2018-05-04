package com.zl.pattern.cursor;

public interface Iterator {

	/**
	 * 是否还有下一个元素 
	 * 
	 * @return true表示有，false表示没有
	 */
	boolean hasNext();
	
	/**
	 * 返回当前元素，并将位置移至下一位
	 */
	Object next();
}
