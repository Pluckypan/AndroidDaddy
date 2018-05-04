package com.zl.pattern.visitor;



public class Client {
	public static void main(String[] args) {
		//��������
		BusinessReport report = new BusinessReport();
		System.out.println("===== ��CEO������ =====");
		//���÷�����CEO
		report.showReport(new CEOVisitor());
		System.out.println("===== ��CTO������ =====");
		//���÷�����CTO
		report.showReport(new CTOVisitor());
	}
}
