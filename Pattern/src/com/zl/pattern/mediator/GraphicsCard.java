package com.zl.pattern.mediator;

public class GraphicsCard extends Colleague{

	public GraphicsCard(Mediator mediator) {
		super(mediator);
	}

	/**
	 * 播放视频数据
	 * 
	 * @param 视频数据
	 */
	public void videoPlay(String data){
		System.out.println("视频：" + data);
	}
}
