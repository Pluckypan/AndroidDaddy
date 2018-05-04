package com.zl.pattern.template;

/**
 * Macϵͳ����
 */
public class MacComputer extends AbstractComputer{

	@Override
	protected void powerOn() {
		System.out.println("Mac���Կ�����Դ");
	}

	@Override
	protected void checkHardware() {
		System.out.println("Mac���Լ��Ӳ��");
	}

	@Override
	protected void loadOS() {
		System.out.println("Mac�����������ϵͳ");
	}

	@Override
	protected void login() {
		System.out.println("Mac���Ե�¼");
	}

}
