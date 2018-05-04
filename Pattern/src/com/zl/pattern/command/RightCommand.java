package com.zl.pattern.command;

public class RightCommand implements Command{

	//����һ��������������Ϸ���������
	private PushBox pushBox;
		
	public RightCommand(PushBox pushBox){
		this.pushBox = pushBox;
	}
	
	@Override
	public void execute() {
		//���þ�������
		pushBox.toRight();
	}
	
	@Override
	public void getCommand() {
		System.out.print("����-->");
	}
}

