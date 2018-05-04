package com.zl.pattern.command;

public class DownCommand implements Command{

	//����һ��������������Ϸ���������
	private PushBox pushBox;
	
	public DownCommand(PushBox pushBox){
		this.pushBox = pushBox;
	}
	
	@Override
	public void execute() {
		//���þ�������
		pushBox.toDown();
	}

	@Override
	public void getCommand() {
		System.out.print("����-->");
	}
}

