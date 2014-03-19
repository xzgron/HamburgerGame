package world;

import static java.lang.Math.*;
import game.GImage;
import game.GMath;
import game.GPhysics;
import game.GSprite;
import game.Game;
import game.Main;
import game.parts.GameWorld;
import game.tools.GMouse;
import world.objects.*;
import world.*;

public abstract class WorldObject extends GSprite{

	private float xPrev, yPrev, zPrev;
	
	private float zPos;// en extra y variabel för texture som inte påväerkar objektets position det vill säga hur högt objectet befinner sig.
	
	private float xSpeed, ySpeed, zSpeed; // används som pixel per sek
	
	private float radius = -1; // i pixlar.  -1 betyder strŠck collision istŠllet fšr cirkel
	
	private int weight = -1; // i gram.  -1 betyder orrublig
	
	private float footPos = 0;
	private float headPos = 1;
	
	//private boolean solid = true; // ett icke solidt object flyttar varken sig sjŠlv eller den den trŠffar 
	private boolean surface = false; ///////om nŒgot ska ligga pŒ marken det renderas under allt annat och det
	//sker ingen collision alls
	
	//private boolean inAir = true;

	private Shadow shadow = null; ///////skugga??
	
	public WorldObject(float xPos, float yPos, float texWidth, float texHeight, String texture, float footPos, float headPos) {
		super(xPos, yPos, texWidth, texHeight);
		setTexture(texture);
		this.headPos = headPos;
		this.footPos = footPos;
	}
	
	
	///////////////MAIN STUFF//////////
	public void update(GameWorld world){
		GPhysics.handleGroundCollision(this, world);
	}
	
	public void render(){
		renderShadow();
		GImage.draw(texture, xPos, yPos - zPos, texWidth, texHeight, red, green, blue, alpha);	
	}
	//////////////////////////////

	
	/////////////////////////////////////COLLISION////////////////////////////////////
	
	
	public void setRadius(float f){
		radius = f;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public void setWeight(int i){
		weight = i;
	}
	
	public int getWeight(){
		return weight;
	}
	
	
	////////////////////////////////////////////

	
	
	/////////////////////////////HEADS AND FOOTS////////////////////////////
	
	
	///////////HEAD/////////
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
	
	public float getHeadZPrev() {
		return(getZPrev() - texHeight/2 + texHeight*headPos);
	}
	//////////////////////////////////////////
	
	///////////FOOT/////////////////////
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
		return(getZ() - texHeight/2 + texHeight*footPos);
	}
	
	public float getFootZPrev() {
		return(getZPrev() - texHeight/2 + texHeight*footPos);
	}
	
	////////////////////////////////////////////

	
	
	/////////////////////////////////////////
	// höjden är höjden i z mellan huvud och fot
	
	
	/////////////HEIGHT//////////////////
	public float getHeight(){
		return getHeadZPos() - getFootZPos() ;
		
	}

	////////////////////////////////////////////
	
	
	///////////////////////////////COORDINATER//////////////////////////////////

////////////////////POSITION///////////////////
	
	public void setPosition(float x, float y, float z){
		xPos = x;
		yPos = y;
		zPos = z;
	}
	

	/////////////PREVIOUS POSITION///////////
	public void updatePrevPos(){
		xPrev = xPos;
		yPrev = yPos;
		zPrev = zPos;
		
	}
	
	public float getXPrev(){
		return xPrev;
	}
	
	public float getYPrev(){
		return yPrev;
	}
	
	public float getZPrev(){
		return zPrev;
	}
	
	//////////////////////////// ZPOS ///////////////
	public void setZ(float z){
		zPos = z;
	}
	
	public float getZ(){
		return zPos;
	}
	
	
	
