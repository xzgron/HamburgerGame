package game;

import static java.lang.Math.*;
import static java.awt.geom.Line2D.*;
import static game.GMath.*;
import game.parts.GameWorld;

import org.lwjgl.input.Mouse;

import world.WorldObject;
import world.objects.food.GFood;
public class GPhysics {
	
	
	
	public static boolean handleCollision(WorldObject go1, WorldObject go2) {
		
		if (go1.getRadius() == -1 || go2.getRadius() == -1)
			return false;
		//K�R STR�CK COLLISION AND SHIT////
		
		
		float dist = GMath.getDistance(go1.xPos, go1.getGroundYPos(),go2.xPos,go2.getGroundYPos());
		
		if(dist >= go1.getRadius()+go2.getRadius()) //utanf�r varandras max radie = snabbt s�tt att s�ga ingen collision.
			return false; 
		
		//float oa = GMath.getAngle(go1.xPos,go1.getGroundYPos(),go2.xPos,go2.getPrevGroundYPos()); // vinkel fr�n object 1 till 2
	/*
		float x1 = (float) cos(toRadians(oa))*go1.getRadius();
		float y1 = (float) sin(toRadians(oa))*go1.getRadius()/2;
		float r1 = GMath.getDistance(0,0,x1,y1);
		
		float x2 = (float) cos(toRadians(oa))*go2.getRadius();
		float y2 = (float) sin(toRadians(oa))*go2.getRadius()/2;
		float r2 = GMath.getDistance(0,0,x2,y2);
		*/

		float dx = go1.getX() - go2.getX(); //fr�n go2 till go1
		float dy = go1.getGroundYPos() - go2.getGroundYPos();
		
		float ovalDistance = GMath.getDistance(0,0,dx,dy*2);
		
		float x1 = dx/ovalDistance * go1.getRadius();
		float y1 = dy/ovalDistance * go1.getRadius();
		float r1 = GMath.getDistance(0,0,x1,y1);
		
		float x2 = dx/ovalDistance * go2.getRadius();
		float y2 = dy/ovalDistance * go2.getRadius();
		float r2 = GMath.getDistance(0,0,x2,y2);
		
		float totRadie = r1+r2; //max avst�nd mellan object 1 och 2 i f�r h�llande till vinkeln mellan dem
		
		
	
		
		if(dist >= totRadie) // objecten st�r precis bredvid varann eller f�rl�ngt bort f�r att kollidera
			return false ;
		
		if(go1.getFootZPos() >= go2.getHeadZPos() || go2.getFootZPos() >= go1.getHeadZPos()) //om object 1 �r �ver eller under object 2
			return false;
	
		////GO1 landar p� GO2///
		if(go1.getFootZPrev() > go2.getHeadZPos() || go2.getHeadZPrev() < go1.getFootZPos()){
			System.out.println("landed on");
			go1.setFootZPos(go2.getHeadZPos());
			go2.setZSpeed(0);
			go1.setZSpeed(0);
			go1.landedOn(go2);
			return true;
		}
		////GO2 landar p� GO1///
		else if(go2.getFootZPrev() > go1.getHeadZPos() || go1.getHeadZPrev() < go2.getFootZPos() ){
			System.out.println("landed on");	
			go2.setFootZPos(go1.getHeadZPos());
			go2.setZSpeed(0);
			go1.setZSpeed(0);
			go2.landedOn(go1);
			return true;
		}

		
		///SIDCOLLISION
		float ww = Math.min(go1.getWeight(),go2.getWeight())/Math.max(go1.getWeight(),go2.getWeight()); // vad v�ger l�ttast i f�rh�llande till tyngst
		

		/*
		if(go1.getWeight() == -1)
			go2.moveByAngle((totRadie-dist), oa);
		else if(go2.getWeight() == -1)
			go1.moveByAngle((totRadie-dist), oa+180);
		else if(go1.getWeight() > go2.getWeight()){
			go1.moveByAngle((totRadie-dist)*ww/2, oa+180);
			go2.moveByAngle((totRadie-dist)-(totRadie-dist)*ww/2, oa);
		}
		else{
			go2.moveByAngle((totRadie-dist)*ww/2, oa);
			go1.moveByAngle((totRadie-dist)-(totRadie-dist)*ww/2, oa+180);
		}*/
			
		if(go1.getWeight() == -1)
			go2.moveByVector((totRadie-dist), -dx, -dy);
		else if(go2.getWeight() == -1)
			go1.moveByVector((totRadie-dist), dx,dy);
		else if(go1.getWeight() > go2.getWeight()){
			go1.moveByVector((totRadie-dist)*ww/2, dx,dy);
			go2.moveByVector((totRadie-dist)-(totRadie-dist)*ww/2, -dx,-dy);
		}
		else{
			go2.moveByVector((totRadie-dist)*ww/2, -dx,-dy);
			go1.moveByVector((totRadie-dist)-(totRadie-dist)*ww/2, dx,dy);
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
		//K�R STR�CK COLLISION AND SHIT////
		
		
		float dist = GMath.getDistance(go1.xPos, go1.getGroundYPos(),go2.xPos,go2.getGroundYPos());
		
		if(dist >= go1.getRadius()+go2.getRadius()) //utanf�r varandras max radie = snabbt s�tt att s�ga ingen collision.
			return false;

		float dx = go1.getX() - go2.getX(); //fr�n go2 till go1
		float dy = go1.getGroundYPos() - go2.getGroundYPos();
		
		float ovalDistance = GMath.getDistance(0,0,dx,dy*2);
		
		float x1 = dx/ovalDistance * go1.getRadius();
		float y1 = dy/ovalDistance * go1.getRadius();
		float r1 = GMath.getDistance(0,0,x1,y1);
		
		float x2 = dx/ovalDistance * go2.getRadius();
		float y2 = dy/ovalDistance * go2.getRadius();
		float r2 = GMath.getDistance(0,0,x2,y2);
		
		float totRadie = r1+r2; //max avst�nd mellan object 1 och 2 i f�r h�llande till vinkeln mellan dem
		
		if(dist >= totRadie) // objecten st�r precis bredvid varann eller f�rl�ngt bort f�r att kollidera
			return false ;
		
		return true;
	}
	
	public static boolean isPosWithinTex(float x, float y, GSprite go){
		if(x >= go.getX() - go.getTexWidth()/2 &&  x <= go.getX() + go.getTexWidth()/2 && 
				y >= go.getY() - go.getTexHeight()/2 &&  y <= go.getY() + go.getTexHeight()/2)
			return true;
		else
			return false;
	}

	public static void useGravity(GFood gf) {
		gf.accelerate(0,0,-GameWorld.getGravity());
	}
}
