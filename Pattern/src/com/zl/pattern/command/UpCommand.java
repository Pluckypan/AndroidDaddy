package com.zl.pattern.command;

public class UpCommand implements Command{

	//����һ��������������Ϸ���������
	private PushBox pushBox;
		
	public UpCommand(PushBox pushBox){
		this.pushBox = pushBox;
	}
	
	@Override
	public void execute() {
		//���þ�������
		pushBox.toUp();
	}
	
	@Override
	public void getCommand() {
		System.out.print("����-->");
	}
}

