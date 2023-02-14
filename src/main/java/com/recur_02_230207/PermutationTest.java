package com.recur_02_230207;

import java.util.Arrays;
import java.util.Scanner;

//nPr
//N개 중에 R개를 뽑아 순어있게 늘어 놓기
public class PermutationTest {

    //main(), perm()에서 쓰기 위함.
    static int N, R, totalCount;
    static int[] result;// result: 순열로 뽑힌 숫자들이 들어있는 배열
    static int[] numbers;// numbers: 전체 데이터 배열
    static boolean[] isSelected; // 순열에 사용된 숫자인지 체크하기 위한 배열


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        R = sc.nextInt();
        
        numbers = new int[N]; // 모든 값
        result = new int[R]; // 순열을 구성하는 숫자가 들어갈 배열
        isSelected = new boolean[N];

        //순열에 사용할 값 넣어 놓기
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = i + 1; // {1,2,3,4,5,...}, 입력된 길이만큼
        }
        
        perm(0); // 순열을 구성하는 수의 개수
        System.out.println("총 경우의 수:" + totalCount);
    }

    //현재까지 뽑힌 숫자 개수를 입력받아 체크 한 뒤 종료하거나 숫자를 뽑거나 함
    //cnt: 이제까지 뽑은 순열을 구성하는 숫자 개수
    private static void perm(int cnt){
        //다 뽑은 상태가 걸리는 조건문
        if(cnt == R) {
            totalCount++;
            System.out.println(Arrays.toString(result));
            return; // 실행 종료
        }
        //숫자를 더 뽑을 수 있는 상태가 걸리는 조건문
        for (int i = 0; i < isSelected.length; i++) {//전체 숫자를 대상으로 뽑기
            if (!isSelected[i]) { //사용가능한 숫자
                isSelected[i] = true; //사용할게!
                result[cnt] = numbers[i]; // i번째 값을 선택

                perm(cnt + 1); //다음 숫자 뽑으러 보냄.
                // 아래 코드는 모든 사람이 숫자를 뽑고 난 뒤의 상황이 될 예정
                isSelected[i] = false; // 사용했던 위치 해제
            }
        }
    }
}
