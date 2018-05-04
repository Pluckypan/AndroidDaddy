package com.zl.pattern.visitor;

public interface Visitor {
	/**
	 * 访问攻城狮类型
	 */
	public void visit(Engineer engineer);
	
	/**
	 * 访问经理类型
	 */
	public void visit(Manager manager);
}
