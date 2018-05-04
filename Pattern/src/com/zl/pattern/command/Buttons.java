package com.zl.pattern.command;

import java.util.ArrayList;

public class Buttons {

	private LeftCommand leftCommand; //向左移动的命令对象引用
	private RightCommand rightCommand; //向右移动的命令对象引用
	private UpCommand upCommand; //向上移动的命令对象引用
	private DownCommand downCommand; //向下移动的命令对象引用
	private RevokeCommand revokeCommand; //撤销命令对象引用
	
	private ArrayList<Command> commandList = new ArrayList<Command>();//用于记录命令动作
	
	/**
	 * 获取执行命令
	 */
	public void getCommandList(){
		for(Command c : commandList){
			c.getCommand();
		}
		System.out.println("");
	}
	
	/**
	 * 设置向左移动的命令对象
	 * 
	 * @param leftCommand 向左移动的命令对象
	 */
	public void setLeftCommand(LeftCommand leftCommand){
		this.leftCommand = leftCommand;
	}
	
	/**
	 * 设置向右移动的命令对象
	 * 
	 * @param rightCommand 向右移动的命令对象
	 */
	public void setRightCommand(RightCommand rightCommand){
		this.rightCommand = rightCommand;
	}
	
	/**
	 * 设置向上移动的命令对象
	 * 
	 * @param upCommand 向上移动的命令对象
	 */
	public void setUpCommand(UpCommand upCommand){
		this.upCommand = upCommand;
	}
	
	/**
	 * 设置向下移动的命令对象
	 * 
	 * @param downCommand 向下移动的命令对象
	 */
	public void setDownCommand(DownCommand downCommand){
		this.downCommand = downCommand;
	}
	
	/**
	 * 设置撤销命令对象
	 * 
	 * @param revokeCommand 撤销命令对象
	 */
	public void setRevokeCommand(RevokeCommand revokeCommand){
		this.revokeCommand = revokeCommand;
	}
	
	/**
	 * 按下向左按钮 
	 */
	public void toLeft(){
		leftCommand.execute();
		commandList.add(leftCommand);
	}
	
	/**
	 * 按下向右按钮 
	 */
	public void toRight(){
		rightCommand.execute();
		commandList.add(rightCommand);
	}
	
	/**
	 * 按下向上按钮 
	 */
	public void toUp(){
		upCommand.execute();
		commandList.add(upCommand);
	}
	
	/**
	 * 按下向下按钮 
	 */
	public void toDown(){
		downCommand.execute();
		commandList.add(downCommand);
	}
	
	/**
	 * 按下撤销按钮 
	 */
	public void toRevoke(){
		revokeCommand.execute();
		commandList.remove(commandList.size()-1);
	}
}
