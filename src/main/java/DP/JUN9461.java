package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN9461 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int t, n;
    public static void main(String[] args) throws IOException {

        t = Integer.parseInt(bf.readLine());
        for (int T = 0; T < t; T++) {
            n = Integer.parseInt(bf.readLine());
            long[] padovan = new long[101];
            padovan[5] = 2;
            padovan[4] = 2;
            padovan[3] = 1;
            padovan[2] = 1;
            padovan[1] = 1;
            for(int i=6; i<=n; i++){
                padovan[i] = padovan[i-1] + padovan[i-5];
            }
            bw.write(padovan[n]+"\n");
        }
        bw.flush();
        bw.close();

    }

}
