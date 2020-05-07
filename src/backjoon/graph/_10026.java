package backjoon.graph;

import java.util.Scanner;

public class _10026 {
	// N 			: 격자의 행과 열의 수 
	// MAT[][] 		: 격자
	// visited[][]	: 격자의 방문 여부	(0 : 방문X, 1  : 방문O)
	// dr, dc		: 동,남,서,북
	// cnt, cnt2	: 적록색약X, 적록색약O 에 대한 구역의 개수
	
	static int N;
	static String [][] MAT = new String[101][101];
	static int [][] visited = new int[101][101];
	static int [] dr = {0,1,0,-1};
	static int [] dc = {1,0,-1,0};
	static int cnt, cnt2;  		
	
	public static void dfs(int x, int y) {
		
		// 현재 방문한 위치의 색깔
		String nowRGB = MAT[x][y];
		// 종료조건
		
		// 탐색조건
		for (int i = 0; i < 4; i++) {
			int nxt_row = x + dr[i];
			int nxt_col = y + dc[i];
			// 격자안에 있는 지 확인
			if( 1 <= nxt_row && nxt_row <= N &&
					1 <= nxt_col && nxt_col <= N ) {
				if( visited[nxt_row][nxt_col] == 0 &&
						MAT[nxt_row][nxt_col].equals(nowRGB) ) {
					visited[nxt_row][nxt_col] = 1;
					dfs(nxt_row, nxt_col);
				}
			}
			
		}
		
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		for (int i = 1; i <= N; i++) {
			String [] str = sc.next().split("");		
			for (int j = 0; j < N; j++) {
				MAT[i][j+1] = str[j];
			}
		}
		
		// 1. 적록색약이 아닌 경우, 구역의 개수를 센다.
		// Graph - DFS		: 한 구역을 탐색할 때마다  cnt++
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 방문여부 확인
				if( visited[i][j] == 0 ) {
					visited[i][j] = 1;
					cnt++;
					dfs(i,j);
				}
			}
		}
		
		// 방문 여부 초기화 ( 적록색약인 경우의 탐색을 위해서 )
		visited = new int[101][101];
		
		
		// 2. 적록색약인 경우, 구역의 개수를 센다.
		// G -> R 수정 (G,R 통일한다.)
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if( MAT[i][j].equals("G") )
					MAT[i][j] = "R";
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				// 방문여부 확인
				if( visited[i][j] == 0 ) {
					visited[i][j] = 1;
					cnt2++;
					dfs(i,j);
				}
			}
		}
		
		System.out.println(cnt + " " + cnt2);
		
	}
}














