package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN2003_수들의합 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    수열 중 연속된 인덱스의 합이 M이 되는 경우의 수 출력
     */
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

    }
}
