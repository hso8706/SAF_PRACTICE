package aSAF.recur_02_230207;

import java.util.Scanner;

public class SW1208_HaJungHo {
    public static void main(String[] args) {
        //평탄화 작업
        //반환값 : 최고점과 최저점의 차이
        //덤프 : 가장 높은 곳에 있는 상자를 가장 낮은 곳으로 옮기는 작업
        //덤프 횟수 : dump, 1 <= dump <= 1000
        //가로 너비(길이) : 배열 길이 (100 고정)
        //세로 높이 : 배열 요소 값 ( 1 <= h <= 100)

        Scanner sc = new Scanner(System.in);
        // 테스트 케이스, 10개 고정
        int T = 10;
//        T=sc.nextInt();

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //입력 1: 덤프 횟수, dump
            int dump = sc.nextInt();
            //입력 2: 배열 요소 값들
            int[] width = new int[100];
            for (int i = 0; i < width.length; i++) {
                width[i] = sc.nextInt();
            }
            for (int i = 0; i < dump; i++) {
                int[] minMaxIndex = max_min_index_array(width);
                width[minMaxIndex[0]] += 1;
                width[minMaxIndex[1]] -= 1;
            }
            int[] minMaxIndex = max_min_index_array(width);
            System.out.printf("#%d %d\n", test_case, width[minMaxIndex[1]] - width[minMaxIndex[0]]);
        }
    }

    private static int[] max_min_index_array(int[] arr){
        int[] temp = arr.clone();
        int[] min_max = new int[2];
        int max = 0;
        int min = 0;
        for (int i = 0; i < temp.length; i++) {
            if(temp[max] <= temp[i]) max = i;
            if(temp[min] >= temp[i]) min = i;
        }
        min_max[0] = min;
        min_max[1] = max;
        return  min_max;
    }
}
