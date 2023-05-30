package cert.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_Sumin {
    static final StringBuilder sb = new StringBuilder();
    static long ans;
    static int[] stations;
    static int N;
    static int[] candidateStation;
    static int[] a;
    static int[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            stations = new int[N];
            candidateStation = new int[4];

            ans = 0L;
            a =  new int[2];
            b = new int[2];
            String[] inputStr = br.readLine().split(" ");
            for(int i = 0; i < N; i++){
                stations[i] = Integer.parseInt(inputStr[i]);
            }

            chooseStation(0 , 0);
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();

    }

    private static void chooseStation(int cnt, int start) {
        if (cnt == 4) {
            //[1,3,5,7]
            //[1,3] [5,7]
            //[1.7] [3,5]
            a = new int[]{candidateStation[0], candidateStation[1]};
            b = new int[]{candidateStation[2], candidateStation[3]};
            if (isSuccess(a, b)){
                ans = Math.max(ans, getScore(a, b));
            }

            a = new int[]{candidateStation[0], candidateStation[3]};
            b = new int[]{candidateStation[1], candidateStation[2]};
            if (isSuccess(a, b)){
                ans = Math.max(ans, getScore(a, b));
            }

            return;
        }
        for(int i = start; i < N; i++){
            candidateStation[cnt] = i;
            chooseStation(cnt+1, i+1);
        }
    }

    private static long getScore(int[] a, int[] b) {
        return (long) (stations[a[0]] + stations[a[1]]) *(stations[a[0]] + stations[a[1]]) + (long) (stations[b[0]] + stations[b[1]]) *(stations[b[0]] + stations[b[1]]);
    }

    private static boolean isSuccess(int[] a, int[] b) {
        if (Math.abs(a[0] - a[1]) == 1 || Math.abs(b[0] - b[1]) == 1){
            return false;
        }
        if (Math.abs(a[0] - b[0]) == 1 || Math.abs(a[1] - b[1]) == 1){
            return false;
        }
        if (Math.abs(a[0] - b[1]) == 1 || Math.abs(a[1] - b[0]) == 1){
            return false;
        }

        return true;
    }
}