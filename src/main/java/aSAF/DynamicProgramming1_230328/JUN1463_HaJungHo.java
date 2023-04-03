package aSAF.DynamicProgramming1_230328;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1463_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 1로 만들기
    - 주어진 값을 제한된 연산을 통해서 1로 만들기
    - 연산을 사용하는 횟수의 최솟값을 출력
    - 연산 3가지
        - 3으로 나누어 떨어지면 3으로 나눔
        - 2로 나누어 떨어지면 2로 나눔
        - 1을 뺌
     op[1]: /3, op[2]: /2, op[3]: -1
     dp[2] = 1;
     dp[3] = 1;
     dp[4] = 2-2 / 1-3 / 3-1 = 2;
     dp[5] = 1-dp[4] = 1 + 2
     dp[6] = 1-dp[5], 2-dp[3], 3-dp[2]
     */
    static int[] result;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bf.readLine());
        result = new int[1000001];
        result[2] = 1;
        result[3] = 1;
        for (int i = 4; i <= N; i++) {
            if (i%6 == 0){
                result[i] = Math.min(1 + result[i / 2], Math.min(1 + result[i / 3], 1 + result[i - 1]));
            }
            else if (i%3 == 0) { // /3
                result[i] = Math.min(1 + result[i / 3], 1 + result[i - 1]);
            }
            else if (i%2 == 0) { // /2
                result[i] = Math.min(1 + result[i / 2], 1 + result[i - 1]);
            }
            else { // -1
                result[i] = 1 + result[i-1];
            }
        }
        System.out.println(result[N]);

    }
}
