package game;

public class MetodTester {
	public static void main(String[] arg){
		
		float[][] matrix = new float[4][5];
		
		matrix[0][0] = 4;
		matrix[0][1] = 0;
		matrix[0][2] = 3;
		matrix[0][3] = 0;
		matrix[0][4] = 1;
		
		matrix[1][0] = 0;
		matrix[1][1] = 4;
		matrix[1][2] = 0;
		matrix[1][3] = 3;
		matrix[1][4] = 0;
		
		matrix[2][0] = 6;
		matrix[2][1] = 0;
		matrix[2][2] = 2;
		matrix[2][3] = 0;
		matrix[2][4] = 0;
		
		matrix[3][0] = 0;
		matrix[3][1] = 6;
		matrix[3][2] = 0;
		matrix[3][3] = 2;
		matrix[3][4] = 1;
		
		float [] solutions = GMath.succesivElimination(matrix);
		System.out.println(" ");
		for(float s: solutions)
			System.out.println(s);
		

		
		
	}
}
