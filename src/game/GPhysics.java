package game;

import static java.lang.Math.*;
import static java.awt.geom.Line2D.*;
import static game.GMath.*;

import org.lwjgl.input.Mouse;

import worldObjects.food.GFood;
public class GPhysics {
	
	
	
	public static void handleCollision(GWorldObject go1, GWorldObject go2) {
		
		if (go1.getRadie() == -1 || go2.getRadie() == -1)
			return;
		//KÖR STRÄCK COLLISION AND SHIT////
		
		float dist = GMath.getDistance(go1.xPos, go1.getGroundPos(),go2.xPos,go2.getGroundPos());
		float totRadie = go1.getRadie()+go2.getRadie();
		
		System.out.println(go1.getPrevGroundPos());
		if(dist > totRadie)
			return;
		
		if(go1.getFootPos() > go2.getHeadPos() ||  go2.getFootPos() > go1.getHeadPos()   )
			return;
		
	
		
		
		//GO1
		
		//rörelsevinkel
		/*
		float a1 = GMath.getAngle(go1.xPos, go1.getGroundPos(), go1.getXPrev(), go1.getPrevGroundPos());
		float a2 = GMath.getAngle(go2.xPos, go2.getGroundPos(), go2.getXPrev(), go2.getPrevGroundPos());	
		*/
		//objectvinkel
		float oa = GMath.getAngle(go1.xPos,go1.getGroundPos(),go2.xPos,go2.getPrevGroundPos());
		
		if(go1.getWeight()<go2.getWeight())
			go1.moveByAngle((totRadie-dist), oa+180);
		else
			go2.moveByAngle((totRadie-dist), oa);

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
	


	
	public static boolean checkCollision(GWorldObject go1, GWorldObject go2){
		if(getDistance(go1.getX(), go1.getY(), go2.getX(), go2.getY()) > go1.getRadie() + go2.getRadie())
			return true;
		else
			return false;
	}
	
	public static boolean isPosWithinTex(float x, float y, GObject go){
		if(x >= go.getX() - go.getTexWidth()/2 &&  x <= go.getX() + go.getTexWidth()/2 && 
				y >= go.getY() - go.getTexHeight()/2 &&  y <= go.getY() + go.getTexHeight()/2)
			return true;
		else
			return false;
	}

	public static void handleGravity(GFood gf) {
		if(gf.getZ() > 0)
			gf.setZSpeed(gf.getZSpeed() - GWorld.gravity * Game.deltaTime);
		
		gf.setZ(gf.getZ() + gf.getZSpeed());
		
		if (gf.getZ() < 0)
			gf.setZ(0);
		
		// TODO Auto-generated method stub
		
	}
}
