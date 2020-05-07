package backjoon.saumsung;

import java.util.Scanner;

public class samsung_14501 {
	static int[][] MAT;
	static int Answer = Integer.MIN_VALUE;
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		
		MAT = new int[N+1][2];
		
		for (int i = 0; i < N; i++) {
			MAT[i][0] = sc.nextInt();		// 상담하는 데 걸리는 일수
			MAT[i][1] = sc.nextInt();		// 상담금액
		}
		
		dfs(N, 0, 0);
		System.out.println(Answer);
	}
	
	public static void dfs(int n, int depth, int sum) {
		if(depth == n) {
			Answer = Math.max(Answer, sum);
			return;
		}
		
		if( depth + MAT[depth][0] <= n ) {
			dfs(n, depth + MAT[depth][0], sum + MAT[depth][1] );
		}
		dfs(n, depth+1, sum);
	}
	
}
