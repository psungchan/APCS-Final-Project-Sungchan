package com.sungchan.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.util.LinkedList;

import com.sungchan.apcs.Camera;
import com.sungchan.apcs.Game;
import com.sungchan.apcs.Texture;
import com.sungchan.framework.GameObject;
import com.sungchan.framework.ObjectId;

public class Block extends GameObject{
	
	private int type;
	private Camera cam;
	Texture tex = Game.getInstance();

	public Block(float x, float y, Camera cam, int type, ObjectId id) {
		super(x, y, id);
		this.cam = cam;
		this.type = type;
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if(this.x > -cam.getX()+Game.WIDTH || (this.x < -cam.getX()-32 ||
			(this.y > -cam.getY() + Game.HEIGHT || (this.y < -cam.getY() - 32)))){
		}else{
			if(type == 0){
				g.drawImage(tex.block[0], (int)x, (int)y, null);
			}
			if(type == 1){
				g.drawImage(tex.block[1], (int)x, (int)y, null);
			}
			if(type == 2){
				g.drawImage(tex.block[2], (int)x, (int)y, null);
			}
			if(type == 3){
				g.drawImage(tex.block[3], (int)x, (int)y, null);
			}
			if(type == 4){
				g.drawImage(tex.block[4], (int)x, (int)y, null);
			}
			if(type == 5){
				g.drawImage(tex.block[5], (int)x, (int)y, null);
			}
			if(type == 6){
				g.drawImage(tex.block[6], (int)x, (int)y, null);
			}
			if(type == 7){
				g.drawImage(tex.block[7], (int)x, (int)y, null);
			}
			
				//g.setColor(Color.green);
			//g.drawRect((int)x, (int)y, 32, 32);
			//g.setColor(Color.white);
			//g.fillRect((int)x+1,  (int)y+1,  30,  30);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
