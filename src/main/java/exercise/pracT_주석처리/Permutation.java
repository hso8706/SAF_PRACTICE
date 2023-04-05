package exercise.pracT_주석처리;

import java.util.Arrays;
import java.util.Scanner;

public class Permutation {
    static int N, R, totalCnt; // N: 전체 배열의 길이,R: 선택될 배열의 길이,totalCnt: 선택된 순열 경우의 수
    static int[] totals, selected; // totals: 전체 배열, selected: 선택된 배열
    static boolean[] isSelected; // isSelected: 전체 배열과 대응하는 선택 여부 확인 배열

    public static void main(String[] args) {
        //N, R 입력
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        //배열들 선언
        totals = new int[N];
        selected = new int[R];
        isSelected = new boolean[N];
        //전체 배열 값 초기화
        for (int i = 0; i < N; i++) {
            totals[i] = i+1;
        }
        //순열 재귀 시작, 0부터 시작
        permu(0);
        System.out.println(totalCnt);
    }
    //순열 재귀 메서드
    private static void permu(int cnt) {
        // cnt 가 R(뽑는 배열의 길이)와 같아지면 종료
        if(cnt == R){
            // 경우의 수(totalCnt) 증가
            totalCnt++;
            System.out.println(Arrays.toString(selected));
            return;
        }
        // totals 배열 및 isSelected 배열을 순회하는 반복문
        for (int i = 0; i < N; i++) {
            // isSelected가 선택되지않았다면.
            if(!isSelected[i]){
                isSelected[i] = true; // isSelected 선택 처리
                selected[cnt] = totals[i]; // selected 선택 배열에 선택될 값 추가
                permu(cnt+1); // cnt를 증가시키며 재귀 호출 
                isSelected[i] = false; // isSelected 선택 취소 처리
            }
        }
    }
}