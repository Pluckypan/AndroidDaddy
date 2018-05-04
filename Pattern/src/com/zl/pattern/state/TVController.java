package com.zl.pattern.state;

/**
 * 电视遥控器
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
		System.out.println("开机了");
	}

	@Override
	public void powerOff() {
		setTVState(new PowerOffState());
		System.out.println("关机了");
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
