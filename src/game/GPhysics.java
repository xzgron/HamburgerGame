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
		//K�R STR�CK COLLISION AND SHIT////
		
		if(GMath.getDistance(go1,go2) > go1.getRadie()+go2.getRadie())
			return;
		
		
		
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



	// gives x value at line stuff
	public static float getX(GPoint a, GPoint b, GPoint c) {
		float vab = getDistance(a, b);
		float x = (float) (c.orgy / atan(vab));
		return x;
	}

	public static float getY(GPoint a, GPoint b, GPoint c) {
		float vab = getDistance(a, b);
		float y = (float) (c.orgx * atan(vab));
		return y;
	}

	public static float getV(GPoint p1, GPoint p2) {
		float dx = p1.getX() - p2.getX();
		float hyp = getDistance(p1,p2);
		return (float) acos(dx/hyp);
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
