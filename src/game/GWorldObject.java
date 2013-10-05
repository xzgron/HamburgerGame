package game;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import worldObjects.food.GFoodShadow;

public abstract class GWorldObject extends GObject{

	//private float xPrev, yPrev, zPrev;
	
	private float zPos;// en extra y variabel f�r texture som inte p�v�erkar objektets position det vill s�ga hur h�gt objectet befinner sig.
	
	private float xSpeed, ySpeed, zSpeed; // anv�nds som pixel per sek
	
	private float radie = -1; // i pixlar -1 betyder annan typ collision
	
	private float weight = -1; // i g kan man v�l s�ga -1 betyder orrublig
	
	private float footPos;
	private float headPos;
	
	private GFoodShadow shadow = null;
	
	public GWorldObject(float xPos, float yPos, float texSize, float footPos, float headPos) {
		super(xPos, yPos, texSize, texSize);
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
		renderShadow();
		GSprite.draw(texture, xPos, yPos + zPos, texWidth, texHeight, red, green, blue, transparency);	
	}


	
	/////////////////////////////////////COLLISION////////////////////////////////////
	
	public void setRadie(float f){
		radie = f;
	}
	
	public float getRadie(){
		return radie;
	}
	
	public void setWeight(float f){
		weight = f;
	}
	
	public float getWeight(){
		return weight;
	}
	
	
	

	
	
	/////////////////////////////HEADS AND FOOTS////////////////////////////
	
	// huvudet best�mmer procentuellt var p� bilden som g�r i taget...?
	
	public void setHeadPosVar(float f){
		if(f > 1 || f < 0)
			System.out.println("headen ska vara mellan 0 och 1");
		else
			headPos = f;
	}
	
	public float getHeadPosVar(){
		return headPos;
	}
	
	public void setHeadZPos(float z){
		zPos = z + texHeight/2 - texHeight*headPos;
	}
	public float getHeadZPos(){
		return(zPos - texHeight/2 + texHeight*headPos);
	}
	
	public float getPrevHeadZPos() {
		return(getPrevZPos() - texHeight/2 + texHeight*headPos);
	}
	
	
	// foten best�mmer procentuellt var p� bilden som g�r i marken
	public void setFootPosVar(float f){
		if(f > 1 || f < 0)
			System.out.println("footen ska vara mellan 0 och 1");
		else
			footPos = f;
	}
	
	public float getFootPosVar(){
		return footPos;
	}
	
	public void setFootZPos(float z){
		zPos = z + texHeight/2 - texHeight*footPos;
	}
	
	public float getFootZPos(){
		return(zPos - texHeight/2 + texHeight*footPos);
	}
	
	public float getFootYPos(){
		return(yPos+zPos-texHeight/2 + texHeight*footPos);
	}
	
	
	public float getPrevFootZPos() {
		return(getPrevZPos() - texHeight/2 + texHeight*footPos);
	}
	

	
	//grounden �r kort sagt gubben yPos v�rde f�r footen n�r objectet st�r i marken
	
	public void setGroundPos(float y){
		yPos = y + texHeight/2 - texHeight*footPos;
	}
	
	public float getGroundYPos(){
		return(yPos - texHeight/2 + texHeight*getFootPosVar());
	}
	
	public float getPrevGroundYPos() {
		return(getPrevYPos() - texHeight/2 + texHeight*getFootPosVar());
	}
	
	// h�jden �r h�jden i z mellan huvud och fot
	
	public float getHeight(){
		return texHeight*headPos- texHeight*footPos ;
		
	}
	
	public float getOrigoAndFootDiff(){
		return texHeight/2-texHeight*getFootPosVar();
	}
	
	
	///////////////////////////////COORDINATER//////////////////////////////////
	public void setSpeedByAngle(float amt, float angle){
		float xs = (float) (amt * cos(toRadians(angle)));
		float ys = (float) (amt * sin(toRadians(angle)));
		setSpeed(xs,ys);
	}
	
	public void setSpeed(float x, float y, float z) {
		xSpeed = x;
		ySpeed = y;
		zSpeed = z;
		
	}
	
	public void setSpeed(float x, float y) {
		xSpeed = x;
		ySpeed = y;
		
	}
	
	public void setZSpeed(float z){
		zSpeed = z;
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
	
	public float getPrevXPos(){
		return xPos-xSpeed*Game.deltaTime;
	}
	
	public float getPrevYPos(){
		return yPos-ySpeed*Game.deltaTime;
	}
	
	public float getPrevZPos(){
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
	
	public void addSpeed(float x, float y, float z){
		xSpeed += x;
		ySpeed += y;
		zSpeed += z;
		
	}
	public void accelerate(float xAcc, float yAcc, float zAcc){
		xSpeed += xAcc * Game.deltaTime;
		ySpeed += yAcc * Game.deltaTime;
		zSpeed += zAcc * Game.deltaTime;
		
	}
	
	public void setZ(float z){
		zPos = z;
	}
	
	public float getZ(){
		return zPos;
	}
	

	
	
	//////////////////////////////////COMMAND HELP//////////////////////
	
	public boolean justLanded(){
		return(getPrevZPos() > 0 && getZ() == 0);

	}
	
	public boolean justJumped(){
		return (getPrevZPos() == 0 && getZ() > 0);

	}

	public boolean isWalking(){
		return(xSpeed != 0 || ySpeed != 0);
	}
	
	public boolean isInAir(){
		return(getZ() > 0);
	}
	
////////////////////////////OTHERS//////////////////////////
	
	public void scale(float f){
		texHeight *= f;
		texWidth *= f;
	}
	
	public void scale(float fw,float fh){
		texHeight *= fh;
		texWidth *= fw;
	}
	
////////////SHADOW////////////////////
	public void createShadow(){
		shadow = new GFoodShadow(this);
	}
	
	public void renderShadow() {
		if(shadow != null)
			shadow.render();
	}
}
