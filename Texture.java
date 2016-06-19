package com.sungchan.apcs;

import java.awt.image.BufferedImage;

public class Texture {
	SpriteSheet bs, ps, ks, ss, bss, js, bjs;
	private BufferedImage block_sheet = null;
	private BufferedImage player_sheet = null;
	private BufferedImage back_sheet = null;
	private BufferedImage stand_sheet = null;
	private BufferedImage standBack_sheet = null;
	private BufferedImage jump_sheet = null;
	private BufferedImage jumpBack_sheet = null;
	
	public BufferedImage[]block = new BufferedImage[9];
	public BufferedImage[]stance = new BufferedImage[4];
	public BufferedImage[]back = new BufferedImage[5];
	public BufferedImage[]non = new BufferedImage[3];
	public BufferedImage[]nonBack = new BufferedImage[3];
	public BufferedImage[]jump = new BufferedImage[4];
	public BufferedImage[]jumpBack = new BufferedImage[4];
	
	public Texture(){
		BufferedImageLoader loader = new BufferedImageLoader();
		try{
			block_sheet = loader.loadImage("/textures.png");
			player_sheet = loader.loadImage("/stance1.png");
			back_sheet = loader.loadImage("/stance2.png");
			//stand_sheet = loader.loadImage("/stand.png");
			//standBack_sheet = loader.loadImage("/stand2.png");
			jump_sheet = loader.loadImage("/jump.png");
			jumpBack_sheet = loader.loadImage("/jump2.png");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		bs = new SpriteSheet(block_sheet);
		ps = new SpriteSheet(player_sheet);
		ks = new SpriteSheet(back_sheet);
		//ss = new SpriteSheet(stand_sheet);
		//bss = new SpriteSheet(standBack_sheet);
		js = new SpriteSheet(jump_sheet);
		bjs = new SpriteSheet(jumpBack_sheet);
		getTextures();
	}
	
	//private void getPlayerStance(){
		
	//}
	
	private void getTextures(){
		block[0] = bs.grabImage(1, 1, 32, 32);
		block[1] = bs.grabImage(2, 1, 32, 32);
		block[2] = bs.grabImage(3, 1, 32, 32);
		block[3] = bs.grabImage(4, 1, 32, 32);
		block[4] = bs.grabImage(5, 1, 32, 32);
		block[5] = bs.grabImage(6, 1, 32, 32);
		block[6] = bs.grabImage(7, 1, 32, 32);
		block[7] = bs.grabImage(8, 1, 32, 32);
		block[8] = bs.grabImage(9, 1, 32, 32);
		
		stance[0] = ps.grabImage(1, 1, 45, 65);
		stance[1] = ps.grabImage(2, 1, 45, 65);
		stance[2] = ps.grabImage(3, 1, 45, 65);
		stance[3] = ps.grabImage(4, 1, 45, 65);
		
		back[0] = ks.grabImage(1, 1, 45, 65);
		back[1] = ks.grabImage(2, 1, 45, 65);
		back[2] = ks.grabImage(3, 1, 45, 65);
		back[3] = ks.grabImage(4, 1, 45, 65);
		back[4] = ks.grabImage(5, 1, 45, 65);
		
		jump[0] = js.grabImage(1, 1, 45, 77);
		jump[1] = js.grabImage(2, 1, 45, 77);
		jump[2] = js.grabImage(3, 1, 45, 77);
		jump[3] = js.grabImage(4, 1, 45, 77);

		jumpBack[0] = bjs.grabImage(1, 1, 45, 77);
		jumpBack[1] = bjs.grabImage(2, 1, 45, 77);
		jumpBack[2] = bjs.grabImage(3, 1, 45, 77);
		jumpBack[3] = bjs.grabImage(4, 1, 45, 77);
		
	}
}
