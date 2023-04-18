package implementation_and_bruteForce;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN14501_퇴사 {
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
    static int[] dp; // N+1, 일수 x 최대 이익
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        input = new info[N+1];
        dp = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            input[i] = new info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 1; i < N+1; i++) {//몇 일 차의 dp를 구할 지 정함
            maxWhat(i);
        }
        int maxRes = Integer.MIN_VALUE;
        for (int val : dp){
//            System.out.println(val);
            maxRes = Math.max(maxRes, val);
        }
        bw.write(maxRes+"");
        bw.flush();
        bw.close();
    }

    private static void maxWhat(int i) {
        int maxVal = Integer.MIN_VALUE;
        int exp = i + input[i].days -1;
        int sum = input[i].incomes;
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{i, exp, sum});

        if(exp >= N+1) return;

        while(!queue.isEmpty()){
            int[] current = queue.pollFirst();
            int cDay = current[0];
            int cExp = current[1];
            int cSum = current[2];
            maxVal = Math.max(maxVal, cSum);

            for (int j = cDay+1; j < N+1; j++) {
                if(j <= cExp) continue;
                int nExp = j + input[j].days - 1;
                if(nExp >= N+1) continue;
                int nSum = cSum + input[j].incomes;
                queue.offerLast(new int[]{j, nExp, nSum});
            }
        }
        dp[i] = maxVal;
    }
}
