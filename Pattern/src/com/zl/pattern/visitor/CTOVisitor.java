package com.zl.pattern.visitor;

public class CTOVisitor implements Visitor {

	@Override
	public void visit(Engineer engineer) {
		System.out.println("����ʨ��" + engineer.name + ", ��������:" + engineer.getCodeLines());
	}

	@Override
	public void visit(Manager manager) {
		System.out.println("����" + manager.name +", ��Ʒ���� ��" + manager.getProducts());
	}

}

