package com.zl.pattern.template;

/**
 * Windowsϵͳ����
 */
public class WindowsComputer extends AbstractComputer{

	@Override
	protected void powerOn() {
		System.out.println("Windows���Կ�����Դ");
	}

	@Override
	protected void checkHardware() {
		System.out.println("Windows���Լ��Ӳ��");
	}

	@Override
	protected void loadOS() {
		System.out.println("Windows�����������ϵͳ");
	}

	@Override
	protected void login() {
		
	}

	@Override
	protected boolean isLogin() {
		return false;//����false�������¼
	}

}
