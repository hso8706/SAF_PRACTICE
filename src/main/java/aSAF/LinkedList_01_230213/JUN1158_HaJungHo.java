package aSAF.LinkedList_01_230213;

import java.io.*;
import java.util.*;

public class JUN1158_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, K;
    static ArrayDeque<Integer> circleArr;
    static List<Integer> resultArr;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        circleArr = new ArrayDeque<>();
        resultArr = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            circleArr.offerLast(i+1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if(circleArr.isEmpty()) break;
                if(j == K-1){
                    resultArr.add(circleArr.pollFirst());
                }
                else circleArr.addLast(circleArr.pollFirst());
            }
        }
        System.out.print("<");
        for (int i = 0; i < resultArr.size(); i++) {
            System.out.print(resultArr.get(i));
            if(i == resultArr.size() -1 ) break;
            System.out.print(", ");
        }
        System.out.print(">");
    }
}
