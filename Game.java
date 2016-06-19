/*
Name: Sungchan Park
Date: 6/15/2016
Assignment: AP CS Final Project
Description: Creates and runs the game on the Canvas
*/ 
 
package com.sungchan.apcs;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.LinkedList;
import java.util.Random;

import com.sungchan.framework.GameObject;
import com.sungchan.framework.KeyInput;
import com.sungchan.framework.ObjectId;
import com.sungchan.objects.Block;
import com.sungchan.objects.Death;
import com.sungchan.objects.Flag;
import com.sungchan.objects.Player;

public class Game extends Canvas implements Runnable{

//	public enum STATE {
//		Menu,
//		Game, 
//		Dead;
//	}



	private static final long serialVersionUID = 9064561415220398101L;
	
	//Signifies if Dead
	public static boolean died = false;
	//Signifies if out of menu
	public static boolean start = false;
	private boolean running = false;
	private Thread thread;
	  
	public static int WIDTH, HEIGHT;
	
	public BufferedImage level = null, background = null, menu = null, dead = null, menuBackground = null, ending = null;
	
	Camera cam;
	static Texture tex;
	Handler handler;	
	
	Random rand = new Random();
	
		
	public static int LEVEL = 1;
	
	public static void setStart(){
		start = true;
	}
	
	public static void setDied(){
		died = true;
	}
	
	public static Texture getInstance(){
		return tex;
	}
	
	private void init(){
		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		tex = new Texture();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/TESTING.png");
		background = loader.loadImage("/background.png");
		menu = loader.loadImage("/menu.png");
		dead = loader.loadImage("/loss.png");
		menuBackground = loader.loadImage("/background.png");
		ending = loader.loadImage("/ending.png");
		
		
		cam = new Camera(0,0);
		handler = new Handler(cam);
		
		//System.out.println("menu");
		
		Sound.sound1.loop();
		
		handler.loadImageLevel(menu);
		//handler.addObject(new Player(100, 100, handler, ObjectId.Player));
		
		//handler.createLevel();
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start(){
		if(running)
			return;
	
		running = true;
		thread = new Thread(this);
		thread.start();
	}	
	
	public void run(){
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			//System.out.println(timer);
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				//System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}

	private void tick(){
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++){
			if(handler.object.get(i).getId() == ObjectId.Player){
				cam.tick(handler.object.get(i));
			}
		}
	}
	
	private void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		//handler.object.clear();
		//g.drawString("SOMETHING" + Player.getPoints(), 150, 150);
		
		g2d.translate(cam.getX(), cam.getY());
		
		//if(inMenu){
		//	g.drawImage(menu, 0, 0, null);
		//	System.out.println("menu");
	//}else{	
			//for(int xx = 0; xx < (639 * 15); xx += 639)
		if(start == false){
			//if(this.x > -cam.getX()+Game.WIDTH || (this.x < -cam.getX()-32 ||
			//		(this.y > -cam.getY() + Game.HEIGHT || (this.y < -cam.getY() - 32)))){
			//}else{
				g.drawImage(background, -500, 0, this);
				//g.drawImage(background, 524, -50, this);
				//g.drawImage(background, 0, 0, this);
			}
		//}
		if(start == true){
			//g.drawString(cam.getX() + 50, cam.getY() + 50);
			g.drawImage(background, -500, 0, this);
			//g.drawImage(background, 524, -50, this);
			//g.drawImage(background, 1048, -50, this);
			//g.drawImage(background, 2060, -50, this);
			//g.drawImage(background, 3020, -50, this);
			//g.drawImage(background, 4000, -50, this);
			//g.drawImage(background, 5000, -50, this);
		}	

		
		handler.render(g);
	//	}
		if(died == true){
			g.setColor(Color.white);
			Font newFont = new Font("Serif", Font.BOLD, 50);
			Font font = newFont.deriveFont(75f);
			g.setFont(font);
			g.drawString("Final Score: " + Player.getPoints(), 275, 400);
			//g.drawImage(background, -500, -50, this);
			//g.drawImage(background, 524, -50, this);
			//ddg.drawImage(ending, 0, 0, this);		
		}else{
		//idk how work... just does
			g.setColor(Color.white);
			Font newFont = new Font("Serif", Font.BOLD, 10);
			Font font = newFont.deriveFont(25f);
			g.setFont(font);
			g.drawString("Points: " + Player.getPoints(), (int)-cam.getX() +50, (int)cam.getY()+60);
		}
		g.dispose();
		bs.show();
	}
	

	
	public static void main(String[] args){
		new Window(800, 600, "AP Computer Science Final: GOTY", new Game());
	}
}
