package aSAF.subset_01_230209;

import java.util.Scanner;

public class SubsetTest_recursion_template {

    static int N, totalCnt;
    static int[] input; // 입력받은 수들
    static boolean[] isSelected; // 각 원소가 부분집합의 구성에 포함되었는지 여부 확인 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt(); // 원소의 개수
        input = new int[N];
        isSelected = new boolean[N]; // 부분집합 포함 여부 체크

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        generateSubSet(0); // 0부터 시작해도되고, N에서 0으로 가도됨
        System.out.println("총 경우의 수 " + totalCnt);
    }

    private static void generateSubSet(int cnt){ // cnt: 직접까지 고려된 원소 수

        if(cnt == N){ // 기저 조건, 배열안의 모든 데이터 체크가 끝난 상태
            // 하나의 부분집합이 완성됐을 때 할 일
            return;
        }
        // 현재 원소를 부분집합의 구성에 포함
        isSelected[cnt] = true;
        generateSubSet(cnt + 1);
        // 현재 원소를 부분집합의 구성에 비포함
        isSelected[cnt] = false;
        generateSubSet(cnt + 1);
    }
}
