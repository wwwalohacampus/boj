package backjoon.saumsung;

import java.util.Scanner;

public class samsung_12100 {
	
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;
	
	static int N;
	static int[][] MAT;
	static int Answer = 0;
	
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		MAT = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				MAT[i][j] = sc.nextInt();
			}
		}

		for (int i = 1; i <= 4; i++) {
			dfs(0, i, MAT);
		}
		
		System.out.println(Answer);
	}
	
	public static void dfs(int depth, int d, int[][] MAT) {
		// 1. 병합 전 이동
		int[][] tempMAT = moveMAT(d, MAT);
		// 2. 병합
		tempMAT = mergeMAT(d, tempMAT);
		// 3. 병합 후 이동
		tempMAT = moveMAT(d, tempMAT);
		
		// 종료조건
		if( ++depth == 5 ) {
			countMax(tempMAT);
			return;
		}
		
		// UP,DOWN,LEFT,RIGHT dfs() 탐색
		for (int i = 1; i <= 4; i++) {
			dfs(depth, i, tempMAT );
		}
		
		
	}
	
	
	public static int[][] moveMAT(int d, int[][] MAT){
		int[][] moveMAT = new int[N][N];
		
		// 이동방향에 따라 
		switch (d) {
			case UP:
					// UP 이동
					for (int j = 0; j < N; j++) {
						int nxt_row = 0;
						for (int i = 0; i < N; i++) {
							if(MAT[i][j] != 0)
								moveMAT[nxt_row++][j] = MAT[i][j];
						}
					}	
					break;
			case DOWN:
					// DOWN 이동
					for (int j = 0; j < N; j++) {
						int nxt_row = N - 1;
						for (int i = N - 1; i >= 0; i--) {
							if(MAT[i][j] != 0)
								moveMAT[nxt_row--][j] = MAT[i][j];
						}
					}
					break;
			case LEFT:
					// LEFT 이동
					for (int i = 0; i < N; i++) {
						int nxt_col = 0;
						for (int j = 0; j < N; j++) {
							if(MAT[i][j] != 0)
								moveMAT[i][nxt_col++] = MAT[i][j];
						}
					}
					break;
			case RIGHT:
					// RIGHT 이동
					for (int i = 0; i < N; i++) {
						int nxt_col = N - 1;
						for (int j = N - 1; j >= 0; j--) {
							if(MAT[i][j] != 0)
								moveMAT[i][nxt_col--] = MAT[i][j];
						}
					}
				
					break;
		}
		
		return moveMAT;
		
	}
	
	
	public static int[][] mergeMAT (int d, int[][] moveMAT){
		
		// 이동방향에 따라 
		switch (d) {
			case UP:
					// UP 이동
					for (int j = 0; j < N; j++) {
						int pre = 0;
						for (int i = 0; i < N; i++) {
							if( pre == 0 || (pre != 0 && pre != moveMAT[i][j] )) {
								pre = moveMAT[i][j];
							}
							else if(pre == moveMAT[i][j]) {
								moveMAT[i][j] += pre;
								moveMAT[i-1][j] = 0;
								pre = 0;
							}
						}
					}
					break;
			case DOWN:
					// DOWN 이동
					for (int j = N - 1; j >= 0; j--) {
						int pre = 0;
						for (int i = N - 1; i >= 0; i--) {
							if( pre == 0 || (pre != 0 && pre != moveMAT[i][j] )) {
								pre = moveMAT[i][j];
							}
							else if(pre == moveMAT[i][j]) {
								moveMAT[i][j] += pre;
								moveMAT[i+1][j] = 0;
								pre = 0;
							}
						}
					}
					break;
			case LEFT:
					// LEFT 이동
					for (int i = 0; i < N; i++) {
						int pre = 0;
						for (int j = 0; j < N; j++) {
							if( pre == 0 || (pre != 0 && pre != moveMAT[i][j] )) {
								pre = moveMAT[i][j];
							}
							else if(pre == moveMAT[i][j]) {
								moveMAT[i][j] += pre;
								moveMAT[i][j-1] = 0;
								pre = 0;
							}
						}
					}
					break;
			case RIGHT:
					// RIGHT 이동
					for (int j = N - 1; j >= 0; j--) {
						int pre = 0;
						for (int i = N - 1; i >= 0; i--) {
							if( pre == 0 || (pre != 0 && pre != moveMAT[i][j] )) {
								pre = moveMAT[i][j];
							}
							else if(pre == moveMAT[i][j]) {
								moveMAT[i][j] += pre;
								moveMAT[i][j+1] = 0;
								pre = 0;
							}
						}
					}
					break;
		}
		
		return moveMAT;
	}
	
	
	public static void countMax(int[][] tempMAT) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				Answer = Math.max(Answer, tempMAT[i][j] );
			}
		}
		
	}
	
}


















