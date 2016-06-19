package com.sungchan.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import com.sungchan.apcs.BufferedImageLoader;
import com.sungchan.apcs.Camera;
import com.sungchan.apcs.Game;
import com.sungchan.apcs.Texture;
import com.sungchan.framework.GameObject;
import com.sungchan.framework.ObjectId;

public class Death extends GameObject{

	private int type;
	private Camera cam;
	Texture tex = Game.getInstance();
	private BufferedImage spike = null;
	
	public Death(float x, float y, Camera cam, int type, ObjectId id) {
		super(x, y, id);
		this.cam = cam;
		this.type = type;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		spike = loader.loadImage("/spike.png");
	}

	public void tick(LinkedList<GameObject> object) {
		
	}

	public void render(Graphics g) {
		if(this.x > -cam.getX()+Game.WIDTH || (this.x < -cam.getX()-32 ||
		  (this.y > -cam.getY() + Game.HEIGHT || (this.y < -cam.getY() - 32)))){
		}else{
			if(type == 0){
			//g.setColor(Color.red);	
			//g.fillRect((int)x, (int)y, 32, 32);
			g.drawImage(tex.block[7], (int)x, (int)y, null);
			}
			if(type == 1){
				g.drawImage(tex.block[8], (int)x, (int)y, null);
			}
		}
	}

	public Rectangle getBounds(){
		return new Rectangle((int)x, (int)y, 32, 32);
	}
	
}
