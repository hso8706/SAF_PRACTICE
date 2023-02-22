package aSAF.tree_02_230215;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN11286_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                int x = Math.abs(o1) - Math.abs(o2);
                if(x == 0){
                    x = o1 - o2;
                }
                return x;
            }
        });

        for (int i = 0; i < N; i++) {
            int x = Integer.parseInt(bf.readLine());
            if (x == 0 && pq.isEmpty()) {
                bw.write(0 + "\n");
                continue;
            }
            if (x == 0 && !pq.isEmpty()){
                bw.write(pq.poll() + "\n");
                continue;
            }
            pq.offer(x);
        }
        bw.flush();
        bw.close();

    }
}
