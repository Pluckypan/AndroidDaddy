package com.zl.pattern.visitor;

import java.util.LinkedList;
import java.util.List;

//员工业务报表类（ObjectStructure）
public class BusinessReport {

	List<Staff> mStaffs = new LinkedList<Staff>();

	public BusinessReport() {
		mStaffs.add(new Manager("王经理"));
		mStaffs.add(new Engineer("攻城狮-A"));
		mStaffs.add(new Engineer("攻城狮-B"));
		mStaffs.add(new Manager("李经理"));
		mStaffs.add(new Engineer("攻城狮-C"));
	}
	
	/**
	 * 为访问者展示报表 
	 * @param visitor 如CEO、CTO
	 */
	public void showReport(Visitor visitor){
		for(Staff staff : mStaffs){
			staff.accept(visitor);
		}
	}
}
