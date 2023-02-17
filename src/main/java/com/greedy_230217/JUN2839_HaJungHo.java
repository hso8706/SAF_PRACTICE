package com.greedy_230217;

import java.io.*;
import java.util.StringTokenizer;

public class JUN2839_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;

    static int bigCnt = 0;
    static int smallCnt = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        howManyBags();
        bw.flush();
        bw.close();

    }

    private static void howManyBags() throws IOException {
        while (N > 0) {
            if(N % 5 != 0){
                N -= 3;
                smallCnt++;
            }
            else {
                bigCnt = N / 5;
                N = 0;
            }
        }
        if(N == 0) bw.write(bigCnt + smallCnt + "\n");
        else bw.write(-1 + "\n");
    }
}
