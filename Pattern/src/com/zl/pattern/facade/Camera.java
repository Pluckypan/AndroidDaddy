package com.zl.pattern.facade;

public interface Camera {

	//打开相机
	public void open();
		
	//拍照
	public void takePicture();
	
	//关闭相机
	public void close();
}
