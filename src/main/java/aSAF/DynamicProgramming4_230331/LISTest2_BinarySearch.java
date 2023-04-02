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

public class LISTest2_BinarySearch {
	static String src = "6\n" + 
			"3 2 6 4 5 1";
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		s = new Scanner(src);
		int N = s.nextInt();
		int[] arr = new int[N];
		int[] C = new int[N];//LIS 배열
		
		for (int i = 0; i < N; i++) arr[i] = s.nextInt();
		
		int end = 0;//이진 탐색할 끝 위치
        for (int i=0; i < N; i++) {
            int insertPosition = Arrays.binarySearch(C, 0, end, arr[i]); // 리턴값은 삽입위치 : -(insertPoint) -1
            
            insertPosition = Math.abs(insertPosition)-1;//real 삽입위치            
            C[insertPosition] = arr[i];// 배열의 원소가 LIS를 이루는 구성요소와는 관계가 없다.
            
            if (end == insertPosition) {// 삽입위치의 인덱스와 크기가 같으면(결국,마지막이 삽입위치였다는 얘기임) 크기 1늘림.
            	//이진탐색한 마지막 위치에 값이 들어갔음. 다음에 올 값을 위해 크기를 1 늘림
                end++;               
            }
        }
        //System.out.println(Arrays.toString(C));
        System.out.println(end);
	}

}
/*
6
3 2 6 4 5 1
*/
