package com.zl.pattern.command;

public class LeftCommand implements Command{

	//����һ��������������Ϸ���������
	private PushBox pushBox;
		
	public LeftCommand(PushBox pushBox){
		this.pushBox = pushBox;
	}
	
	@Override
	public void execute() {
		//���þ�������
		pushBox.toLeft();
	}
	
	@Override
	public void getCommand() {
		System.out.print("����-->");
	}
}
