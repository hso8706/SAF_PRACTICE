package study.week_5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class JUN2473 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    # 조건
    - 3<=N<=5000
    - 3개 뽑아서 더하기 => 조합으로 하면 100억은 될 듯
        - 느낌이 이분 탐색
    # 로직
    - 최소 한 번은 더해서 0에 가까운 수가 됨을 찾아야함.
        - 이 과정은 무조건 완탐일 듯 한데
        -
     */
    static int N;
    static int[] numbers;
    static int[] results;
    public static void main(String[] args) throws IOException {

        init();
        findZero();

    }

    private static void findZero() {
    }

    private static void init() throws IOException {

        N = Integer.parseInt(bf.readLine());
        numbers = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        results = new int[3];
    }
}
