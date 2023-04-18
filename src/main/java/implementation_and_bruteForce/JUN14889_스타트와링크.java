package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN14889_스타트와링크 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    최대 이익을 얻을 수 있는 상담 일정

    - 최대 상담 일정인 순간이 최대 이익인지 파악해야함
        - 최대 상담 일정인 순간마다 이익의 합을 저장하자
     */
    static class info {
        int days;
        int incomes;

        public info(int days, int incomes) {
            this.days = days;
            this.incomes = incomes;
        }
    }
    static info[] input; // 소요 일수, 얻는 이익
    static int[][] dp; // N+1 x N+1
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        input = new info[N+1];
        dp = new int[N+1][N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            input[i] = new info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }
}
