package com.zl.pattern.state;

/**
 * ����ң����
 * 
 * */
public class TVController implements PowerController{

	TVState mTVState;
	
	public void setTVState(TVState mTVState){
		this.mTVState = mTVState;
	}
	
	@Override
	public void powerOn() {
		setTVState(new PowerOnState());
		System.out.println("������");
	}

	@Override
	public void powerOff() {
		setTVState(new PowerOffState());
		System.out.println("�ػ���");
	}
	
	public void nextChannel(){
		mTVState.nextChannel();
	}
	
	public void prevChannel(){
		mTVState.prevChannel();
	}
	
	public void turnUp(){
		mTVState.turnUp();
	}
	
	public void turnDown(){
		mTVState.turnDown();
	}
}
