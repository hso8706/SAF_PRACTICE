package aSAF.DynamicProgramming1_230328;

public class Coloring_APT_HJH {
    /*
    ### 아파트 페인트 칠하기
    - 조건1. 색은 노란색, 파란색으로 2가지가 있다.
    - 조건2. 노란색은 두 층을 연속으로 같은 색으로 칠할 수 있다
    - 조건3. 파란색은 두 층을 연속으로 같은 색으로 칠할 수 없다
    - f(n): 아파트를 칠할 수 있는 방법의 수, f(1)=2, f(2)=3

    ### 해결
    1. 요구 답 : f(8) 구하기
    2. 메모리제이션 배열 선언 : int[] f = new int[9]; f[0]은 제외
    3. f[0] = 0, f[1] = 2, f[2] = 3

    - 파랑일 땐 무조건 노랑 => 경우의 수 1
    - 노랑일 땐 f(1) 상황만큼 곱하기 가능
    - f(n) = 노랑(n) + 파랑(n)
        - 노랑(n) : 이전 노랑+파랑 개수만큼 증가
        - 파랑(n) : 이전 노랑 개수만큼 증가
     */
    //top down
    public static void main(String[] args) {
        int[] f = new int[9];
        int[] yellow = new int[9];
        int[] blue = new int[9];
        yellow[0] = 0;
        yellow[1] = 1;
        blue[0] = 0;
        blue[1] = 1;
        f[1] = yellow[1] + blue[1];

//        f[1] = yellow[1] + blue[1];
//        f[2] = yellow[2] + blue[2] = f[1] + yellow[1];
//        f[3] = yellow[3] + blue[3] = f[2] + yellow[2];
//        f[4] = yellow[4] + blue[4] = f[3] + yellow[3];
        for (int i = 2; i < 9; i++) {
            yellow[i] = f[i-1];
            blue[i] = yellow[i-1];
            f[i] = yellow[i] + blue[i];
        }
        System.out.println(f[8]);

    }

    //bottom up
}
