package com.LinkedList_01_230213;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2493_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int[] topHeight;
    static int[] resultArr;
    static ArrayDeque<Integer> topHeightStack;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        topHeight = new int[N];
        resultArr = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            topHeight[i] = Integer.parseInt(st.nextToken());
        }
        topHeightStack = new ArrayDeque<>();

        //stack 활용
        //스택에 인덱스를 저장함.
        //peek 를 활용하여 스택에 저장된 인덱스의 탑 높이를 조회
        //메인 컨셉
            //가장 높은 탑의 인덱스가 stack 의 top 위치하도록 유지
        topHeightStack.addFirst(0);
        for (int i = 1; i < N; i++) {
            while(!topHeightStack.isEmpty()) {
                if (topHeight[topHeightStack.peekFirst()] < topHeight[i]) {
                    topHeightStack.pollFirst();
                }
                else break;

            }
            if(!topHeightStack.isEmpty()){
                resultArr[i] = topHeightStack.peekFirst() + 1;
            }
            topHeightStack.addFirst(i);
        }
        for (int i = 0; i < N; i++) {
            bw.write(resultArr[i] + " ");
        }

        bw.flush();
        bw.close();
    }
}
