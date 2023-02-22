package aSAF.greedy_230217;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class JUN16435_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, L;
    static ArrayList<Integer> height;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        height = new ArrayList<>();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            height.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(height);
        for (int i = 0; i < N; i++) {
            if (height.get(i) <= L ){
                L++;
            }
            else break;
        }
        bw.write(L + "\n");
        bw.flush();
        bw.close();
    }
}
