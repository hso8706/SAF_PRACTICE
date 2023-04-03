package aSAF.DynamicProgramming1_230328;

public class Coloring_Pole_HJH {
    /*
        ### 막대기 연결하기
        - 파랑 : 1, 노랑 : 1, 빨강 : 2
        - f(n) : 총 (n)cm 가 되는 방법의 수
        - f(6)=? 구하기
     */
    public static void main(String[] args) {
        /*
        f(0) = 1; 아무것도 선택 안 함
        f(1) = 2; 파, 노
        f(2) = 5; 빨, 파파, 파노, 노파, 노파
        f(3) = 12; 빨파, 파빨, 빨노, 노빨, 노노노, 노노파, 노파노, 파노노, 노파파, 파노파, 파파노, 파파파

        f(n) = f(n-1) + f(n-1) + f(n-2);
        : (n-1)cm 를 만들 수 있는 가지수에 파랑을 더하거나, 노랑을 더하면 f(n)이 됨.
        : (n-2)cm 를 만들 수 있는 가지수에 빨강을 더하면 f(n)이 됨.
        f(3) = f(2) + f(2) + f(1) = 5 + 5 + 2;
         */
        int[] f = new int[7];
        f[1] = 2;
        f[2] = 5;
        for (int i = 3; i < 7; i++) {
            f[i] = f[i-1]*2 + f[i-2];
        }
        System.out.println(f[6]);
    }
}
