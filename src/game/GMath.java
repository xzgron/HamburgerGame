package game;

import static game.GMath.getDistance;
import static java.lang.Math.asin;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import static java.lang.Math.toDegrees;
import worldObjects.food.GFood;
import worldObjects.food.Hamburger;

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
		float angle = (float) (toDegrees(asin(dy/hyp)));
		
		if (dx <= 0)
			angle = 180-angle;
		if (angle < 0)
			angle += 360;
	
		//System.out.println(angle);
		return angle;

	}

	public static float getAngle(GWorldObject go1, GWorldObject go2) {
		return getAngle(go1.getX(),go1.getGroundYPos(),go2.getX(),go2.getGroundYPos());
	}
	
	
	
	public static float[] succesivElimination(float[][] matrix){
		int p = matrix.length; // height
		int n = matrix[0].length-1; // antal obekanta (width)
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
		
		for(int i = 0; i <p; i++){
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
	
}
