package com.zl.pattern.template;

/**
 * Windows系统电脑
 */
public class WindowsComputer extends AbstractComputer{

	@Override
	protected void powerOn() {
		System.out.println("Windows电脑开启电源");
	}

	@Override
	protected void checkHardware() {
		System.out.println("Windows电脑检查硬件");
	}

	@Override
	protected void loadOS() {
		System.out.println("Windows电脑载入操作系统");
	}

	@Override
	protected void login() {
		
	}

	@Override
	protected boolean isLogin() {
		return false;//返回false，不需登录
	}

}
