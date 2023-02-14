package com.LinkedList_01_230213;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SW1228_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T = 10;
//    static int T = 1;
    static int N_origin, N_order;
    static List<Integer> code_origin;

    public static void main(String[] args) throws IOException {
        for (int i = 1; i < T + 1 ; i++) {
            code_origin = new ArrayList<>();
            N_origin = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());
            makeOriginCode();

            N_order = Integer.parseInt(bf.readLine());
            st = new StringTokenizer(bf.readLine());
            reviseOriginCode();
            bw.write("#" + i + " ");
            printCode();
            bw.flush();
        }
        bw.close();
    }

    private static void printCode() throws IOException {
        for (int i = 0; i < 10; i++) {
            bw.write(code_origin.get(i) + " ");
        }
        bw.write("\n");
    }

    private static void reviseOriginCode() {
        while (st.countTokens() != 0){
            if (st.nextToken().equals("I")){
                int tempIdx = Integer.parseInt(st.nextToken());
                int tempLength = Integer.parseInt(st.nextToken());
                for (int i = tempIdx; i < tempIdx + tempLength; i++) {
                    code_origin.add(i, Integer.parseInt(st.nextToken()));
                }
            }
        }
    }

    private static void makeOriginCode() {
        while(st.countTokens() != 0){
            code_origin.add(Integer.parseInt(st.nextToken()));
        }
    }
}
