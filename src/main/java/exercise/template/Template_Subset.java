package exercise.template;

public class Template_Subset {
    static int N, totalCount;
    static int[] totalArr;
    static boolean[] isSelected;
    private static void subs(int checkPoint){
        if(checkPoint == N){
            // 기저 조건, 완성된 부분 집합의 결과를 가공하는 부분
            return;
        }

        for (int i = 0; i < N; i++) {
            isSelected[checkPoint] = true; // 현재 인덱스의 요소를 부분 집합에 포함
            subs(checkPoint + 1);
            isSelected[checkPoint] = false; // 다시 이전 인덱스로 돌아가기 위함
            subs(checkPoint + 1); // false 일 때도 재귀를 종료하기 위함.
        }
    }
}

/*
1   2   3
f   f   f
t
 */
