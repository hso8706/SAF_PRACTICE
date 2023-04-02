package aSAF.DynamicProgramming4_230331;

import java.util.Arrays;
import java.util.Scanner;

/* 
6
3 2 6 4 5 1
==>3

10
8 2 4 3 6 11 7 10 14 5
==>6
 */

public class LISTest1 {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		int[] arr = new int[N];//data
		int[] LIS = new int[N]; // 각수를 끝에 세울수 있는 최장길이. n번째 값까지 고려했을 때의 최장길이. i원소를 끝으로 하는 최장 증가 길이 수열
		
		for (int i = 0; i < N; i++) 
			arr[i] = s.nextInt();
	
		int max = 0;
		for(int i=0; i<N; ++i) { // 기존 증가수열에 덧붙일 대상
			LIS[i] = 1; // 일단 현재의 수만 수열에 넣었을때 길이로 초기화.자기 자신으로만 이루어진 길이
			
			for(int j=0; j<i; ++j) { // 결국, 자신의 앞쪽에 세울수 있는(자신보다 작은) 애 중에 가장 긴 최장길이에 자신을 붙인다.
				if(arr[j]<arr[i] && LIS[i]<LIS[j]+1) {  // 배열의 뒤에 있는 애가 더 큰값이고 LIS배열의 앞쪽값에 +1을 한값이 최대값 갱신 
					LIS[i] = LIS[j]+1;
				}
			}// 결국, LIS[i]에는 자신을 끝으로 하는 최대값이 저장되어 있음
			
			// 전체 결과 중 최장길이최대값 갱신 
			if(max < LIS[i]) max = LIS[i];
		}

		System.out.println(max);
	}
}
