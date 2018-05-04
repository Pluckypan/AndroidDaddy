package com.zl.pattern.mediator;

public class SoundCard extends Colleague{

	public SoundCard(Mediator mediator) {
		super(mediator);
	}

	/**
	 * 播放音频数据
	 * 
	 * @param 音频数据
	 */
	public void soundPlay(String data){
		System.out.println("音频：" + data);
	}
}
