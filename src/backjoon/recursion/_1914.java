package backjoon.recursion;

import java.math.BigInteger;
import java.util.Scanner;

public class _1914 {
	
	// N개의 원반을 x번 이동해서, y번 기둥에 이동
	static void move(int N, int x, int y) {
		if( N > 1 ) {
			move(N-1, x, 6-x-y);
		}
		// 이동과정
		System.out.println(x + " " + y);
		
		if( N > 1 ) {
			move(N-1, 6-x-y, y);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		BigInteger two = new BigInteger("2");
		BigInteger cnt = two.pow(N).subtract(BigInteger.ONE);	// 2^100 - 1 
		System.out.println(cnt);
		move(N, 1, 3);			// 1번 기둥의 N개의 원반을 3번기둥으로 이동
		
	}
}
