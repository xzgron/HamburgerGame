package game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

public abstract class GWorldObject extends GObject{

	//private float xPrev, yPrev, zPrev;
	
	private float zPos;// en extra y variabel för texture som inte påväerkar objektets position det vill säga hur högt objectet befinner sig.
	
	private float xSpeed, ySpeed, zSpeed; // används som pixel per sek
	
	private float radie = -1; // i pixlar -1 betyder annan typ collision
	
	private float weight = -1; // i g kan man väl säga -1 betyder orrublig
	
	private float footPos;
	private float headPos;
	
	public GWorldObject(float xPos, float yPos, float texSize, float weight, float footPos, float headPos) {
		super(xPos, yPos, texSize, texSize);
		this.weight = weight;
		if(headPos > 1 || headPos < 0){
			System.out.println("huvudet ska vara mellan 0 och 1");
			this.headPos = 0.5f;
			}
		else
			this.headPos = headPos;
		
		
		if(footPos > 1 || footPos < 0){
			System.out.println("footen ska vara mellan 0 och 1");
			this.footPos = 0.5f;
			}
		else
			this.footPos = footPos;

		// TODO Auto-generated constructor stub
	}
	
	public void render(){
		GSprite.draw(texture, xPos, yPos + zPos, texWidth, texHeight, red, green, blue, transparency);	
	}

	
	
	public void update(){
		
		
		
		
		useSpeed();
		//updatePrevPos();
	}
	
	
	/////////////////////////////////////COLLISION////////////////////////////////////
	
	public void setRadie(float f){
		radie = f;
	}
	
	public float getRadie(){
		return radie;
	}
	
	public float getWeight(){
		return weight;
	}
	
	
	

	
	
	/////////////////////////////HEADS AND FOOTS////////////////////////////
	
	// huvudet bestämmer procentuellt var på bilden som går i taget...?
	
	public void setHeadPos(float f){
		if(f > 1 || f < 0)
			System.out.println("headen ska vara mellan 0 och 1");
		else
			headPos = f;
	}
	
	public float getHeadPos(){
		return(yPos + zPos - texHeight/2 + texHeight*headPos);
	}
	
	
	// foten bestämmer procentuellt var på bilden som går i marken
	public void setFootPos(float f){
		if(f > 1 || f < 0)
			System.out.println("footen ska vara mellan 0 och 1");
		else
			footPos = f;
	}
	
	public float getFootPos(){
		return(yPos + zPos - texHeight/2 + texHeight*footPos);
	}
	
	public void setGroundPos(float y){
		yPos = y - texHeight/2 + texHeight*footPos;
	}
	
	public float getGroundPos(){
		return(yPos - texHeight/2 + texHeight*footPos);
	}
	
	public float getPrevFootPos() {
		return(getYPrev() + getZPrev() - texHeight/2 + texHeight*footPos);
	}
	
	public float getPrevGroundPos() {
		return(getYPrev() - texHeight/2 + texHeight*footPos);
	}
	
	public float getHeight(){
		return texHeight*headPos - texHeight*footPos ;
		
	}
	
	public float getOrigoAndFootDiff(){
		return texHeight/2-texHeight*footPos;
	}
	
	
	///////////////////////////////COORDINATER//////////////////////////////////
	public void setSpeedByAngle(float amt, float angle){
		float xs = (float) (amt * sin(toRadians(angle)));
		float ys = (float) (amt * cos(toRadians(angle)));
		setSpeed(xs,ys);
	}
	
	public void setSpeed(float xs, float ys, float zs) {
		xSpeed = xs;
		ySpeed = ys;
		zSpeed = zs;
		
	}
	
	public void setSpeed(float xs, float ys) {
		xSpeed = xs;
		ySpeed = ys;
		
	}
	
	public void setZSpeed(float f){
		zSpeed = f;
	}
	
	public void stop(){
		xSpeed = 0;
		ySpeed = 0;
		
	}

	public void setPosition(float x, float y, float z){
		xPos = x;
		yPos = y;
		zPos = z;
	}
	
	public float getXPrev(){
		return xPos-xSpeed*Game.deltaTime;
	}
	
	public float getYPrev(){
		return yPos-ySpeed*Game.deltaTime;
	}
	
	public float getZPrev(){
		return zPos-zSpeed*Game.deltaTime;
	}
	
	/*
	
	public float getXPrev(){
		return xPrev;
	}
	
	public float getYPrev(){
		return yPrev;
	}
	public float getZPrev(){
		return zPrev;
	}
	
	
	public void updatePrevPos(){
		
		xPrev = xPos;
		yPrev = yPos;
		zPrev = yPos;
		
	}*/
	
	public float getXSpeed(){
		return xSpeed;
	}
	
	public float getYSpeed(){
		return ySpeed;
	}
	public float getZSpeed(){
		return zSpeed;
	}
	
	public float getXYSpeed(){
		return GMath.getDistance(0,xSpeed,0,ySpeed);
	}
	
	
	public void useSpeed(){
		xPos += xSpeed * Game.deltaTime;
		yPos += ySpeed * Game.deltaTime;
		zPos += zSpeed * Game.deltaTime;
	}
	
	public void accelerate(float xAcc, float yAcc, float zAcc){
		xSpeed += xAcc;
		ySpeed += yAcc;
		zSpeed += zAcc;
		
	}
	
	public void setZ(float z){
		zPos = z;
	}
	
	public float getZ(){
		return zPos;
	}
	

	
	
	//////////////////////////////////COMMAND HELP//////////////////////
	
	public boolean justLanded(){
		if(getZPrev() > 0 && zPos == 0)
			return true;
		else 
			return false;
	}
	
	public boolean justJumped(){
		if(getZPrev() == 0 && zPos > 0)
			return true;
		else 
			return false;
	}

	public boolean isWalking(){
		if(xSpeed != 0 || ySpeed != 0)
			return true;
		else 
			return false;
	}
	
	public boolean isJumping(){
		if(zPos > 0)
			return true;
		else
			return false;
	}
	
////////////////////////////OTHERS//////////////////////////
	
	public void scale(float f){
		texHeight *= f;
		texWidth *= f;
	}


}
