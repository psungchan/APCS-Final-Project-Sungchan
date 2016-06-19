package com.sungchan.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.print.DocFlavor.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sungchan.apcs.BufferedImageLoader;
import com.sungchan.apcs.Camera;
import com.sungchan.apcs.Game;
import com.sungchan.apcs.Handler;
import com.sungchan.apcs.Texture;
import com.sungchan.framework.GameObject;
import com.sungchan.framework.ObjectId;

public class Player extends GameObject{

	//private float width = 48, height = 96;
	private float width = 45, height = 65;
	private float gravity = 0.5f;
	private final float MAX_SPEED = 10;
	
	
//	private BufferedImage player = null;
	private boolean left = false; 
	private static int points = 0;
	private Handler handler;
	private Camera cam;
	private int facing = 1;
	
	private Animation playerWalk, playerBack, playerStand, playerStandBack, playerJump, playerJumpBack;
	//protected static STATE state;
		
	//public void showLoader(){
     //   java.net.URL url = this.getClass().getResource("t.gif");
    //    Icon icon = new ImageIcon(url);
    //    JLabel label = new JLabel(icon);
        //frameLoader.setUndecorated(true);
        //frameLoader.getContentPane().add(label);
        //frameLoader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frameLoader.pack();
       // frameLoader.setLocationRelativeTo(null);
       // frameLoader.setVisible(true);
   // }
	
	Texture tex = Game.getInstance();
	
	public Player(float x, float y, Handler handler, Camera cam, ObjectId id) {
		super(x, y, id);
		this.handler = handler;
		this.cam = cam;
		
		//playerStand = new Animation(8, tex.non[0], tex.non[1], tex.non[2], tex.non[1]);
		//playerStandBack = new Animation(8, tex.nonBack[0], tex.nonBack[1], tex.nonBack[2], tex.nonBack[1]);
		playerWalk = new Animation(8,  tex.stance[1], tex.stance[2], tex.stance[3]);
		playerBack = new Animation(8,  tex.back[0], tex.back[1], tex.back[2]);
		playerJump = new Animation(8, tex.jump[0], tex.jump[1], tex.jump[2], tex.jump[3]);
		//playerJumpBack = new Animation(8, tex.jumpBack[]);
		
		BufferedImageLoader loader = new BufferedImageLoader();
	//	player = loader.loadImage("/walk.gif");
	}

	public void tick(LinkedList<GameObject> object) {
		x += velX;
		y += velY;
		
		if(velX < 0)
			facing = -1;
		if(velX > 0)
			facing = 1;
		
		if(falling || jumping){
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		collision(object);
		
		playerBack.runAnimation();
		playerWalk.runAnimation();
	}
	
	
	private void collision(LinkedList<GameObject> object){
		for(int i = 0; i < handler.object.size(); i++){
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ObjectId.Coin){
				if(getBoundsTop().intersects(tempObject.getBounds()) || getBoundsLeft().intersects(tempObject.getBounds()) || 
					getBoundsRight().intersects(tempObject.getBounds()) || getBounds().intersects(tempObject.getBounds())){
					points++;
					handler.removeObject(handler.object.get(i));
				}
			}
			
			if(tempObject.getId() == ObjectId.Flag){
				Game.setStart();
				if(getBounds().intersects(tempObject.getBounds()))
					handler.switchLevel();
			}
			
			if(tempObject.getId() == ObjectId.Death){
				if(getBounds().intersects(tempObject.getBounds())){
				//	Game.state = STATE.Dead;
					Game.setDied();
					handler.switchLevel();
					System.out.println("Final Score: " + Player.getPoints());
				}
			}
			
			if(tempObject.getId() == ObjectId.Block){
				
				if(getBoundsTop().intersects(tempObject.getBounds()) && getBoundsRight().intersects(tempObject.getBounds())){
					
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds()) && getBoundsLeft().intersects(tempObject.getBounds())){
					
				}
				
				if(getBounds().intersects(tempObject.getBounds()) && getBoundsRight().intersects(tempObject.getBounds())){
					y = tempObject.getY()-height;
					velY = 0;
					falling = false;
					jumping = false;
				}else{
					falling = true;
				}
				

				if(getBounds().intersects(tempObject.getBounds()) && getBoundsLeft().intersects(tempObject.getBounds())){
					y = tempObject.getY()-height;
					velY = 0;
					falling = false;
					jumping = false;
				}else{
					falling = true;
				}
				
				
				if(getBoundsTop().intersects(tempObject.getBounds())){
					y = tempObject.getY() + 32;
					velY = 0;
				}
				
				if(getBounds().intersects(tempObject.getBounds())){
					y = tempObject.getY()-height;
					velY = 0;
					falling = false;
					jumping = false;
				}else{
					falling = true;
				}
				
				if(getBoundsRight().intersects(tempObject.getBounds())){
					x = tempObject.getX() - width;
				}

				if(getBoundsLeft().intersects(tempObject.getBounds())){
					x = tempObject.getX() + 34;
				}
			
			}
		}
	}

	public void render(Graphics g) {

		//if(velY > 0){
		//	g.drawImage(tex.jump[1], (int)x, (int)y, null);
		//}else if (velY < 0){
		//	playerJumpBack.drawAnimation(g, (int)x, (int)y);
		if(jumping){
			if(facing == 1){
				g.drawImage(tex.jump[2], (int)x, (int)y, null);
			}else if(facing == -1){
				g.drawImage(tex.jumpBack[2], (int)x, (int)y, null);
			}
		}else{
			if(velX != 0){
				if(facing == -1){
					playerBack.drawAnimation(g, (int)x, (int)y);
				}
				else if(facing == 1){
					playerWalk.drawAnimation(g, (int)x, (int)y);
				}
			}
			else if(velX == 0){
				//playerStandBack.drawAnimation(g, (int)x, (int)y);
				if(facing == 1){
					g.drawImage(tex.stance[0],(int)x, (int)y, null);
				}else{
					g.drawImage(tex.back[4], (int)x, (int)y, null);
				}
					//playerStand.drawAnimation(g, (int)x, (int)y);
			}
		}
		

		
		//	g.drawImage(player, (int)x, (int)y, null);
		//g.drawImage(tex.stance[0],(int)x, (int)y, (int)width, (int)height, null);
		//g.setColor(Color.blue);
		//g.fillRect((int)x, (int)y, (int)width, (int)height);
		//Graphics2D g2d = (Graphics2D)g;
		
		//g.setColor(Color.red);
		//g2d.draw(getBounds());
		//g2d.draw(getBoundsRight());
		//g2d.draw(getBoundsLeft());
		//g2d.draw(getBoundsTop());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x + (width/2)-((width/2)/2)), 
							(int) ((int)y + (height/2)), (int)width / 2, (int)height / 2);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x + (width/2)-((width/2)/2)), 
							(int)y, (int)width / 2, (int)height / 2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)(x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}

	public static int getPoints() {
		return points;
	}

}
