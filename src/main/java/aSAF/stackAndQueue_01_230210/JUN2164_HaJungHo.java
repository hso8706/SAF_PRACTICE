package aSAF.stackAndQueue_01_230210;

import java.io.*;
import java.util.ArrayDeque;

public class JUN2164_HaJungHo {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(bf.readLine());
        ArrayDeque<Integer> cards = new ArrayDeque<>();

        for (int i = 1; i < N + 1; i++) {
            cards.offer(i);
        }

        while(cards.size() > 1){
            cards.pollFirst();
            if (cards.size() == 1) break;
            cards.addLast(cards.pollFirst());
        }
        bw.write(cards.pollFirst() + "\n");
        bw.flush();
        bw.close();
    }
}
