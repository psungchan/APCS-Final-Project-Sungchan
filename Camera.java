/*
Name: Sungchan Park
Date: 6/15/2016
Assignment: AP CS Final Project
Description: Creates a controllable camera that designates the rendered screen
*/

package com.sungchan.apcs;

import com.sungchan.framework.GameObject;

public class Camera {
	
	//X and Y location of Camera start and the player track 
	private float x, y, xTarg;
	
	//Constructor
	public Camera(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	//Constantly resets the location of the Camera
	public void tick(GameObject player){
		this.xTarg = -player.getX()+Game.WIDTH/2;
		x+= ((xTarg - x) * 0.3);
		
		x = -player.getX() + Game.WIDTH / 2;
	}
	
	//Sets X
	public void setX(float x){
		this.x = x;
	}

	//Sets Y
	public void setY(float y){
		this.y = y;
	}
	
	//Gets X
	public float getX(){
		return x;
	}
	
	//Gets Y
	public float getY(){
		return y;
	}
}
