package game;

import static java.lang.Math.*;
import game.tools.GMouse;
import world.WorldObject;

public class GMath {
	public static float getLength(float x, float y){
		return (float)sqrt(x*x+y*y);	
	}
	
	public static float getLength(float x, float y,float z){
		return (float)sqrt(x*x+y*y + z*z);	
	}
	
	public static float getDistance(float x1,float y1,float x2, float y2) {
		return (float) sqrt(pow(x1 - x2, 2) + pow(y1 - y2, 2));
	}
	
	public static float getDistance(GSprite go1,GSprite go2) {
		return getDistance(go1.getX(), go1.getY(), go2.getX(), go2.getY());
	}

	public static float random(float a, float b){
		return (float) (Math.random()*Math.abs(a-b) + Math.min(a, b));
	}
	
	public static float getAngle(float x1,float y1,float x2, float y2){
		float dx = x2-x1;
		float dy = y2-y1;
		float hyp = getDistance(x1,y1,x2,y2);
		float angle = (float) (toDegrees(asin(dy/hyp)));
		
		if (dx <= 0)
			angle = 180-angle;
		if (angle < 0)
			angle += 360;
	
		//System.out.println(angle);
		return angle;

	}

	public static float getAngle(GSprite go1, GSprite go2) {
		return getAngle(go1.getX(),go1.getY(),go2.getX(),go2.getY());
	}
	
	public static float getSphereVolume(float radius){
		return (float) (4/3*PI*pow(radius,3));
	}
	
	
	
	public static float[] succesivElimination(float[][] matrix){
		int p = matrix.length; // height
		int n = matrix[0].length-1; // antal obekanta (width)
		
		//UTSKRIVNING
		for(int i = 0; i <p; i++){
			String s = "";
			for(int j = 0; j < n+1; j++){
				s += matrix[i][j] + "  ";
			}
			System.out.println(s);
		}
	
		System.out.println(" ");
		
		for(int i = 0; i < p-1; i++){ //ekvationen som andra ska subtraheras med
			for(int j = i+1; j < p; j++){ //vilken ekvation som ska ändras
				float var = matrix[j][i]/matrix[i][i];
				for(int k = 0; k < n+1; k++){ //vilken variabel som ska subtraheras
					matrix[j][k] -= matrix[i][k]*var;
				}
			}
		}
		//UTSKRIVNING
		for(int i = 0; i < p; i++){
			String s = "";
			for(int j = 0; j < n+1; j++){
				s += matrix[i][j] + "  ";
			}
			System.out.println(s);
		}
	
		
		float[] solutions = new float[n];

		for(int i = solutions.length-1; i >= 0; i--){
			solutions[i]=matrix[i][n];
			for(int j = solutions.length-1; j > i; j--)
				solutions[i] -= solutions[j]*matrix[i][j];
			
			solutions[i] /= matrix[i][i];
		}
		
		
		return solutions;
		
	}
	
	public static boolean isPosWithinSquare(float x,float y, float sx , float sy, float sw, float sh){
		return (x >= sx - sw/2 && x <= sx+sw/2 && y >= sy - sh/2 && y <= sy+sh/2);
	}
	
	public static float[] lineIntersectionPoint(float l1x1, float l1y1, float l1x2, float l1y2,float l2x1, float l2y1, float l2x2, float l2y2){
		/////func 1////
		
		//frŒn 1 till 2
		float v1x = l1x2-l1x1;
		float v1y = l1y2-l1y1;
		float v2x = l2x2-l2x1;
		float v2y = l2y2-l2y1;

		/*
		float c1 = v1x*l1y1 - v1y*l1x1;
		float c2 = v2x*l2y1 - v2y*l2x1;
		*/ 		//normalForm = v1y*x - v1x*y + v1x*l1y1 - v1y*l1x1

		/////// l1v1 + t*v1 = l2v1 + t*v2
		// l1x1 + t*v1x = l2x1 + t*v2x
		// l1y1 + t*v1y = l2y1 + t*v2y
		// 
		// l1x1 - l2y1 + t(v1x - v2x) = 0.
	
		
	}
	
	public static boolean areLinesParallell(float l1x1, float l1y1, float l1x2, float l1y2,float l2x1, float l2y1, float l2x2, float l2y2){
		float v1x = l1x1-l1x2;
		float v1y = l1y1-l1y2;
		
		float v2x = l2x1-l2x2;
		float v2y = l2y1-l2y2;
		
		float absV1 = getLength(v1x,v1y);
		float absV2 = getLength(v2x,v2y);
		if(almostEquals(abs(v1x)/absV1, abs(v2x)/absV2) && almostEquals(abs(v1y)/absV1, abs(v2y)/absV2))
			return true;
		else 
			return false;
	}
	
	public static boolean almostEquals(float a, float b){
		if(a >= b -0.0001 && a <= b + 0.0001)
			return true;
		else
			return false;
	}
}
