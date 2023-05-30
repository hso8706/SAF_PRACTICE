package cert.second;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Solution_Prof {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T, N, max;
    static int[] subway, nums = new int[4];

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T+1; t++) {
            bw.write("#"+t+" ");
            N = Integer.parseInt(bf.readLine());
            subway = new int[N];
            st = new StringTokenizer(bf.readLine(), " ");
            for (int i = 0; i < N; i++) {
                subway[i] = Integer.parseInt(st.nextToken());
            }
            max = Integer.MIN_VALUE;
            comb(0,0);
            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void comb(int cnt, int start) {
        if(cnt == 4){
            for (int i = 0; i < 4; i++) {
                int next = (i+1)%4; // 원을 다루기위해 모듈러 연산
                if(Math.abs(nums[i] - nums[next]) == 1) return;
                if(Math.abs(nums[i] - nums[next]) == N-1) return;
            }
            int validity = 0;
            double first = (subway[nums[0]]+subway[nums[1]])*(subway[nums[0]]+subway[nums[1]]) + (subway[nums[2]]+subway[nums[3]])*(subway[nums[2]]+subway[nums[3]]);
            double second = (subway[nums[0]]+subway[nums[3]])*(subway[nums[0]]+subway[nums[3]]) + (subway[nums[2]]+subway[nums[1]])*(subway[nums[2]]+subway[nums[1]]);
            validity = (int) Math.max(first, second);
            max = Math.max(max, validity);
            return;
        }

        for (int i = start; i < N; i++) {
            nums[cnt] = i; // 인덱스로 처리
            comb(cnt+1, i+1);
        }
    }
}
