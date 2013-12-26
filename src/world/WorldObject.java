package world;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;
import game.GImage;
import game.GMath;
import game.GSprite;
import game.Game;
import game.Main;
import world.objects.food.GFood;
import world.objects.food.GFoodShadow;

public abstract class WorldObject extends GSprite{

	//private float xPrev, yPrev, zPrev;
	
	private float zPos;// en extra y variabel för texture som inte påväerkar objektets position det vill säga hur högt objectet befinner sig.
	
	private float xSpeed, ySpeed, zSpeed; // används som pixel per sek
	
	private float radie = -1; // i pixlar.  -1 betyder strŠck collision istŠllet fšr cirkel
	
	private float weight = -1; // i gram.  -1 betyder orrublig
	
	private float footPos;
	private float headPos;
	
	private boolean surface = false;
	
	private GFoodShadow shadow = null;
	
	public WorldObject(float xPos, float yPos, float texSize, float footPos, float headPos) {
		super(xPos, yPos, texSize, texSize);

		this.headPos = headPos;
		this.footPos = footPos;
	}
	
	public abstract void update();
	
	public void render(){
		renderShadow();
		GImage.draw(texture, xPos, yPos - zPos, texWidth, texHeight, red, green, blue, transparency);	
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
	
	// huvudet bestämmer procentuellt var på bilden som går i taget...?
	
	public void setHeadPosVar(float f){
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
	
	
	// foten bestämmer procentuellt var på bilden som går i marken
	public void setFootPosVar(float f){
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
		return(yPos - getFootZPos());
	}
	
	
	public float getPrevFootZPos() {
		return(getPrevZPos() - texHeight/2 + texHeight*footPos);
	}
	

	
	//grounden är kort sagt gubben yPos värde för footen när objectet står i marken
	
	public void setGroundPos(float y){
		yPos = y - texHeight/2 + texHeight*footPos;
	}
	
	public float getGroundYPos(){
		return(yPos + texHeight/2 - texHeight*getFootPosVar());
	}
	
	public float getPrevGroundYPos() {
		return(getPrevYPos() + texHeight/2 - texHeight*getFootPosVar());
	}
	
	// höjden är höjden i z mellan huvud och fot
	
	public float getHeight(){
		return getHeadZPos() - getFootZPos() ;
		
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
		return xPos-xSpeed*Main.getDelta();
	}
	
	public float getPrevYPos(){
		return yPos-ySpeed*Main.getDelta();
	}
	
	public float getPrevZPos(){
		return zPos-zSpeed*Main.getDelta();
	}
	
	public void landedOn(WorldObject go){}
	
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
		xPos += xSpeed * Main.getDelta();
		yPos += ySpeed * Main.getDelta();
		zPos += zSpeed * Main.getDelta();
	}
	
	public void addSpeed(float x, float y, float z){
		xSpeed += x;
		ySpeed += y;
		zSpeed += z;
		
	}
	public void accelerate(float xAcc, float yAcc, float zAcc){
		xSpeed += xAcc * Main.getDelta();
		ySpeed += yAcc * Main.getDelta();
		zSpeed += zAcc * Main.getDelta();
		
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
	
///////////isOnGround/////////
	
	public boolean isSurface(){
		return surface;
	}
	
	public void setIfSurface(boolean b){
		surface = b;
	}
}
