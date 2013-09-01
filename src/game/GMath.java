package game;

import static game.GMath.getDistance;
import static java.lang.Math.asin;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;

public class GMath {
	public static float getDistance(float x1,float y1,float x2, float y2) {
		return (float) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
	}
	
	public static float getDistance(GObject go1,GObject go2) {
		return getDistance(go1.xPos, go1.yPos,go2.xPos, go2.yPos);
	}

	public static float random(float a, float b){
		return (float) (Math.random()*Math.abs(a-b) + Math.min(a, b));
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
