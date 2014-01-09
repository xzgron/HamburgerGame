package game;

import static java.lang.Math.*;
import static java.awt.geom.Line2D.*;
import static game.GMath.*;
import game.parts.GameWorld;

import org.lwjgl.input.Mouse;

import world.WorldObject;
import world.objects.GFood;
public class GPhysics {
	
	
	
	public static boolean handleCollision(WorldObject go1, WorldObject go2) {
		if (go1.isSurface()||go2.isSurface())
			return false;
		
		if (go1.getRadius() == -1 || go2.getRadius() == -1)
			return false;
		//KÖR STRÄCK COLLISION AND SHIT////
		
		float dx = go1.getX() - go2.getX(); //frŒn go2 till go1
		float dy = go1.getY() - go2.getY();
		
		float dist = getLength(dx,dy); //avstŒndet mellan objekten
		
		float totRadius = go1.getRadius()+go2.getRadius(); //totala minradien mellan objekten
		
		
		
		if(dist >= totRadius) //utanfšr varandras max ovalaradie = snabbt sŠtt att sŠga ingen collision.
			return false; 
		
	
		float ovalDist = GMath.getLength(dx,dy*2);
		
		/*
		float x1 = dx/ovalDistance * go1.getRadius(); //go1:s x vŠrde pŒ kanten mot go2
		float y1 = dy/ovalDistance * go1.getRadius(); //go1:s y vŠrde pŒ kanten mot go2
		
		float x2 = dx/ovalDistance * go2.getRadius(); //go2:s x vŠrde pŒ kanten mot go1
		float y2 = dy/ovalDistance * go2.getRadius(); //go2:s y vŠrde pŒ kanten mot go1
		
		float minDist = getLength(x1+x2,y1+y2); //minsta avstŒnd mellan object 1 och 2 i fšr hŒllande till vinkeln mellan dem
		*/
		
		/////////////KOMPIRMERAD VERSION//////////
		float minDist = totRadius*dist/ovalDist;
		///////////////////////////
		
		if(dist >= minDist) // objecten stŒr precis bredvid varann eller fšrlŒngt bort fšr att kollidera
			return false;

		
		
		//////////////////…VER ELLER UNDER///////////////
		if(go1.getFootZPos() >= go2.getHeadZPos() || go2.getFootZPos() >= go1.getHeadZPos()) //om object 1 Šr šver eller under object 2 ingen collision
			return false;
		//////////////////////////////////////////////
		
		///////////////GO1 landar på GO2//////////////
		if(go1.getFootZPrev() > go2.getHeadZPrev()){
			go1.setFootZPos(go2.getHeadZPos());
			go1.landedOn(go2);
			go2.gotLandedOnBy(go1);
			return true;
		}
		//////////////////////////////////////////////
			
		/////////////////GO2 landar på GO1/////////////////
		else if(go2.getFootZPrev() > go1.getHeadZPrev()){ //object tvŒ var šver object 1
			go2.setFootZPos(go1.getHeadZPos());
			go2.landedOn(go1);
			go1.gotLandedOnBy(go2);
			return true;
		}
		//////////////////////////////////////////////

		
		/////////////////SIDCOLLISION
		float ww = Math.min(go1.getWeight(),go2.getWeight())/Math.max(go1.getWeight(),go2.getWeight()); // vad vŠger lŠttast i fšrhŒllande till tyngst
		
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
		go1.collidedWith(go2);
		go2.collidedWith(go1);
		
		//go1.setSpeed(0, 0);
		//go2.setSpeed(0, 0);
		return true;
		}

	
	public static boolean objectsOverlapp(WorldObject go1, WorldObject go2){
		
		if (go1.getRadius() == -1 || go2.getRadius() == -1)
			return false;
		//KÖR STRÄCK COLLISION AND SHIT////
		
		float dx = go1.getX() - go2.getX(); //frŒn go2 till go1
		float dy = go1.getY() - go2.getY();
		
		float dist = getLength(dx,dy*2);
		
		float totRadius = go1.getRadius()+go2.getRadius();
		
		if(dist > totRadius) //utanfšr varandras max radie = snabbt sŠtt att sŠga ingen collision.
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

	public static void useGravity(WorldObject go) {
		go.accelerate(0,0,-Main.game.world.getGravity());
	}
	
	public static void handleGroundCollision(WorldObject go){
		if(go.getFootZPos() < -0.0001){
			go.setFootZPos(0);
			go.setZSpeed(0);
			}
	}
	
	public static float calculateDamage(float speed, float weight,float multiplyer){
		return speed*weight*multiplyer/1000f;
	}
}
