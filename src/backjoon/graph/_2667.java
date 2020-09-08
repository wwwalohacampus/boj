package backjoon.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class _2667 {
	static int N;
	static int[][] MAT;
	static int[][] visited;
	static int[] dr = {-1,0,1,0};
	static int[] dc = {0,1,0,-1};
	static int home;
	static ArrayList<Integer> homes = new ArrayList<Integer>();
	
	public static void dfs(int x, int y) {
		// 방문
		visited[x][y] = 1;
		home++;
		
		// 종료조건
		
		
		// 탐색조건
		for (int i = 0; i < 4; i++) {
			int nx = x + dr[i];
			int ny = y + dc[i];
			
			if( nx >= 1 && nx <= N && ny >= 1 && ny <= N ) {
				if( visited[nx][ny] == 0 && MAT[nx][ny] == 1 ) {
					dfs( nx, ny );
				}
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		visited = new int[N+1][N+1];
		MAT = new int[N+1][N+1];
		
		for (int i = 1; i <= N; i++) {
			String line = sc.next();
			for (int j = 1; j <= N; j++) {
				MAT[i][j] = line.charAt(j-1) - '0'; 
			}
		}
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if( visited[i][j] == 0 && MAT[i][j] == 1) {
					home = 0;
					dfs(i, j);
					homes.add(home);
				}
				
			}
		}

		Collections.sort(homes);
		System.out.println(homes.size());
		for (Integer home : homes) {
			System.out.println(home);
		}
		
		
		sc.close();
	}
}
