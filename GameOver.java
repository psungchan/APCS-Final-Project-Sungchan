package com.sungchan.objects;

import com.sungchan.apcs.Game;

public class GameOver {
	
	private static int score = Player.getPoints();
	
	public static void end(){
		System.out.println("Final Score: " + score);
	}
	
}
