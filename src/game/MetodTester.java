package game;

public class MetodTester {
	public static void main(String[] arg){
		
		float[][] matrix = new float[4][5];
		
		matrix[0][0] = 1;
		matrix[0][1] = 1;
		matrix[0][2] = 1;
		matrix[0][3] = -1;
		matrix[0][4] = 1;
		
		matrix[1][0] = 1;
		matrix[1][1] = -1;
		matrix[1][2] = 1;
		matrix[1][3] = 1;
		matrix[1][4] = 1;
		
		matrix[2][0] = -1;
		matrix[2][1] = 1;
		matrix[2][2] = 1;
		matrix[2][3] = 1;
		matrix[2][4] = 1;
		
		matrix[3][0] = 1;
		matrix[3][1] = 1;
		matrix[3][2] = -1;
		matrix[3][3] = 1;
		matrix[3][4] = 1;
		
		float [] solutions = GMath.succesivElimination(matrix);
		System.out.println(" ");
		for(float s: solutions)
			System.out.println(s);
		

		
		
	}
}
