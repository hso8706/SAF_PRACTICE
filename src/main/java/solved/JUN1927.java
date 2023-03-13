package solved;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JUN1927 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());

        for (int i = 0; i < N; i++) {
            int input = Integer.parseInt(bf.readLine());
            if(input == 0){
                if(!pq.isEmpty()) {
                    bw.write(pq.poll() + "\n");
                }
                else bw.write(0 + "\n");
            }
            else {
                pq.offer(input);
            }
        }
        bw.flush();
        bw.close();
    }

}
