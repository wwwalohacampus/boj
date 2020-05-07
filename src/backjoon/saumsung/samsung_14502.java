package backjoon.saumsung;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import javax.security.sasl.SaslClient;

/*
Q.연구소
인체에 치명적인 바이러스를 연구하던 연구소에서 바이러스가 유출되었다. 다행히 바이러스는 아직 퍼지지 않았고, 
바이러스의 확산을 막기 위해서 연구소에 벽을 세우려고 한다. 연구소는 크기가 N×M인 직사각형으로 나타낼 수 있으며, 
직사각형은 1×1 크기의 정사각형으로 나누어져 있다. 연구소는 빈 칸, 벽으로 이루어져 있으며, 벽은 칸 하나를 가득 차지한다. 
일부 칸은 바이러스가 존재하며, 이 바이러스는 상하좌우로 인접한 빈 칸으로 모두 퍼져나갈 수 있다. 새로 세울 수 있는 벽의 개수는 3개이며, 꼭 3개를 세워야 한다.

예를 들어, 아래와 같이 연구소가 생긴 경우를 살펴보자.

	2 0 0 0 1 1 0
	0 0 1 0 1 2 0
	0 1 1 0 1 0 0
	0 1 0 0 0 0 0
	0 0 0 0 0 1 1
	0 1 0 0 0 0 0
	0 1 0 0 0 0 0
	
이때, 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 곳이다. 아무런 벽을 세우지 않는다면, 바이러스는 모든 빈 칸으로 퍼져나갈 수 있다.
2행 1열, 1행 2열, 4행 6열에 벽을 세운다면 지도의 모양은 아래와 같아지게 된다.

	2 1 0 0 1 1 0
	1 0 1 0 1 2 0
	0 1 1 0 1 0 0
	0 1 0 0 0 1 0
	0 0 0 0 0 1 1
	0 1 0 0 0 0 0
	0 1 0 0 0 0 0
	
바이러스가 퍼진 뒤의 모습은 아래와 같아진다.

	2 1 0 0 1 1 2
	1 0 1 0 1 2 2
	0 1 1 0 1 2 2
	0 1 0 0 0 1 2
	0 0 0 0 0 1 1
	0 1 0 0 0 0 0
	0 1 0 0 0 0 0
	
벽을 3개 세운 뒤, 바이러스가 퍼질 수 없는 곳을 안전 영역이라고 한다. 위의 지도에서 안전 영역의 크기는 27이다.
연구소의 지도가 주어졌을 때 얻을 수 있는 안전 영역 크기의 최댓값을 구하는 프로그램을 작성하시오.


 
[입력]
첫째 줄에 지도의 세로 크기 N과 가로 크기 M이 주어진다. (3 ≤ N, M ≤ 8)
둘째 줄부터 N개의 줄에 지도의 모양이 주어진다. 0은 빈 칸, 1은 벽, 2는 바이러스가 있는 위치이다. 
2의 개수는 2보다 크거나 같고, 10보다 작거나 같은 자연수이다.
빈 칸의 개수는 3개 이상이다.

[출력]
첫째 줄에 얻을 수 있는 안전 영역의 최대 크기를 출력한다.

(입력예시)
7 7
2 0 0 0 1 1 0
0 0 1 0 1 2 0
0 1 1 0 1 0 0
0 1 0 0 0 0 0
0 0 0 0 0 1 1
0 1 0 0 0 0 0
0 1 0 0 0 0 0

(출력예시)
27



(문제분석)

	- Answer				: 안정영역의 최댓값
	- 바이러스					: 상하좌우로 확산
	- (조건)	:				: 벽을 3개 세운다.  
			1)  벽을 세우는 방법을 탐사한다.
			2)	벽을 세운 뒤, 바이러스를 퍼뜨린다.
			3)  안전영역의 크기를 구한다.
			4)	안전영역의 최댓값을 구한다.	
			
			
(Solution)
	
	1. 입력			: 	N, M, r, c, d, MAT[][]
	2. 벽을 세운다.			(DFS)
	3. 바이러스를 퍼뜨린다. 		(BFS)
		1) 벽 3개를 세운 MAT 복사 -> C_MAT 
		2) C_MAT 바이러스 확산, 안전영역 크기를 계산
		
	3. 안전 영역의 최대 크기를 출력한다.

*/

public class samsung_14502 {
	// 입력할 변수들
	// 세로크기 N, 가로크기 M
	static int N, M;
	// 바이러스의 위치를 담을 벡터 S
	static ArrayList<int []> S = new ArrayList<int[]>();
	// 바이러스를 퍼뜨릴 자료구조 - BFS 구현할 큐
	static Queue<int[]> que = new LinkedList<int[]>();
	
	
	// MAT[][] 
	static int MAT[][] = new int[9][9];
	// C_MAT[][]
	static int C_MAT[][] = new int[9][9];
	
