package com.zl.pattern.visitor;

public interface Visitor {
	/**
	 * ���ʹ���ʨ����
	 */
	public void visit(Engineer engineer);
	
	/**
	 * ���ʾ�������
	 */
	public void visit(Manager manager);
}
