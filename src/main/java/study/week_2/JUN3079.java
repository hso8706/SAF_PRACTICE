package study.week_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN3079 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    /*
    - 이중 반복문으로 해결 불가
    - T: int 형 범위 내에 속함, 10억
    - M: 10억
     */

    static int N;
    static long M;
    static int[] T;
    static long max;
    static long minTime;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = new int[N];
        minTime = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(bf.readLine());
            max =Math.max(max, T[i]);
        }

        findMinTime();
        System.out.println(minTime);
    }

    private static void findMinTime() {
        long low = 0;
        long high = M * max;

        while(low <= high){
            long mid = (low+high)/2;
            long sum = 0;

            for(long i : T){
                long cnt = mid/i;

                if(sum >= M) break;
                sum += cnt;
            }

            if(sum>=M){
                high = mid - 1;
                minTime = Math.min(mid, minTime);
            }
            else {
                low = mid + 1;
            }
        }
    }
}
