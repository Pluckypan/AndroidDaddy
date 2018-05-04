package com.zl.pattern.memorandum;

public class Client {
	public static void main(String[] args) {
		//构建游戏对象
		CallOfDuty game = new CallOfDuty();
		//1.打游戏
		game.play();
		
		Caretaker caretaker = new Caretaker();
		//2.游戏存档
		caretaker.archive(game.createMemento());
		//3.退出游戏
		game.quit();
		//4.恢复游戏
		CallOfDuty newGame = new CallOfDuty();
		newGame.restore(caretaker.getMemento());
		
		//5.再次打游戏(不存档)
		game.play();
		//6.恢复之前存档
		newGame.restore(caretaker.getMemento());
	}
}
