package cert.fifth;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_Sumin {
    static final StringBuilder sb = new StringBuilder();
    static long ans;
    static int N;
    static int[] balloons;
    static boolean[] visit;
    static int[] p;
    static boolean[] bomb;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("testcase/test5.txt")); //주석
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            balloons = new int[N];
            visit = new boolean[N];
            bomb = new boolean[N];
            p = new int[N];
            ans = 0;

            String[] inputStr = br.readLine().split(" ");
            for(int i = 0; i < N; i++){
                balloons[i] = Integer.parseInt(inputStr[i]);
            }

            selectTurn(0);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static void selectTurn(int cnt) {
        if (cnt == N){
            bomb = new boolean[N];
            ans = Math.max(ans, getGame(p));
            return;
        }
        for(int i = 0; i < N ; i++){
            if (visit[i]){
                continue;
            }
            visit[i] = true;
            p[cnt] = i;
            selectTurn(cnt+1);
            visit[i] = false;
        }
    }

    private static long getGame(int[] p) {
        long score = 0;
        for(int i = 0; i < p.length; i++){
            int nowTurn = p[i];
            bomb[nowTurn] = true;
            int leftIdx = getIdx(0, nowTurn);
            int rightIdx = getIdx(1, nowTurn);
            if (leftIdx == -1 && rightIdx == -1){
                score += balloons[nowTurn];
            }
            else if (leftIdx == -1 && rightIdx != -1){
                score += balloons[rightIdx];
            }
            else if (leftIdx != -1 && rightIdx == -1){
                score += balloons[leftIdx];
            }
            else{
                score += balloons[leftIdx] * balloons[rightIdx];
            }
        }

        return score;
    }

    private static int getIdx(int leftOrright, int nowTurn) {
        if (leftOrright == 0){
            //left
            for(int i = nowTurn - 1 ; i >= 0 ; i--){
                if (!bomb[i]){
                    return i;
                }
            }
        }
        else{
            //right
            for(int i = nowTurn + 1 ; i < N ; i++){
                if (!bomb[i]){
                    return i;
                }
            }
        }
        return -1;
    }
}
