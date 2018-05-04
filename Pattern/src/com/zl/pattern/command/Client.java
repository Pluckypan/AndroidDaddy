package com.zl.pattern.command;

public class Client {

	public static void main(String[] args) {
		//���ȴ�����Ϸ
		PushBox pushBox = new PushBox();
		
		//������Ϸ����5������
		LeftCommand leftCommand = new LeftCommand(pushBox);
		RightCommand rightCommand = new RightCommand(pushBox);
		UpCommand upCommand = new UpCommand(pushBox);
		DownCommand downCommand = new DownCommand(pushBox);
		RevokeCommand revokeCommand = new RevokeCommand(pushBox);
		
		//��ť����ִ�в�ͬ����
		Buttons buttons = new Buttons();
		buttons.setLeftCommand(leftCommand);
		buttons.setRightCommand(rightCommand);
		buttons.setUpCommand(upCommand);
		buttons.setDownCommand(downCommand);
		buttons.setRevokeCommand(revokeCommand);
		
		//ִ�в���
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
