package solved;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1003 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T, N; // T: test case, N: input
    /*
    ### 피보나치 함수
    - 시간 초과를 해결해야하는 재귀 호출 문제

    => 재귀가 아닌 반복문으로 해결
    - 재귀
    : 우리가 아는 피보나치 수열의 흐름대로 큰 값에서 작은 값으로 쪼개나가는 형식
    - 반복문
    : 기존 피보나치 수열의 흐름과 반대로, 작은 수부터 큰 수로 합쳐나가는 형식.
     때문에 여기서 작은 수에서 합쳐져가는 값을 저장해두어야 다음 값에 쓸 수 있다. 이를 위해 배열을 사용함.

     ### 피보나치 반복문
     - 피보나치 수열에서 값을 만들어나가는 최하단 부분은 결국 0과 1이다.
     - 0이 저장되는 배열과 1이 저장되는 배열을 생성한다.
     - 위 배열은 입력된 수 + 1(0부터 N까지) 만큼 저장할 수 있다.
     - 배열의 인덱스는 입력값, 해당 인덱스에 할당된 값은 개수로 판단.
     */
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(bf.readLine());
            if(N==0) {
                bw.write(1 + " " + 0 +"\n");
                continue;
            }
            if(N==1) {
                bw.write(0 + " " + 1 +"\n");
                continue;
            }
            int[] fiboZero = new int[N+1]; // N ~ 0
            int[] fiboOne = new int[N+1]; // N ~ 0
            // 피보나치 초기값; 입력이 0일 경우의 개수
            // fibo(0) => 0: 1, 1: 0
            // fibo(1) => 0: 0, 1: 1
            fiboZero[0] = 1;
            fiboZero[1] = 0;
            fiboOne[0] = 0;
            fiboOne[1] = 1;
            // fibo(2)부터 시작
            // fibo(0)와 fibo(1)을 쌓아가며 값을 만듬 => 1의 개수가 결국 해당 피보나치 수열의 값
            for (int j = 2; j <= N; j++) {
                fiboZero[j] = fiboZero[j-1] + fiboZero[j-2];
                fiboOne[j] = fiboOne[j-1] + fiboOne[j-2];
            }
            bw.write(fiboZero[N] + " " + fiboOne[N] + "\n");
        }
        bw.flush();
        bw.close();
    }
    static void fibonacci(int n){

    }
}
