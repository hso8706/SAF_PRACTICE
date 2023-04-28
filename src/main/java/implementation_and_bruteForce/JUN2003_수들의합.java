package implementation_and_bruteForce;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2003_수들의합 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    수열 중 연속된 인덱스의 합이 M이 되는 경우의 수 출력
    
    1. i 번째부터 j 번째까지 합을 구해서 비교해보기
        - 무조건 안 될 것 같긴한데
        - ???? 이게 왜 됨?
     */
    static int N, M; // N: 수열 개수, M: 총합 목표
    static int[] arr;
    static int cnt;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            int sum = 0;
            for (int j = i; j < N; j++) {
                sum += arr[j];
                if(sum == M) {
                    cnt++;
                    break;
                }
                if(sum > M) break;;
            }
        }

        System.out.println(cnt);
    }
}