	// 동남서북
	static int dr[] = {0, 1, 0, -1};
	static int dc[] = {1, 0, -1, 0};
	
	// 최종 답, 안전지역
	static int Answer, grass;
	
	
	// 1. 벽 세우기
	// dfs() : (반환타입x), (매개변수: idx, cnt)
	public static void dfs(int idx, int cnt) {
		// 종료조건
		if( cnt == 3 ) {
			//  이제 벽을 다 세웠으니까
			//  바이러스를 확산시킨다.
			bfs();
		}
		// 탐색조건
		else {
			for (int i = 0; i < N; i++) {
				// 다음 탐색할 x좌표를 구한다.
				int nxt_row = i + 1;
				for (int j = 0; j < M; j++) {
					// 다음 탐색할 y좌표를 구한다.
					int nxt_col = j + 1;
					// 벽을 설치할 수 있는 초원이 경우
					if( MAT[nxt_row][nxt_col] == 0 ) {
						// 벽을 세운다.
						MAT[nxt_row][nxt_col] = 1;
						dfs(i+1, cnt+1);				// dfs(1, 3)
						// 탐색한 벽을 원복(제거)
						MAT[nxt_row][nxt_col] = 0;
					}
				}
			}
			
		}
	}
	
	
	// 2. 바이러스 확산
	public static void bfs() {
		// que 를 초기화한다.
		que.clear();
		
		// 벽을 세운 격자를 카피한다.		( MAT[][] -> C_MAT[][] )
		// clone()
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				C_MAT[i][j] = MAT[i][j];
			}
		}
		
		// 현재 격자에서의 안전영역의 수 (매 탐색마다 초기화)
		int now_grass = 0;
		int now_virus = 0;
		
		// 바이러스의 위치(x,y) 를 가져와서 큐에 삽입
		for (int i = 0; i < S.size(); i++) {
			// 1. 시작점을 큐에 넣는다.
			que.add(S.get(i));
		}
		
		while( !que.isEmpty() ) {
			// 2. 큐에서 한 점을 꺼내서 기준점으로 삼는다.
			int now[] = que.poll();
			int now_row = now[0];
			int now_col = now[1];
			
			// 3. 현재 위치에서 다음 좌표를 찾는다.		(동남서북)
			for (int i = 0; i < 4; i++) {
				int nxt_row = now_row + dr[i];
				int nxt_col = now_col + dc[i];
				
				// 4. 격자 안에 있는지 확인한다.
				if( nxt_row >= 1 && nxt_row <= N 
						&& nxt_col >= 1 && nxt_col <= M) {
					// 		ㄴ 5. 바이러스가 확산될 수 있는 영역인지 확인한다.
					if( C_MAT[nxt_row][nxt_col] == 0 ) {
					//		ㄴ 6. 바이러스를 퍼뜨리고, 위치를 큐에 삽입한다.
						C_MAT[nxt_row][nxt_col] = 2;
						now_virus++;				// (추가된)퍼뜨린 바이러스의 개수
						que.add(new int[] {nxt_row, nxt_col});
						
					}
				}
			}
		}
		// 현재 격자 (C_MAT) 에서 안전영역의 개수를 계산한다.
		now_grass = grass - now_virus;			// (now_virus : 추가된 바이러스의 개수)
		// 현재까지 안전영역의 최댓값을 구한다.
		if( Answer < now_grass ) {
			Answer = now_grass;
		}
	}
	
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		// 인접행렬 초기화
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				MAT[i][j] = 0;
			}
		}
		
		// 바이러스 위치 벡터를 초기화
		S.clear();
		Answer = 0;
		grass = 0;			// 최초 안전영역의 개수
		
		// 안전영역 : 0, 벽 : 1, 바이러스 : 2
		// 격자의 정보를 입력
		for (int i = 1; i <= N ; i++) {
			for (int j = 1; j <= M; j++) {
				MAT[i][j] = sc.nextInt();
				// 안전영역인 경우
				if( MAT[i][j] == 0 ) 
					grass++;
				// 바이러스인 경우
				else if ( MAT[i][j] == 2 ) {
					// 바이러스의 x좌표, y좌표
					S.add(new int[] {i, j} );
				}
			}
		}
		// 새로 세울 벽의 개수 3을 빼준다.
		grass = grass - 3;
		
		// dfs() 를 통해서 벽 3개를 세운다.
		dfs(0,0);
		
		// 안전영역의 최대 크기를 출력한다.
		System.out.println(Answer);
		
		
	}
	

}























