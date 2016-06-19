package com.sungchan.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.sungchan.apcs.BufferedImageLoader;
import com.sungchan.framework.GameObject;
import com.sungchan.framework.ObjectId;



public class Flag extends GameObject{
	
	private BufferedImage img = null;
	
	public Flag(float x, float y, ObjectId id){
		super(x, y, id);
		BufferedImageLoader loader = new BufferedImageLoader();
		img = loader.loadImage("/flag.png");
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		//g.setColor(Color.orange);
		//g.fillRect((int)x, (int)y, 32, 32);
		g.drawImage(img, (int)x - 64, (int)y-90, null);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}
}
