package com.sungchan.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.sungchan.apcs.Handler;
import com.sungchan.apcs.Game;
//import com.sungchan.apcs.Game.STATE;

public class KeyInput extends KeyAdapter{
	
	Handler handler;
	private boolean oncePer = true;
	
	public KeyInput(Handler handler){
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
				if(key == KeyEvent.VK_ENTER && oncePer == true){
					//Game.inMenu = false;
					handler.switchLevel();
					oncePer = false;
				}
				
				if(tempObject.getId() == ObjectId.Player){
					if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) tempObject.setVelX(5);
					if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) tempObject.setVelX(-5);
					if((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) && !tempObject.isJumping()){
						tempObject.setJumping(true);
						tempObject.setVelY(-12);
						
					}
				}
		}
		
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();

		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Player){
				if((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && tempObject.getVelX() == 5) 
					tempObject.setVelX(0);
				if((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && tempObject.getVelX() == -5) 
					tempObject.setVelX(0);
				//if((key == KeyEvent.VK_SPACE || key == KeyEvent.VK_P))
				//	tempObject.setVelY(0);
				if(key == KeyEvent.VK_ENTER){  
					oncePer = true;
				}
			}
		}
	}
}
