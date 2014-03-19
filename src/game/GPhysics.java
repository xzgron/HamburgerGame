package game;

import static java.lang.Math.*;
import static java.awt.geom.Line2D.*;
import static game.GMath.*;
import game.parts.GameWorld;

import org.lwjgl.input.Mouse;

import world.WorldObject;
import world.objects.GFood;
public class GPhysics {
	
	// här hanteras allt som är specifikt för spelet likt hur collision ska hanteras och andra fysiska egenskaper som skada
	// eller gravitation etc.
	
	public static float viewPoint = 2f; // 2 är från sin(30°) vilket betyder att view vinkeln är 30°
	
	public static boolean handleCollision(WorldObject go1, WorldObject go2, GameWorld world) {
		if (go1.isSurface()||go2.isSurface())
			return false;
		
		if (go1.getRadius() == -1 || go2.getRadius() == -1)
			return false;
		//K÷R STRƒCK COLLISION AND SHIT////
		
		float dx = go1.getX() - go2.getX(); //från go2 till go1
		float dy = go1.getY() - go2.getY();
		
		float dist = getLength(dx,dy); //avståndet mellan objekten
		
		float totRadius = go1.getRadius()+go2.getRadius(); //totala minradien mellan objekten
		
		
		
		if(dist >= totRadius) //utanför varandras max ovalaradie = snabbt sätt att säga ingen collision.
			return false; 
		
	
		float ovalDist = GMath.getLength(dx,dy*viewPoint);
		
		/*
		float x1 = dx/ovalDistance * go1.getRadius(); //go1:s x värde på kanten mot go2
		float y1 = dy/ovalDistance * go1.getRadius(); //go1:s y värde på kanten mot go2
		
		float x2 = dx/ovalDistance * go2.getRadius(); //go2:s x värde på kanten mot go1
		float y2 = dy/ovalDistance * go2.getRadius(); //go2:s y värde på kanten mot go1
		
		float minDist = getLength(x1+x2,y1+y2); //minsta avstånd mellan object 1 och 2 i för hållande till vinkeln mellan dem
		*/
		
		/////////////KOMPIRMERAD VERSION//////////
		float minDist = totRadius*dist/ovalDist;
		///////////////////////////
		
		if(dist >= minDist) // objecten står precis bredvid varann eller förlångt bort för att kollidera
			return false;

		
		
		//////////////////ÖVER ELLER UNDER///////////////
		if(go1.getFootZPos() >= go2.getHeadZPos() || go2.getFootZPos() >= go1.getHeadZPos()) //om object 1 är över eller under object 2 ingen collision
			return false;
		//////////////////////////////////////////////
		
		///////////////GO1 landar pÂ GO2//////////////
		if(go1.getFootZPrev() > go2.getHeadZPrev()){
			go1.setFootZPos(go2.getHeadZPos());
			go1.landedOn(go2,world);
			go2.gotLandedOnBy(go1, world);
			if(go2.getZSpeed() < 0)
				go2.setZSpeed(0);
			return true;
		}
		//////////////////////////////////////////////
			
		/////////////////GO2 landar pÂ GO1/////////////////
		else if(go2.getFootZPrev() > go1.getHeadZPrev()){ //object två var över object 1
			go2.setFootZPos(go1.getHeadZPos());
			go2.landedOn(go1, world);
			go1.gotLandedOnBy(go2,world);
			if(go2.getZSpeed() < 0)
				go2.setZSpeed(0);
			return true;
		}
		//////////////////////////////////////////////

		
		/////////////////SIDCOLLISION
		float ww = Math.min(go1.getWeight(),go2.getWeight())/Math.max(go1.getWeight(),go2.getWeight()); // vad väger lättast i förhållande till tyngst
		
		if(go1.getWeight() == -1)
			go2.moveByVector((minDist-dist), -dx, -dy);
		else if(go2.getWeight() == -1)
			go1.moveByVector((minDist-dist), dx,dy);
		else if(go1.getWeight() > go2.getWeight()){
			go1.moveByVector((minDist-dist)*ww/2, dx,dy);
			go2.moveByVector((minDist-dist)-(minDist-dist)*ww/2, -dx,-dy);
		}
		else{
			go2.moveByVector((minDist-dist)*ww/2, -dx,-dy);
			go1.moveByVector((minDist-dist)-(minDist-dist)*ww/2, dx,dy);
		}
		
		//System.out.println(GMath.getDistance(go1.xPos, go1.getGroundPos(),go2.xPos,go2.getGroundPos()));
		go1.collidedWith(go2,world);
		go2.collidedWith(go1,world);
		
		//go1.setSpeed(0, 0);
		//go2.setSpeed(0, 0);
		return true;
		}

	
	public static boolean objectsOverlapp(WorldObject go1, WorldObject go2){
		
		if (go1.getRadius() == -1 || go2.getRadius() == -1)
			return false;
		//K÷R STRƒCK COLLISION AND SHIT////
		
		float dx = go1.getX() - go2.getX(); //från go2 till go1
		float dy = go1.getY() - go2.getY();
		
		float dist = getLength(dx,dy*viewPoint);
		
		float totRadius = go1.getRadius()+go2.getRadius();
		
		if(dist > totRadius) //utanför varandras max radie = snabbt sätt att säga ingen collision.
			return false; 
		else
			return true;
	}
	
	public static boolean isPosWithinTex(float x, float y, GSprite go){
		if(x >= go.getX() - go.getTexWidth()/2 &&  x <= go.getX() + go.getTexWidth()/2 && 
				y >= go.getY() - go.getTexHeight()/2 &&  y <= go.getY() + go.getTexHeight()/2)
			return true;
		else
			return false;
	}

	public static void handleGravity(WorldObject go, GameWorld world) {
		go.accelerate(0,0,-world.getGravity());
	}
	
	public static void handleGroundCollision(WorldObject go, GameWorld world){
		if(go.getFootZPos() < -0.0001){
			go.setFootZPos(0);
			go.setZSpeed(0);
			}
	}
	
	public static float calculateDamage(float speed, float weight,float multiplyer){
		return speed*weight*multiplyer/1000f;
	}
	
	public static float getWorldRadius(float radius, float xDir, float yDir){
		float ovalDistance = GMath.getLength(xDir,yDir*viewPoint);
		float distance = GMath.getLength(xDir,yDir);
		return radius* distance/ovalDistance;	
	}

}
