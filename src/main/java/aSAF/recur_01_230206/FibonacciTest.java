package aSAF.recur_01_230206;

import java.util.Scanner;

public class FibonacciTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
//        System.out.println(fibo(n));
        System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
        jhRecur(n, "");
        System.out.println("라고 답변하였지.");
    }


    private static int fibo(int n) {
//        if(n == 0 || n == 1)
        if(n < 2){
            return n; // 고정된 수는 그대로 리턴 => 종료 조건
        }

        return fibo(n - 1) + fibo(n - 2);
    }

    private static void jhRecur(int n, String str){
        System.out.println(str + "\"재귀함수가 뭔가요?\"");
        if (n == 0){
            System.out.println(str + "\"재귀함수는 자기 자신을 호출하는 함수라네\"");
            return;
        }
        System.out.println(str + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n" +
                str + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n" +
                str + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"");
        str += "____";
        jhRecur(n-1, str);
        System.out.println(str + "라고 답변하였지.");
    }
}
