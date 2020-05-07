package backjoon.saumsung;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//1. 시작점 Pair 를 큐에 넣는다.
// 2. 큐에서 현재위치(R,B)를 꺼낸다.
// 3. (종료조건)
//		- 기울임 10번 초과시 실패(-1_
//		- 파란색 구슬이 구멍에 빠지면 continue
//		- 빨간색 구슬이 구멍에 빠지면 cnt 반환
// 4. (탐색조건)
//		1) 빨간색 구슬 이동
//		2) 파란색 구슬 이동
// 		3) 빨간색 구슬과 파란색 구슬이 겹치는지 확인
//		4) 방문여부 확인, 큐에 삽입
// 5. 큐가 비어있지 않으면 반복
public class samsung_13460 {
	// 변수선언
	static int N, M;
	static int rx, ry, bx, by;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int[][] MAT;
	static int[][][][] visited;
	static Queue<Pair> que = new LinkedList<>();
	
	public static class Pair {
		int rx, ry, bx, by, cnt;

		public Pair(int rx, int ry, int bx, int by, int cnt) {
			this.rx = rx;
			this.ry = ry;
			this.bx = bx;
			this.by = by;
			this.cnt = cnt;
		}
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		sc.nextLine();
		
		MAT = new int[N+1][M+1];
		visited = new int[N+1][M+1][N+1][M+1];
		
		// 문자를 넘버링, R과 B의 입력좌표를 지정
		for (int i = 1; i <= N; i++) {
			String str = sc.nextLine();
			for (int j = 1; j <= str.length(); j++) {
				int num = 0;
				switch (str.charAt(j-1)) {
				case '.': 	num = 0;
							break;
				case '#': 	num = 1;
							break;
				case 'R': 	num = 2;
							rx = i;
							ry = j;
							break;
				case 'B': 	num = 3;
							bx = i;
							by = j;
							break;
				case 'O': 	num = 4;
							break;
				}
				MAT[i][j] = num;
			}
		}
		System.out.println(bfs());
	}
	
	
	// bfs()
	public static int bfs() {
		// 1. 시작점 Pair 를 큐에 넣는다.
		que.add(new Pair(rx, ry, bx, by, 0));
		visited[rx][ry][bx][by] = 1;
		
		// 5. 큐가 비어있지 않으면 반복
		while(!que.isEmpty()) {
			// 2. 큐에서 현재위치(R,B)를 꺼낸다.
			Pair p = que.poll();	
				
			// 3. (종료조건)
			//		- 기울임 10번 초과시 실패(-1)
			//		- 파란색 구슬이 구멍에 빠지면 continue
			//		- 빨간색 구슬이 구멍에 빠지면 cnt 반환
			if( p.cnt > 10 ) return -1;
			if( MAT[p.bx][p.by] == 4 )	continue;
			if( MAT[p.rx][p.ry] == 4 ) return p.cnt;
			
			// 4. (탐색조건)
			for (int i = 0; i < 4; i++) {
				//	1) 빨간색 구슬 이동
				int nxt_rx = p.rx;
				int nxt_ry = p.ry;
				while(true) {
					// 다음 지점이 벽(1)이랑 구멍(4)이 아니면
					if(MAT[nxt_rx][nxt_ry] != 1 && MAT[nxt_rx][nxt_ry] != 4) {
						nxt_rx += dx[i];
						nxt_ry += dy[i];
					} else {
						// 다음 지점이 벽(1) 이면
						if( MAT[nxt_rx][nxt_ry] == 1) {
							nxt_rx -= dx[i];
							nxt_ry -= dy[i];
						}
						break;
					}
				}
			
				//	2) 파란색 구슬 이동
				int nxt_bx = p.bx;
				int nxt_by = p.by;
				while(true) {
					// 다음 지점이 벽(1)이랑 구멍(4)이 아니면
					if(MAT[nxt_bx][nxt_by] != 1 && MAT[nxt_bx][nxt_by] != 4) {
						nxt_bx += dx[i];
						nxt_by += dy[i];
					} else {
						// 다음 지점이 벽(1) 이면
						if( MAT[nxt_bx][nxt_by] == 1) {
							nxt_bx -= dx[i];
							nxt_by -= dy[i];
						}
						break;
					}
				}
				// 	3) 빨간색 구슬과 파란색 구슬이 겹치는지 확인
				if( nxt_rx == nxt_bx && nxt_ry == nxt_by ) {
					if(MAT[nxt_rx][nxt_ry] != 4) {
						int red_cost = Math.abs(nxt_rx - p.rx) + Math.abs(nxt_ry - p.ry);
						int blue_cost = Math.abs(nxt_bx - p.bx) + Math.abs(nxt_by - p.by);
						if(red_cost > blue_cost) {
							nxt_rx -= dx[i];
							nxt_ry -= dy[i];
						} else {
							nxt_bx -= dx[i];
							nxt_by -= dy[i];
						}
					}
				}
				
				//	4) 방문여부 확인, 큐에 삽입
				if( visited[nxt_rx][nxt_ry][nxt_bx][nxt_by] == 0 ) {
					visited[nxt_rx][nxt_ry][nxt_bx][nxt_by] = 1;
					que.add( new Pair(nxt_rx, nxt_ry, nxt_bx, nxt_by, p.cnt + 1) );
				}
			}
		}
		
		// 실패 하면 -1
		return -1;
	}
	
}













