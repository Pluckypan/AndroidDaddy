package com.zl.pattern.template;

/**
 * Mac系统电脑
 */
public class MacComputer extends AbstractComputer{

	@Override
	protected void powerOn() {
		System.out.println("Mac电脑开启电源");
	}

	@Override
	protected void checkHardware() {
		System.out.println("Mac电脑检查硬件");
	}

	@Override
	protected void loadOS() {
		System.out.println("Mac电脑载入操作系统");
	}

	@Override
	protected void login() {
		System.out.println("Mac电脑登录");
	}

}
