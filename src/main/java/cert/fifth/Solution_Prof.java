package cert.fifth;

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

    static int N, max;
    static int[] balloons, nums; //nums: 뽑힌 풍선 순서(idx)
    static boolean[] visit;

    public static void main(String[] args) throws NumberFormatException, IOException {
        int T = Integer.parseInt(bf.readLine());

        for (int t = 1; t < T + 1; t++) {
            bw.write("#" + t + " ");
            N = Integer.parseInt(bf.readLine());
            balloons = new int[N];
            nums = new int[N];
            visit = new boolean[N];
            st = new StringTokenizer(bf.readLine(), " ");
            for (int i = 0; i < N; i++) {
                balloons[i] = Integer.parseInt(st.nextToken());
            }

            max = Integer.MIN_VALUE;
            perm(0);
            bw.write(max + "\n");
        }
        bw.flush();
        bw.close();

    }

    private static void perm(int cnt) {
        if(cnt == N){
            int[] copys = new int[N];
            for (int i = 0; i < N; i++) {
                copys[i] = balloons[i];
            }
            int score = 0;

            for (int i = 0; i < N; i++) {
                int b = nums[i];

                int lb = 0;
                for (int l = b-1; l >= 0 ; l--) {
                    if(copys[l] != 0){
                        lb = copys[l];
                        break;
                    }
                }

                int rb=0;
                for (int r = b+1; r < N; r++) {
                    if(copys[r] != 0){
                        rb = copys[r];
                        break;
                    }
                }

                if(lb != 0 && rb!= 0) score += lb*rb;
                else if(lb != 0) score += lb;
                else if(rb != 0) score += rb;
                else score += copys[b];
                copys[b] = 0;
            }

            max = Math.max(max, score);
            return;
        }

        for (int i = 0; i < N; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            nums[cnt] = i;
            perm(cnt+1);
            visit[i] = false;
        }
    }
}
