package backjoon.saumsung;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class samsung_14888_2 {
	static int N;						// 입력 숫자의 개수
	static int [] arr;					// 입력 숫자 값
	static int [] op = new int[4];		// 연산자의 개수
	static ArrayList<Integer> list;		// 모든 연산 결과 list
	
	public static void dfs(int depth, int sum) {
		
		// 연산자 개수 확인
		for (int i = 0; i < op.length; i++) {
			// 연산자 개수 남아있으면
			if( op[i] != 0 ) {
				// 해당 연산자를 하나 쓸거니까 연산자 개수 -1 감소
				op[i]--;
				// 연산자 구분 ( + : 0 , - : 1, * : 2, / : 3 )
				switch (i) {
				case 0:	dfs(depth+1, sum + arr[depth]);
						break;
				case 1:	dfs(depth+1, sum - arr[depth]);
						break;
				case 2:	dfs(depth+1, sum * arr[depth]);
						break;
				case 3:	dfs(depth+1, sum / arr[depth]);
						break;
				}
				// 다음 케이스를 위해서 연산자 개수 원복
				op[i]++;
			}
		}
		
		if( depth == N ) {
			// 계산결과를 리스트에 담는다
			list.add(sum);
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N];
		list = new ArrayList<Integer>();
		
		// 숫자 입력
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		
		// 연산자의 개수 입력
		for (int i = 0; i < op.length; i++) {
			op[i] = sc.nextInt();
		}
				
		// dfs 탐색을 통해 연산
		// 종료조건 depth == N
		int depth = 1;
		dfs( depth, arr[0] );

		Collections.sort(list);
		int max = list.get(list.size()-1);
		int min = list.get(0);
		
		System.out.println(max);
		System.out.println(min);
		
	}
}
















