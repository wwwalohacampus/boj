package backjoon.test;

import java.util.Scanner;

public class moveTest {
	
	static int N;
	static int [][] MAT;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		MAT = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				MAT[i][j] = sc.nextInt();
			}
		}
		
		int[][] movedMAT = moveUp(MAT);
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(movedMAT[i][j]);
			}
			System.out.println();
		}
		
	}
	
	public static int[][] moveUp(int[][] MAT){
		int[][] moveMAT = new int[N][N];
		
		for (int j = 0; j < N; j++) {
			int nxt_row = 0;
			for (int i = 0; i < N; i++) {
				if(MAT[i][j] != 0)
					moveMAT[nxt_row++][j] = MAT[i][j];
			}
		}
		
		return moveMAT;
	}
	
}


















