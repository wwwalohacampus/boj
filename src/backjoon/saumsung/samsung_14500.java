package backjoon.saumsung;

import java.util.Scanner;

/*
     -테트로미노의 종류    : 5 개
  	 -나타낼 수 있는 모양  : 총 19가지 
 
    1) 
 	ㅁㅁㅁㅁ
 	
 	ㅁ
 	ㅁ
 	ㅁ
 	ㅁ
 	
 	2)
 	ㅁㅁ
 	ㅁㅁ
 	
 	
 	3)
 			ㅁ       ㅁ
 		  ㅁㅁㅁ      ㅁㅁㅁ
  	
		ㅁㅁ		ㅁㅁ
		  ㅁ		ㅁ
		  ㅁ		ㅁ
 	
 		ㅁㅁㅁ	ㅁㅁㅁ
 	             ㅁ      ㅁ
 	     
 	  	ㅁ		ㅁ   
 	  	ㅁ		ㅁ
            ㅁㅁ        	ㅁㅁ
            
    4)
                 ㅁ		ㅁ
       	ㅁㅁ		ㅁㅁ     
       	ㅁ          	   ㅁ
       	
       	ㅁㅁ		ㅁㅁ 	
 	    ㅁㅁ		  ㅁㅁ
 	
 	    
 	5)  
 	  	ㅁ
 	    ㅁㅁㅁ
 	    
 	    ㅁ
 	    ㅁㅁ
 	    ㅁ	
 	    
 	   ㅁㅁㅁ 
 	     ㅁ
 	     
 	      ㅁ
 	   ㅁㅁ
 	      ㅁ  
 	        
 */

public class samsung_14500 {
	static int N, M;
	static int a[][] = new int[501][501];
	static int Answer = 0;
	
	
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		
		// 격자 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				// ㅁㅁㅁㅁ
				if( j+3 < M ) {
					int sum = a[i][j] + a[i][j+1] + a[i][j+2] + a[i][j+3];
					if( Answer < sum ) Answer = sum;
				}
				
				// ㅁ
				// ㅁ
				// ㅁ
				// ㅁ
				if( i+3 < N ) {
					int sum = a[i][j] + a[i+1][j] + a[i+2][j] + a[i+3][j];
					if( Answer < sum ) Answer = sum;
				}
				
				
				// ㄴ자
				if(i+2 < N && j+1 < M) {
					int temp = a[i][j] + a[i+1][j] + a[i+2][j] + a[i+2][j+1];
					if( Answer < temp ) Answer = temp;
				}
				if(i+2 < N && j-1 >= 0) {
					int temp = a[i][j] + a[i+1][j] + a[i+2][j] + a[i+2][j-1];
					if( Answer < temp ) Answer = temp;
				}
				if(i+2 < N && j+1 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i+1][j+1] + a[i+2][j+1];
					if( Answer < temp ) Answer = temp;
				}
				if(i+2 < N && j+1 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i+1][j] + a[i+2][j];
					if( Answer < temp ) Answer = temp;
				}
				if(i+1 < N && j+2 < M) {
					int temp = a[i+1][j] + a[i][j] + a[i][j+1] + a[i][j+2];
					if( Answer < temp ) Answer = temp;
				}
				if(i+1 < N && j+2 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i][j+2] + a[i+1][j+2];
					if( Answer < temp ) Answer = temp;
				}
				if(i+1 < N && j+2 < M) {
					int temp = a[i][j] + a[i+1][j] + a[i+1][j+1] + a[i+1][j+2];
					if( Answer < temp ) Answer = temp;	
				}
				if(i-1 >= 0 && j+2 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i][j+2] + a[i-1][j+2];
					if( Answer < temp ) Answer = temp;
				}
				
				
				// z자
				if(i+2 < N && j-1 >= 0) {
					int temp = a[i][j] + a[i+1][j] + a[i+1][j-1] + a[i+2][j-1];
					if( Answer < temp ) Answer = temp;
				}
				if(i+2 < N && j+1 < M) {
					int temp = a[i][j] + a[i+1][j] + a[i+1][j+1] + a[i+2][j+1];
					if( Answer < temp ) Answer = temp;
				}
				if(i+1 < N && j+2 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i+1][j+1] + a[i+1][j+2];
					if( Answer < temp ) Answer = temp;
				}
				if(i-1 >= 0 && j+2 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i-1][j+1] + a[i-1][j+2];
					if( Answer < temp ) Answer = temp;
				}
				
				
				// ㅗ 자
				if(i+2 < N) {
					int temp = a[i][j] + a[i+1][j] + a[i+2][j];
					if(j-1 >= 0) {
						int temp2 = temp + a[i+1][j-1];
						if( Answer < temp2 ) Answer = temp2;
					}
					if(j+1 < M) {
						int temp2 = temp + a[i+1][j+1];
						if( Answer < temp2 ) Answer = temp2;
					}
				}
				if(j+2 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i][j+2];
					if(i+1 < N) {
						int temp2 = temp + a[i+1][j+1];
						if( Answer < temp2 ) Answer = temp2;
					}
					if(i-1 >= 0) {
						int temp2 = temp + a[i-1][j+1];
						if( Answer < temp2 ) Answer = temp2;
					}
				}

					
				// ㅁ자
				if(i+1 < N && j+1 < M) {
					int temp = a[i][j] + a[i][j+1] + a[i+1][j] + a[i+1][j+1];
					if(temp > Answer) Answer = temp;
				}
				
			}
		}
		
		System.out.println(Answer);
	}
}













