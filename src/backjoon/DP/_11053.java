package backjoon.DP;

import java.util.ArrayList;
import java.util.Scanner;

// 가장 긴 증가하는 수열
public class _11053 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int result = 0;
		ArrayList<Integer> data = new ArrayList<Integer>();
		int[] dp = new int[1000];
		
		for (int i = 0; i < N; i++) {
			data.add(sc.nextInt());
		}
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if( data.get(j) < data.get(i) ) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			result = Math.max(result, dp[i]);
		}
		System.out.println(result);
		
		sc.close();
	}
}









