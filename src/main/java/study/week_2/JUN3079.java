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

        init();
        findMinTime();
        System.out.println(minTime);
    }

    /*
    - 이분 탐색 활용 => O(logN)
    - 기본 로직
        - 최소 시간과 최대 시간을 설정
        - 중간 지점의 시간을 몇 개의 심사대에서 해결할 수 있는지 개수를 파악
        - 해당 개수가 모든 사람(M)을 충족하는지 여부를 판단하여 minTime 체크
            - 충족한다면 minTime 갱신과 최대 시간(high)을 수정
            - 충족하지 못하면 회소 시간(low)를 수정
     */
    private static void findMinTime() {
        long low = 0;
        long high = M * max;

        while(low <= high){
            long mid = (low+high)/2;
            long sum = 0;

            for(long i : T){//시간: 10^6
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

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = new int[N];
        minTime = Long.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(bf.readLine());
            max =Math.max(max, T[i]);
        }
    }
}
