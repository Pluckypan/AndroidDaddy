package com.zl.pattern.visitor;

import java.util.LinkedList;
import java.util.List;

//Ա��ҵ�񱨱��ࣨObjectStructure��
public class BusinessReport {

	List<Staff> mStaffs = new LinkedList<Staff>();

	public BusinessReport() {
		mStaffs.add(new Manager("������"));
		mStaffs.add(new Engineer("����ʨ-A"));
		mStaffs.add(new Engineer("����ʨ-B"));
		mStaffs.add(new Manager("���"));
		mStaffs.add(new Engineer("����ʨ-C"));
	}
	
	/**
	 * Ϊ������չʾ���� 
	 * @param visitor ��CEO��CTO
	 */
	public void showReport(Visitor visitor){
		for(Staff staff : mStaffs){
			staff.accept(visitor);
		}
	}
}
