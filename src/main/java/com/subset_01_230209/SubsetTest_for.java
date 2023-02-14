package com.subset_01_230209;
//반복문으로 부분집합 만드는 코드 구현
//{1,2,3}으로 만들 수 있는 부분집합
public class SubsetTest_for {
    public static void main(String[] args) {
        int N = 3; // 원소 수
        boolean[] isSelected = new boolean[N+1]; // 0번째 idx 는 안 쓸 예정.

        for (int i = 0; i <= 1; i++) { // 첫번째 요소 선택 여부 결정
            isSelected[1] = (i == 1); // 1이면 선택(true), 1이 아니면 비선택(false)

            for (int j = 0; j <= 1; j++) {// 두번째 숫자 선택 여부 결정
                isSelected[2] = (j == 1); // 1이면 선택(true), 1이 아니면 비선택(false)

                for (int k = 0; k <= 1; k++) {// 세번째 숫자 선택 여부 결정
                    isSelected[3] = (k == 1); // 1이면 선택(true), 1이 아니면 비선택(false)

                    // 부분집합 결정완료. isSelected 배열안의 값을 보고 부분집합 내용 출력
                    for (int l = 1; l <= N; l++) {
                        System.out.print((isSelected[l] ? l : "-") + " ");
                    }
                    System.out.println();
                }
            }
        }

    }
}
