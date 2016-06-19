/*
Name: Sungchan Park
Date: 6/15/2016
Assignment: AP CS Final Project
Description: Allows for images to be used
*/
package com.sungchan.apcs;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {
	private BufferedImage image;
	
	//Tries to get an image from designated location
	public BufferedImage loadImage(String path){
		try {
			image = ImageIO.read(getClass().getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
