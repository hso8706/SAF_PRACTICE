package aSAF.recur_03_230208;

import java.util.Arrays;
import java.util.Scanner;

// nCr = n! / (n-r)! * r!
// 순열과 비교하여 공부하기
public class Combination {
    // 1,2,3
    // 3C2 = 
    static int N, R, totalCount;
    static int[] result;
    static int[] numbers;
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        numbers = new int[N];
        result = new int[R];
        // 1 부터 N 까지의 값이 요소로 저장된 numbers 채우기
        for (int i = 0; i < N; i++) {
            numbers[i] = i+1;
        }
        
        combi(0, 0); // 재귀 시작
        System.out.println("총 경우의 수 : " + totalCount);
    }

    // 현재까지 조합을 구성하는 숫자의 개수(cnt)와 조합을 구성할 수의 시작 인덱스(start)를 입력 받아 조합 구성
    // cnt : result 칸의 자릿수를 세는 변수
    // start : numbers 에서 중복된 값이 안들어가도록 시작 지점을 변경하는 역할, 반복문에서 사용
    private static void combi(int cnt, int start) {
        // 하나의 조합이 완성되면 화면에 출력하기
        if (cnt == R) { // R 이 경계를 잡아주는 역할
            totalCount++;
            System.out.println(Arrays.toString(result));
            return;
        }
        
        //핵심 포인트
        //조합을 구성할 숫자 선택
        for (int i = start; i < N; ++i){
            result[cnt] = numbers[i];
            combi(cnt + 1, i + 1); // cnt 가 걸리면 재귀에서 다시 회귀하고 i 값만 올라감
        }
    }
}
