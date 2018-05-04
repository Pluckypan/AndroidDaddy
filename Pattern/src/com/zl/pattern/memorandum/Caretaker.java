package com.zl.pattern.memorandum;

/**
 * Caretaker，负责管理Memento
 */
public class Caretaker {

	Memento mMemento; //备忘录
	
	/**
	 * 存档
	 */
	public void archive(Memento memento){
		this.mMemento = memento;
	}
	
	/**
	 * 获取存档
	 */
	public Memento getMemento(){
		return mMemento;
	}
}
