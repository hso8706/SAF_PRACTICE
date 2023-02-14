package com.exercise;

import java.util.Scanner;

public class Template_Permutation {
    // 초기화
    // 1. 전체 배열 만들기, 길이 N(입력)
        // 내용 채우기
    // 2. 뽑은 배열 만들기, 길이 R(입력)
    // 3. 뽑은 요소를 체크하는 배열, 길이 N(==1번 배열), boolean
    // 4. 뽑은 배열이 완성된 횟수 구하기(옵션)
    // 재귀
    // 인자: checkPoint, 현재 전체 배열에서 몇개의 배열을 뽑았는지 확인 == 뽑은 배열에 채워진 요소의 갯수가 몇개인지 확인
    // base: checkPoint == R 이면 종료
    // body
        // 반복문 순회, N만큼
        // 조건문, isSelected 를 통해서 현재 조회된 totalArr 의 요소가 뽑혀있는지 아닌지 확인
            // 아니라면, selectedArr 에 추가하고, 해당 인덱스의 isSelected 를 true(사용함)으로 변경
            // 맞다면, 넘어감
        // 재귀 호출, checkPoint 에 1을 추가해서 호출
        // 재귀 종료되면 마지막에 사용된 isSelected 를 false 로 다시 상태를 변경하고, 반복문을 통해 다음 인덱스의 요소를 조회하도록 구현

    static int[] totalArr, selectedArr;
    static int N, R, totalCount;
    static boolean[] isSelected;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        totalArr = new int[N];
        selectedArr = new int[R];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            totalArr[i] = i + 1;
        }

        perm(0);
        System.out.println(totalCount);
    }

    private static void perm(int checkPoint) {
        if (checkPoint == R){
            totalCount++;
            return;
        }
        //isSelected 는 기본값인 false 로 초기화되어 있는 상태
        for (int i = 0; i < N; i++) { // isSelected 순회를 위한 반복문
            if(!isSelected[i]){ //false 를 만나면 조건 성립
                selectedArr[checkPoint] = totalArr[i];
                isSelected[i] = true;
                perm(checkPoint + 1);
                isSelected[i] = false;
            }
        }
    }
}
