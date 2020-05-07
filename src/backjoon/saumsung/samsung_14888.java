package backjoon.saumsung;

import java.util.ArrayList;
import java.util.Scanner;



// 수식 결과의 최댓값, 최솟값
class Pair {
	public int max, min;
	public Pair(int max, int min) {
		this.max = max;
		this.min = min;
	}
}

public class samsung_14888 {
	
	static Pair calc(int[] a, int index, int cur, int plus, int minus, int mul, int div) {
		
		if(a.length == index) {
			return new Pair(cur, cur);
		}
		
		ArrayList<Pair> res = new ArrayList<Pair>();
		if( plus > 0 ) {
			res.add(calc(a, index+1, cur+a[index], plus-1, minus, mul, div));
		}
		if( minus > 0 ) {
			res.add(calc(a, index+1, cur-a[index], plus, minus-1, mul, div));
		}
		if( mul > 0 ) {
			res.add(calc(a, index+1, cur*a[index], plus, minus, mul-1, div));
		}
		if( div > 0 ) {
			res.add(calc(a, index+1, cur/a[index], plus, minus, mul, div-1));
		}
		
		Pair ans = res.get(0);
		for (Pair p : res) {
			if(ans.max < p.max)
				ans.max = p.max;
			if(ans.min > p.min)
				ans.min = p.min;
		}
		
		return ans;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N =  sc.nextInt();
		int a[] = new int[N];
		for (int i = 0; i < N; i++) {
			a[i] = sc.nextInt();
		}
		int plus = sc.nextInt();
		int minus = sc.nextInt();
		int mul = sc.nextInt();
		int div = sc.nextInt();
		
		Pair ans = calc(a, 1, a[0], plus, minus, mul, div);
		System.out.println(ans.max);
		System.out.println(ans.min);
		
		
	}
}

