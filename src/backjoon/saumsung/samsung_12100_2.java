package backjoon.saumsung;


import java.util.Scanner;

public class samsung_12100_2 {

	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static final int RIGHT = 4;

	static int N;
	static int[][] map;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		map = new int[N][N];

		for (int x = 0; x < N; x++)
			for (int y = 0; y < N; y++)
				map[x][y] = sc.nextInt();

		sc.close();

		move(0, UP, map); //상하좌우로 이동
		move(0, DOWN, map);
		move(0, LEFT, map);
		move(0, RIGHT, map);

		System.out.println(MAX);
	}

	public static void move(int count, int direction, int[][] map) {

		int[][] tempMap = moveMap(direction, map); //배열 요소를 특정 방향으로 몰아넣는다.
		tempMap = mergeMap(direction, tempMap); //배열 요소들의 값을 비교하여 같은 경우 합산, 아닌 경우 스킵
		tempMap = moveMap(direction, tempMap); //합산 후 생긴 중간의 0을 없애기 위해 다시 배열을 특정 방향으로 몰아넣는다.

		if (++count == 5) { //5번 이동했으면 배열 내 최대 값을 찾아 갱신 시도
			checkMaxValue(tempMap);
			return;
		}

		move(count, UP, tempMap); //상하좌우로 한 번 더 이동
		move(count, LEFT, tempMap);
		move(count, RIGHT, tempMap);
		move(count, DOWN, tempMap);
	}

	// 최대값 확인
	private static void checkMaxValue(int[][] tempMap) {
		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				MAX = Math.max(MAX, tempMap[x][y]); //최대값인 경우 갱신
			}
		}
	}

	// 배열 요소 중에 특정 방향으로 같은 값이 연속해서 있을 경우 병합
    // 병합은 한번만 하고 이후에 합병한 값과 같은 값이 나와도 연속 병합은 하지 않는다.
    // 예시) 4,4,8인 경우 좌측 이동시 8,8이 되지만 한 번에 16이 되진 않는다.
	private static int[][] mergeMap(int direction, int[][] moveMap) {

		switch (direction) { //병합 방향에 따라 처리가 다름
			case UP: 
				for (int y = 0; y < N; y++) {
					int prev = 0;
					for (int x = 0; x < N; x++) {
						if (prev == 0 || (prev != 0 && prev != moveMap[x][y]))
							prev = moveMap[x][y];
						else if (prev == moveMap[x][y]) {
							moveMap[x][y] += prev; //아래쪽 값을 합산시키고 위쪽 값을 0으로 만든다.
							moveMap[x - 1][y] = 0;
							prev = 0;
						}
					}
				}
				break;
			case DOWN:
				for (int y = N - 1; y >= 0; y--) { //이것도 반대로 하는 이유는?
					int prev = 0;
					for (int x = N - 1; x >= 0; x--) { //아래부터 올라오면서 세로 검사
						if (prev == 0 || (prev != 0 && prev != moveMap[x][y])) //값이 다르거나 이전에 이전 값이 0이었던 경우 값을 갱신한다.
							prev = moveMap[x][y];
						else if (prev == moveMap[x][y]) { //prev가 존재하고 값이 같은 경우 합친다.
							moveMap[x][y] += prev; //위쪽 값을 합산시키고 아래쪽 값을 0으로 만든다.
							moveMap[x + 1][y] = 0; 
							prev = 0;
						}
					}
				}
				break;
			case LEFT: //좌측 이동 검사
				for (int x = 0; x < N; x++) { 
					int prev = 0;
					for (int y = 0; y < N; y++) { //왼쪽에서 오른쪽으로 가로 검사
						if (prev == 0 || (prev != 0 && prev != moveMap[x][y]))
							prev = moveMap[x][y];
						else if (prev == moveMap[x][y]) {
							moveMap[x][y] += prev;
							moveMap[x][y - 1] = 0;
							prev = 0;
						}
					}
				}
				break;
			case RIGHT: //우측 이동 검사
				for (int x = N - 1; x >= 0; x--) {
					int prev = 0;
					for (int y = N - 1; y >= 0; y--) {
						if (prev == 0 || (prev != 0 && prev != moveMap[x][y]))
							prev = moveMap[x][y];
						else if (prev == moveMap[x][y]) {
							moveMap[x][y] += prev;
							moveMap[x][y + 1] = 0;
							prev = 0;
						}
					}
				}
				break;
		}

		return moveMap;
	}

	// 특정 방향으로 배열을 이동시키고 이동한 배열을 반환한다.
    // 병합 이전에 모든 값을 한쪽으로 몰기 위해서 한번, 병합 이후에 병합 때문에 생긴 0값을 처리하기 위해서
    // 사용된다.
	private static int[][] moveMap(int direction, int[][] originMap) {

		int[][] moveMap = new int[N][N];

		switch (direction) {
			case UP:
				for (int y = 0; y < N; y++) {
					int newX = 0;  //newX를 사용해서 중간에 0이 없이 원하는 방향으로 값을 몰 수 있다.
					for (int x = 0; x < N; x++) {
						if (originMap[x][y] != 0)
							moveMap[newX++][y] = originMap[x][y];
                    }
				}
				break;
			case DOWN:
				for (int y = N - 1; y >= 0; y--) {
					int newX = N - 1; 
					for (int x = N - 1; x >= 0; x--) {
						if (originMap[x][y] != 0)
							moveMap[newX--][y] = originMap[x][y];
					}
				}
				break;
			case LEFT:
				for (int x = 0; x < N; x++) {
					int newY = 0; //newY를 사용해서 중간에 0이 없이 원하는 방향으로 값을 몰 수 있다.
					for (int y = 0; y < N; y++) {
						if (originMap[x][y] != 0)
							moveMap[x][newY++] = originMap[x][y];
					}
				}
				break;
			case RIGHT:
				for (int x = N - 1; x >= 0; x--) {
					int newY = N - 1;
					for (int y = N - 1; y >= 0; y--) {
						if (originMap[x][y] != 0)
							moveMap[x][newY--] = originMap[x][y];
					}
				}
				break;
		}

		return moveMap;
	}
}