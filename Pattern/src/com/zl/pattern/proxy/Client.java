package com.zl.pattern.proxy;

import java.lang.reflect.Proxy;


public class Client {
	public static void main(String[] args) {
		//构造出诉讼人小民
		ILawsuit xiaomin = new XiaoMin();
		
		//1.静态代理
		//构造一个代理律师，并将小民传递进去
		//ILawsuit lawyer = new Lawyer(xiaomin);
		
		//--------------------------------------
		//2.动态代理
		//构造一个动态代理
		DynamicPorxy proxy = new DynamicPorxy(xiaomin);
		
		//获取被代理类小民的ClassLoader
		ClassLoader loader = xiaomin.getClass().getClassLoader();
		
		//动态构造一个代理者律师
		ILawsuit lawyer = (ILawsuit) Proxy.newProxyInstance(loader, new Class[]{ ILawsuit.class }, proxy);
		
		//律师提交申请
		lawyer.submit();
		
		//律师进行举证
		lawyer.burden();
		
		//律师代小民辩护
		lawyer.defend();
		
		//完成诉讼
		lawyer.finish();
	}
}
