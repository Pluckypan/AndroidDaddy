package com.zl.pattern.template;

/**
 * ����� Computer
 */
public abstract class AbstractComputer {
	//�����ǻ���������������Ը���,�������ⲿֱ�ӵ�����Щ������������protected
	/**
     * ������Դ
     */
	protected abstract void powerOn();
	
	/**
     * ���Ӳ��
     */
	protected abstract void checkHardware();
	
	/**
     * �������ϵͳ
     */
	protected abstract void loadOS();
	
	/**
     * ��¼
     */
	protected abstract void login();
	
	//�����ǹ��ӷ�����������ʵ��
	/**
     * �Ƿ���Ҫ��¼
     * 
     * @return trueΪ��Ҫ��¼
     */
	protected boolean isLogin(){
        return true;
    }
	
	//������ģ�巽��������Ϊfinal�����಻�ܸ��Ǵ˷��� 
	/**
     * �������������������Ϊ������Դ��ϵͳ��顢����ϵͳ������Ƿ��¼��
     */
	public final void startUp(){
		System.out.println("--------���� START--------");
		powerOn();
		checkHardware();
		loadOS();
		if(isLogin()){
			login();
		}
		System.out.println("-------- ���� END --------");
	}
	
}
