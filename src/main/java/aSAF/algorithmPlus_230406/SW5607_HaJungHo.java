package aSAF.algorithmPlus_230406;

import java.io.*;
import java.util.StringTokenizer;

public class SW5607_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int T, N, R;
    static long[] factorialDp;
    static int MOD = 1234567891;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#"+tc+" ");
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            factorialDp = new long[1000001];
            factorialDp[0] = 1;
            for (int i = 1; i < 1000001; i++) {
                factorialDp[i] = (factorialDp[i-1]*i)%MOD;
            }
            long upper = factorialDp[N]%MOD;
            long bottom = ((factorialDp[R]%MOD) * (factorialDp[N-R]%MOD))%MOD;

            long bottomToUpper = recur(bottom, MOD - 2);
            bw.write((upper * bottomToUpper)%MOD+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static long recur(long num, int p) {
        if(p == 0) return 1;

        long half = recur(num, p/2);

        if(p%2 == 0) return ((half%MOD)*(half%MOD))%MOD;
        else return ((half%MOD)*(half*num%MOD))%MOD;
    }
}
