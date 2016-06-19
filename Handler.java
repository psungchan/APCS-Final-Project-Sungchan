package com.sungchan.apcs;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.awt.image.BufferStrategy;

import com.sungchan.framework.GameObject;
import com.sungchan.framework.ObjectId;
import com.sungchan.objects.Block;
import com.sungchan.objects.Coin;
import com.sungchan.objects.Death;
import com.sungchan.objects.Flag;
import com.sungchan.objects.GameOver;
import com.sungchan.objects.Player;

public class Handler {
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	private GameObject tempObject;
	
	private Camera cam;
	
	private BufferedImage level2 = null, level = null, level3 = null, level4, end = null; 
						//lossScreen = null, winScreen = null;
	
	public Handler(Camera cam){
		this.cam = cam;
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/TESTING.png");
		level2 = loader.loadImage("/three.png");
		level3 = loader.loadImage("/four.png");
		level4 = loader.loadImage("/kelvin.png");
		end = loader.loadImage("/end.png");
		//try {
			//lossScreen = ImageIO.read(new File("loss.png"));
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
		//winScreen = 
	}
	
	public void tick(){
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			tempObject.tick(object);
		}
	}
	
	public void render(Graphics g){
		for(int i = 0; i < object.size(); i++){
			tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void loadImageLevel(BufferedImage image){
		int w = image.getWidth();
		int h = image.getHeight();
		
		for(int rows = 0; rows < h; rows++){
			for(int cols = 0; cols < w; cols++){
				int pixel = image.getRGB(rows, cols);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				//Regular death block
				if(red == 255 && green == 0 & blue == 0){
					addObject(new Death((rows * 32), (cols * 32), cam, 0, ObjectId.Death));
				}
				//Spike block
				if(red == 163 && green == 255 & blue == 135){
					addObject(new Death((rows * 32), (cols * 32), cam, 1, ObjectId.Death));
				}
				
				//Top Block
				if(red == 255 && green == 255 & blue == 255){
					addObject(new Block((rows * 32), (cols * 32), cam, 0, ObjectId.Block));
				}
				//Left Corner
				if(red == 255 && green == 217 & blue == 168){
					addObject(new Block((rows * 32), (cols * 32), cam, 1, ObjectId.Block));
				}
				//Right Corner
				if(red == 196 && green == 255 & blue == 228){
					addObject(new Block((rows * 32), (cols * 32), cam, 2, ObjectId.Block));
				}
				//Bottom Left
				if(red == 117 && green == 255 & blue == 156){
					addObject(new Block((rows * 32), (cols * 32), cam, 3, ObjectId.Block));
				}
				//Bottom Right
				if(red == 255 && green == 235 & blue == 89){
					addObject(new Block((rows * 32), (cols * 32), cam, 4, ObjectId.Block));
				}
				//Left Wall
				if(red == 140 && green == 255 & blue == 241){
					addObject(new Block((rows * 32), (cols * 32), cam, 5, ObjectId.Block));
				}
				//Right Wall
				if(red == 237 && green == 255 & blue == 183){
					addObject(new Block((rows * 32), (cols * 32), cam, 6, ObjectId.Block));
				}
				//Middle
				if(red == 186 && green == 255 & blue == 245){
					addObject(new Block((rows * 32), (cols * 32), cam, 7, ObjectId.Block));
				}
				
				
				if(red == 255 && green == 216 & blue == 0){
					addObject(new Flag((rows * 32), (cols * 32), ObjectId.Flag));
				}
				
				if(red == 0 && green == 0 & blue == 255){
					addObject(new Player(32, 32, this, cam, ObjectId.Player));
				}
				
				if(red == 0 && green == 255 & blue == 0){
					addObject(new Coin((rows * 32), (cols * 32), cam, ObjectId.Coin));
				}
				
				//if((red == 255) && (green == 0) & (blue == 220)){
				//	addObject(new Score((rows * 32), (cols * 32), ObjectId.Score));
				//}
			}
		}
	}
	

	
	public void switchLevel(){
		clearLevel();
		cam.setX(0);
	//	BufferStrategy bs = this.getBufferStrategy();
	//	if(bs == null){
	//		this.createBufferStrategy(3);
	//		return;
	//	}
	//
	//da	Graphics g = bs.getDrawGraphics();
		
		switch(Game.LEVEL){
		case 1:
			loadImageLevel(level);
			break;
		case 2:
			loadImageLevel(level2);
			break;
		case 3:
			loadImageLevel(level3); 
			break;
		case 4:
			loadImageLevel(end);
			break;
		case 5:
			GameOver.end();
			System.exit(1);
		}
		Game.LEVEL++;
	}
	
	private void clearLevel(){
		object.clear();
	}
	
	public void addObject(GameObject object){
		this.object.add(object);
	}
	
	public void removeObject(GameObject object){
		this.object.remove(object);
	}
		
}
