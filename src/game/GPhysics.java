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
		
		if (go1.getRadie() == -1 || go2.getRadie() == -1)
			return false;
		//KÖR STRÄCK COLLISION AND SHIT////
		
		
		float dist = GMath.getDistance(go1.xPos, go1.getGroundYPos(),go2.xPos,go2.getGroundYPos());
		
		if(dist >= go1.getRadie()+go2.getRadie())
			return false;
		
		float oa = GMath.getAngle(go1.xPos,go1.getGroundYPos(),go2.xPos,go2.getPrevGroundYPos());
		
		
		
		float x1 = (float) cos(toRadians(oa))*go1.getRadie();
		float y1 = (float) sin(toRadians(oa))*go1.getRadie()/2;
		float r1 = GMath.getDistance(0,0,x1,y1);
		
		float x2 = (float) cos(toRadians(oa))*go2.getRadie();
		float y2 = (float) sin(toRadians(oa))*go2.getRadie()/2;
		float r2 = GMath.getDistance(0,0,x2,y2);
		

		
		float totRadie = r1+r2;
		
		
	
		
		if(dist >= totRadie)
			return false ;
		
		if(go1.getFootZPos() > go2.getHeadZPos() || go2.getFootZPos() > go1.getHeadZPos())
			return false;
	
		////GO1 landar på GO2///
		if(go1.getPrevFootZPos() > go2.getHeadZPos()|| go2.getPrevHeadZPos() < go1.getFootZPos()){
			System.out.println("landed on");
			go1.landedOn((GFood) go2);
			go1.setFootZPos(go2.getHeadZPos());
			go2.setZSpeed(0);
			return true;
		}
		////GO2 landar på GO1///
		else if(go2.getPrevFootZPos() > go1.getHeadZPos() || go1.getPrevHeadZPos() < go2.getFootZPos() ){
			System.out.println("landed on");
			go2.landedOn((GFood) go1);
			go2.setFootZPos(go1.getHeadZPos());
			go1.setZSpeed(go2.getZSpeed());
			return true;
		}

		
		
		float ww = Math.min(go1.getWeight(),go2.getWeight())/Math.max(go1.getWeight(),go2.getWeight()); // hur mycket mer väger lättast än tyngst
		

		
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
		}
		//System.out.println(GMath.getDistance(go1.xPos, go1.getGroundPos(),go2.xPos,go2.getGroundPos()));
		return true;
		}
		//go2.setSpeedByAngle(go2.getXYSpeed(), a2 + (a2-a1));*/
		/*
		float px1 = go1.getXPrev();
		float py1 = go1.getPrevGroundPos();
		

		
		float px2 = go2.getXPrev();
		float py2 = go2.getPrevGroundPos();
		
		float dx1 = go1.getXSpeed();
		float dy1 = go1.getYSpeed();
	
		float dx2 = go2.getXSpeed();
		float dy2 = go2.getYSpeed();
		
		//inte riktigt dx och dy
		float dx = dx1-dx2;
		float dy = dy1-dy2;
		
		float q = -(totRadie*totRadie + px1*px2 + py1*py2 - px1*px1-py1*py1-px2*px2-py2*py2)/(dx+dy);
		
		float p = 2*(dx*px1-dx*px2+dy*py1-dy*py2)/(dx+dy);
		
		System.out.println(p);
		System.out.println(q);
		
		float t1 = (float) (-p/2 + Math.sqrt((p/2)*(p/2) - q));
		float t2 = (float) (-p/2 - Math.sqrt((p/2)*(p/2) - q));
		
		System.out.println(t1);
		System.out.println(t2);
		go1.setPosition(go1.getXPrev() + go1.getXSpeed()*Game.deltaTime*t1, go1.getPrevGroundPos() + go1.getYSpeed()*Game.deltaTime*t1);
		
		go2.setPosition(go2.getXPrev() + go2.getXSpeed()*Game.deltaTime*t1, go2.getPrevGroundPos() + go2.getYSpeed()*Game.deltaTime*t1);
		
		//go1.setSpeedByAngle(go1.getXYSpeed(), a2 + (a2-a1));
		/*
		
	}

	/*
	 * if()
	 * 
	 * p1[sp*2]; p1[sp*2+1]; p1[(sp*2+2]; p1[(sp*2+3];
	 */


/*
	public static boolean overlay(GObject go1, GObject go2){
		for(GPoint p: go1.getPoints()){
			if(p.isWithin(go2))
				return true;
		}
		for(GPoint p: go2.getPoints()){
			if(p.isWithin(go1))
				return true;
		}
		
		return false;
	}



	/*
	public static float[] getPosInRotGraph(float a, GPoint p){
		float x = p.getX();
		float y = p.getY();
		
		float hyp = getDistance(0,0,x,y);
		
		float orgA = (float) toDegrees(acos(x/hyp));
		
		if (y <= 0)
			orgA += 180;
		
		float newA = orgA + a;
				
		float newX = (float) (cos(toRadians(newA)) * hyp);
				
		float newY = (float) (sin(toRadians(newA)) * hyp);

		return new float[]{newX,newY};
	}
	*/
	
	/* dx = hyp * sin(angle)
	 * angle = asin(dx/hyp)
	 * 
	 * dy = hyp * cos(angle) 
	 * angle = acos(dy/hyp)
	 * 
	 * 
	 * */
	


	
	public static boolean checkCollision(WorldObject go1, WorldObject go2){
		if(getDistance(go1.getX(), go1.getY(), go2.getX(), go2.getY()) > go1.getRadie() + go2.getRadie())
			return true;
		else
			return false;
	}
	
	public static boolean isPosWithinTex(float x, float y, GSprite go){
		if(x >= go.getX() - go.getTexWidth()/2 &&  x <= go.getX() + go.getTexWidth()/2 && 
				y >= go.getY() - go.getTexHeight()/2 &&  y <= go.getY() + go.getTexHeight()/2)
			return true;
		else
			return false;
	}

	public static void useGravity(GFood gf) {
		if(gf.isInAir())
			gf.accelerate(0,0,-GameWorld.getGravity());

		
		if (gf.getZ() < 0){
			gf.setZ(0);
			gf.setZSpeed(0);
			}
		
		// TODO Auto-generated method stub
		
	}
}
