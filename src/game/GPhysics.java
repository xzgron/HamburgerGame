package game;

import static java.lang.Math.*;
import static java.awt.geom.Line2D.*;

public class GPhysics {
/*
	public static void handleCollision(GObject go1, GObject go2) {
		if(getDistance(go1,go2) > go1.getHyp()+go2.getHyp())
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

	
	public static float getDistance(GObject go1, GObject go2) {
		return getDistance(go1.getX(),go1.getY(),go2.getX(), go2.getY());
	}

	public static float getDistance(GPoint p1, GPoint p2) {
		return getDistance(p1.getX(),p1.getY(),p2.getX(), p2.getY());
	}
	*/

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
	
	public static float getDistance(float x1,float y1,float x2, float y2) {
		return (float) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
	}
	
	public static float getAngle(float x1,float y1,float x2, float y2){
		float dx = x2-x1;
		float dy = y2-y1;
		float hyp = getDistance(x1,y1,x2,y2);
		
		float angle = (float) (toDegrees(asin(dx/hyp)));
		
		if (dy <= 0)
			angle = 180-angle;
		if (angle < 0)
			angle += 360;
		return angle;

	}
}
