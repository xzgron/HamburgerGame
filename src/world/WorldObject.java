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

	private float xPrev, yPrev, zPrev;
	
	private float zPos;// en extra y variabel för texture som inte påväerkar objektets position det vill säga hur högt objectet befinner sig.
	
	private float xSpeed, ySpeed, zSpeed; // används som pixel per sek
	
	private float radius = -1; // i pixlar.  -1 betyder strŠck collision istŠllet fšr cirkel
	
	private int weight = -1; // i gram.  -1 betyder orrublig
	
	private float footPos = 0;
	private float headPos = 1;
	
	private boolean surface = false; ///////om nŒgot ska ligga pŒ marken
	
	private GFoodShadow shadow = null; ///////skugga??
	
	public WorldObject(float xPos, float yPos, float texSize, float footPos, float headPos) {
		super(xPos, yPos, texSize, texSize);

		this.headPos = headPos;
		this.footPos = footPos;
	}
	
	public WorldObject(float xPos, float yPos, float texWidth, float texHeight, float footPos, float headPos) {
		super(xPos, yPos, texWidth, texHeight);

		this.headPos = headPos;
		this.footPos = footPos;
	}
	
	
	///////////////MAIN STUFF//////////
	public void update(){
	}
	
	public void render(){
		renderShadow();
		GImage.draw(texture, xPos, yPos - zPos, texWidth, texHeight, red, green, blue, transparency);	
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
		return(zPos - texHeight/2 + texHeight*footPos);
	}
	
	public float getFootZPrev() {
		return(getZPrev() - texHeight/2 + texHeight*footPos);
	}
	
	////////////////////////////////////////////

	
	//grounden är kort sagt gubben yPos värde för footen när objectet står i marken
	///////////////GROUNDPOSITION//////////////////////////
	
	public void setGroundPos(float y){
		yPos = y - texHeight/2 + texHeight*footPos;
	}
	
	public float getGroundYPos(){
		return(yPos + texHeight/2 - texHeight*getFootPosVar());
	}
	
	public float getGroundYPrev() {
		return(getYPrev() + texHeight/2 - texHeight*getFootPosVar());
	}
	
	/////////////////////////////////////////
	// höjden är höjden i z mellan huvud och fot
	
	
	/////////////HEIGHT//////////////////
	public float getHeight(){
		return getHeadZPos() - getFootZPos() ;
		
	}

	////////////////////////////////////////////
	
	
	///////////////////////////////COORDINATER//////////////////////////////////
	
	//////////SPEED///////////////
	public void setSpeedByAngle(float amt, float angle){
		float xs = (float) (amt * cos(toRadians(angle)));
		float ys = (float) (amt * sin(toRadians(angle)));
		setSpeed(xs,ys);
	}
	
	public void setSpeedByVector(float amt, float xDirection, float yDirection){
		float directionLength = GMath.getDistance(0, 0, xDirection, yDirection);
		xDirection = xDirection/directionLength*amt;
		yDirection = yDirection/directionLength*amt;
		setSpeed(xDirection,yDirection);
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
////////////////////////////////////
	
	public void setPosition(float x, float y, float z){
		xPos = x;
		yPos = y;
		zPos = z;
	}
	

	/////////////PREVIOUS POSITION///////////
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
		zPrev = zPos;
		
	}
	
	////////////////////////////
	
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
		return GMath.getDistance(0,0,xSpeed,ySpeed);
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
	

	//////////////////////////////
	
	//////////////////////////////////COMMAND HELP//////////////////////
	
	public boolean justLanded(){
		return(getZPrev() > 0 && getZ() == 0);

	}
	
	public boolean justJumped(){
		return (getZPrev() == 0 && getZ() > 0);

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
	
///////////SURFACE/////////
	
	public boolean isSurface(){
		return surface;
	}
	
	public void setIfSurface(boolean b){
		surface = b;
	}
	
////////////////////////////
	
	
///////////////COLLISION//////////////////
	
	public void landedOn(WorldObject go){
		
	}
	
	public void collidedWith(WorldObject go){
		
	}
	
/////////////////////////////////////////
	
}
