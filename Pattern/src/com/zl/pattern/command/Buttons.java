package com.zl.pattern.command;

import java.util.ArrayList;

public class Buttons {

	private LeftCommand leftCommand; //�����ƶ��������������
	private RightCommand rightCommand; //�����ƶ��������������
	private UpCommand upCommand; //�����ƶ��������������
	private DownCommand downCommand; //�����ƶ��������������
	private RevokeCommand revokeCommand; //���������������
	
	private ArrayList<Command> commandList = new ArrayList<Command>();//���ڼ�¼�����
	
	/**
	 * ��ȡִ������
	 */
	public void getCommandList(){
		for(Command c : commandList){
			c.getCommand();
		}
		System.out.println("");
	}
	
	/**
	 * ���������ƶ����������
	 * 
	 * @param leftCommand �����ƶ����������
	 */
	public void setLeftCommand(LeftCommand leftCommand){
		this.leftCommand = leftCommand;
	}
	
	/**
	 * ���������ƶ����������
	 * 
	 * @param rightCommand �����ƶ����������
	 */
	public void setRightCommand(RightCommand rightCommand){
		this.rightCommand = rightCommand;
	}
	
	/**
	 * ���������ƶ����������
	 * 
	 * @param upCommand �����ƶ����������
	 */
	public void setUpCommand(UpCommand upCommand){
		this.upCommand = upCommand;
	}
	
	/**
	 * ���������ƶ����������
	 * 
	 * @param downCommand �����ƶ����������
	 */
	public void setDownCommand(DownCommand downCommand){
		this.downCommand = downCommand;
	}
	
	/**
	 * ���ó����������
	 * 
	 * @param revokeCommand �����������
	 */
	public void setRevokeCommand(RevokeCommand revokeCommand){
		this.revokeCommand = revokeCommand;
	}
	
	/**
	 * ��������ť 
	 */
	public void toLeft(){
		leftCommand.execute();
		commandList.add(leftCommand);
	}
	
	/**
	 * �������Ұ�ť 
	 */
	public void toRight(){
		rightCommand.execute();
		commandList.add(rightCommand);
	}
	
	/**
	 * �������ϰ�ť 
	 */
	public void toUp(){
		upCommand.execute();
		commandList.add(upCommand);
	}
	
	/**
	 * �������°�ť 
	 */
	public void toDown(){
		downCommand.execute();
		commandList.add(downCommand);
	}
	
	/**
	 * ���³�����ť 
	 */
	public void toRevoke(){
		revokeCommand.execute();
		commandList.remove(commandList.size()-1);
	}
}
