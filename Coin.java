package com.sungchan.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window.Type;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.sungchan.apcs.BufferedImageLoader;
import com.sungchan.apcs.Camera;
import com.sungchan.apcs.Game;
import com.sungchan.framework.GameObject;
import com.sungchan.framework.ObjectId;

public class Coin extends GameObject{

	private Camera cam;
	private BufferedImage coin = null;
	
	
	public Coin(float x, float y, Camera cam, ObjectId id) {
		super(x, y, id);
		this.cam = cam;
		BufferedImageLoader loader = new BufferedImageLoader();
		coin = loader.loadImage("/Coin.png");
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if(this.x > -cam.getX()+Game.WIDTH || (this.x < -cam.getX()-32 ||
			(this.y > -cam.getY() + Game.HEIGHT || (this.y < -cam.getY() - 32)))){
		}else{
			
			g.drawImage(coin, (int)x, (int)y, null);
			//g.setColor(Color.yellow);
			//g.fillRect ((int)x,  (int)y,  32,  32);
		}
	}
	
	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}