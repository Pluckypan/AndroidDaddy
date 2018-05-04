package com.zl.pattern.command;

public class PushBox {
	/**
	 * 执行向左命令 
	 */
	public void toLeft(){
		System.out.println("向左");
	}
	
	/**
	 * 执行向右命令 
	 */
	public void toRight(){
		System.out.println("向右");
	}
	
	/**
	 * 执行向下命令 
	 */
	public void toDown(){
		System.out.println("向下");
	}
	
	/**
	 * 执行向上命令 
	 */
	public void toUp(){
		System.out.println("向上");
	}
	
	/**
	 * 执行撤销命令 
	 */
	public void revoke(){
		System.out.println("撤销");
	}
}
