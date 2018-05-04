package com.zl.pattern.command;

public class Client {

	public static void main(String[] args) {
		//首先创建游戏
		PushBox pushBox = new PushBox();
		
		//根据游戏构造5种命令
		LeftCommand leftCommand = new LeftCommand(pushBox);
		RightCommand rightCommand = new RightCommand(pushBox);
		UpCommand upCommand = new UpCommand(pushBox);
		DownCommand downCommand = new DownCommand(pushBox);
		RevokeCommand revokeCommand = new RevokeCommand(pushBox);
		
		//按钮可以执行不同命令
		Buttons buttons = new Buttons();
		buttons.setLeftCommand(leftCommand);
		buttons.setRightCommand(rightCommand);
		buttons.setUpCommand(upCommand);
		buttons.setDownCommand(downCommand);
		buttons.setRevokeCommand(revokeCommand);
		
		//执行操作
		buttons.toLeft();
		buttons.toDown();
		buttons.toDown();
		buttons.toRight();
		buttons.getCommandList();
		buttons.toRevoke();
		buttons.toUp();
		buttons.toLeft();
		buttons.toDown();
		buttons.toUp();
		buttons.getCommandList();
	}
}
