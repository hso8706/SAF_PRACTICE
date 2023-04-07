package aSAF.algorithmPlus01_230406;

import java.io.*;
import java.util.StringTokenizer;

public class SW3238 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*

     */
    static int T;
    static long n, r, p;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T; tc++) {
            st = new StringTokenizer(bf.readLine());
            n = Long.parseLong(st.nextToken());
            r = Long.parseLong(st.nextToken());
            p = Long.parseLong(st.nextToken());

            long nf = fatorial(n);
            long rf = fatorial(r);
            long pf = fatorial(p);
        }

    }

    private static long fatorial(long n) {
        long result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
