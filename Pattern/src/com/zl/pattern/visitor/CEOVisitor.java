package com.zl.pattern.visitor;

public class CEOVisitor implements Visitor {

	@Override
	public void visit(Engineer engineer) {
		System.out.println("����ʨ��" + engineer.name + ", KPI:" + engineer.kpi);
	}

	@Override
	public void visit(Manager manager) {
		System.out.println("����" + manager.name + ", KPI:" + manager.kpi
				+ ", �²�Ʒ���� ��" + manager.getProducts());
	}

}
