package game;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class GMath {
	public static float getDistance(float x1,float y1,float x2, float y2) {
		return (float) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
	}
	

	public static float random(float a, float b){
		return (float) (Math.random()*Math.abs(a-b) + Math.min(a, b));
	}
}
