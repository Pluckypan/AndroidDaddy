package com.zl.pattern.command;

public class RightCommand implements Command{

	//持有一个接受推箱子游戏对象的引用
	private PushBox pushBox;
		
	public RightCommand(PushBox pushBox){
		this.pushBox = pushBox;
	}
	
	@Override
	public void execute() {
		//调用具体命令
		pushBox.toRight();
	}
	
	@Override
	public void getCommand() {
		System.out.print("向右-->");
	}
}

