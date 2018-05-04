package com.zl.pattern.command;

public interface Command {
	/**
	 * 命令执行方法
	 */
	void execute();
	
	/**
	 * 获取命令类型
	 */
	void getCommand();
}
