package com.zl.pattern.memorandum;

/**
 * 
 * 简单模拟“使命召唤”游戏 
 * 
 */

public class CallOfDuty {
	
	private int mCheckpoint = 1;
	
	private int mLiftValue = 100;
	
	private String mWeapon = "沙漠之鹰";
	
	//玩游戏
	public void play(){
		System.out.println("打游戏："+String.format("第%d关", mCheckpoint) + "奋战杀敌中");
		mLiftValue -= 10;
		System.out.println("进度升级了");
		mCheckpoint++;
		System.out.println("到达" + String.format("第%d关", mCheckpoint));
	}
	
	//退出游戏
	public void quit(){
		System.out.println("--------------");
		System.out.println("退出前的游戏属性：" + this.toString());
		System.out.println("退出游戏");
		System.out.println("--------------");
	}
	
	/**
	 *创建备忘录 
	 */
	public Memento createMemento(){
		Memento memento = new Memento();
		memento.mCheckpoint = mCheckpoint;
		memento.mLiftValue = mLiftValue;
		memento.mWeapon = mWeapon;
		return memento;
	}
	
	//恢复游戏
	public void restore(Memento memento){
		this.mCheckpoint = memento.mCheckpoint;
		this.mLiftValue = memento.mLiftValue;
		this.mWeapon = memento.mWeapon;
		System.out.println("恢复后的游戏属性：" + this.toString());
	}
	
	@Override
	public String toString() {
		return "CallOfDuty [mCheckpoint=" + mCheckpoint + ",mLiftValue="
				+ mLiftValue + ",mWeapon=" + mWeapon + "]";
	}
}
