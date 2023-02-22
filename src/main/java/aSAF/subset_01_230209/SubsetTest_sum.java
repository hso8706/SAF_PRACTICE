package aSAF.subset_01_230209;

import java.util.Scanner;

public class SubsetTest_sum {

    static int N, S, totalCnt; // S(합) 추가
    static int[] input; // 입력받은 수들
    static boolean[] isSelected; // 각 원소가 부분집합의 구성에 포함되었는지 여부 확인 배열

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        input = new int[N];
        isSelected = new boolean[N];

        for (int i = 0; i < N; i++) {
            input[i] = sc.nextInt();
        }

        generateSubSet(0, 0); // 0부터 시작해도되고, N에서 0으로 가도됨
        System.out.println("총 경우의 수 " + totalCnt);
    }

    private static void generateSubSet(int cnt, int sum){
        // 매개변수
        // cnt: 직전까지 고려된 원소 수
        // sum: 직전까지 선택된 원소들의 합
        if(cnt == N){ // 기저 조건, cnt == N이면 리턴
            if(sum == S){
                totalCnt++;
                for (int i = 0; i < N; i++) {
                    if(isSelected[i]) System.out.print(input[i]  + "\t");
                }
                System.out.println();
            }
            return;
        }
        // 현재 원소를 부분집합의 구성에 포함
        isSelected[cnt] = true;
        generateSubSet(cnt + 1, sum+input[cnt]);
        // 현재 원소를 부분집합의 구성에 비포함
        isSelected[cnt] = false;
        generateSubSet(cnt + 1, sum);
    }
}
