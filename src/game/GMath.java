package game;

import static java.lang.Math.*;
import game.input.GMouse;
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
}