	///////////////////SPEED////////////////
	public void useSpeed(){
		xPos += xSpeed * Main.getDelta();
		yPos += ySpeed * Main.getDelta();
		zPos += zSpeed * Main.getDelta();
	}
	
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
		return GMath.getLength(xSpeed,ySpeed);
	}
	
	public float getXYZSpeed(){
		return GMath.getLength(xSpeed,ySpeed,zSpeed);
	}
	
	
	
	public void setZSpeed(float z){
		zSpeed = z;
	}
	
	public void setXYSpeed(float speed){
		setSpeedByVector(speed, getXSpeed(), getYSpeed());
	}
	public void setSpeed(float x, float y) {
		xSpeed = x;
		ySpeed = y;	
	}
	
	public void setSpeed(float x, float y, float z) {
		xSpeed = x;
		ySpeed = y;
		zSpeed = z;
	}
	
	public void setSpeedByAngle(float amt, float angle){
		float xs = (float) (amt * cos(toRadians(angle)));
		float ys = (float) (amt * sin(toRadians(angle)));
		setSpeed(xs,ys);
	}
	
	public void setSpeedByVector(float amt, float xDir, float yDir){
		float directionLength = GMath.getLength(xDir, yDir);
		xDir = xDir/directionLength*amt;
		yDir = yDir/directionLength*amt;
		setSpeed(xDir,yDir);
	}
	public void setSpeedByVector(float amt, float xDir, float yDir, float zDir){
		float directionLength = GMath.getLength(xDir, yDir,zDir);
		xDir = xDir/directionLength*amt;
		yDir = yDir/directionLength*amt;
		zDir = zDir/directionLength*amt;
		setSpeed(xDir,yDir,zDir);
	}	
	
	public void addSpeed(float x, float y, float z){
		xSpeed += x;
		ySpeed += y;
		zSpeed += z;
		
	}
	
	public void stop(){
		setSpeed(0,0);		
	}
	
	////////////////ACCELERATION///////////////
	public void accelerate(float xAcc, float yAcc, float zAcc){
		xSpeed += xAcc * Main.getDelta();
		ySpeed += yAcc * Main.getDelta();
		zSpeed += zAcc * Main.getDelta();
	}
	public void accelerateByAngle(float amt, float angle){
		float xs = (float) (amt * cos(toRadians(angle)));
		float ys = (float) (amt * sin(toRadians(angle)));
		accelerate(xs,ys,0);
	}
	public void accelerateByVector(float amt, float xDir, float yDir, float zDir){
		float directionLength = GMath.getLength(xDir, yDir,zDir);
		xDir = xDir/directionLength*amt;
		yDir = yDir/directionLength*amt;
		zDir = zDir/directionLength*amt;
		accelerate(xDir,yDir,zDir);
	}
	


	

	//////////////////////////////
	
	//////////////////////////////////COMMAND HELP////////////////////// //foot och head medfšr avrundings fel 
	
	public boolean justLanded(){
		return(getFootZPrev() > 0.00001f && getFootZPos() <= 0.00001f);

	}
	
	public boolean justJumped(){
		return (getFootZPrev() <= 0.00001f && getFootZPos() > 0.00001f);

	}
	
	/*public void setInAir(boolean b){ // om man ska kunna gŒ pŒ saker
		inAir = b;
	}*/
	
	public boolean isInAir(){
		/*if(getFootZPos() > 0.00001f)
			inAir = false;
		return inAir;*/
		return (getFootZPos() > 0.00001f);
	}
	
	public boolean isOnGround(){
		return(getFootZPos() <= 0.00001f);
	}

	public boolean isMoving(){
		return(xSpeed != 0 || ySpeed != 0);
	}
	

	
////////////////////////////SCALING//////////////////////////
	
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
		shadow = new Shadow(this);
	}
	
	public void renderShadow() {
		if(shadow != null)
			shadow.render();
	}
	public void removeShadow(){
		shadow = null;
	}
	
	public float getShadowRadius(){
		if(shadow != null)
			return shadow.getRadius();
		else
			return 0;
	}
	
///////////SURFACE/////////
	
	public boolean isSurface(){
		return surface;
	}
	
	public void setIfSurface(boolean b){
		surface = b;
	}
	
////////////////////////////
	
	
///////////////COLLISION//////////////////
	
	public void landedOn(WorldObject go, GameWorld world){
		
	}
	
	public void gotLandedOnBy(WorldObject go, GameWorld world){
		
	}
	
	public void collidedWith(WorldObject go, GameWorld world){
	}
	
/////////////////////////////////////////
	
	//////////////////WORLD TRANSLATION////////////
	public boolean isCursorWithin(GameWorld world){
		return GMath.isPosWithinSquare(GMouse.getX()+world.getXTranslation(),GMouse.getY()+world.getYTranslation(),getX(), getY()+getZ(), getTexWidth(), getTexHeight());
	}
	
	
	
	//////////////////////////////////
}
