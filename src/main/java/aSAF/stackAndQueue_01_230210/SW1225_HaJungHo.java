package aSAF.stackAndQueue_01_230210;

import java.io.*;
import java.util.*;

public class SW1225_HaJungHo {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = 10;
        for (int tc = 1; tc < T +1 ; tc++) {
            bw.write("#" + bf.readLine() + " " );

            st = new StringTokenizer(bf.readLine());
            Queue<Integer> queue = new LinkedList<>();
            for (int i = 0; i < 8; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }
            label: while(true){
                for (int num = 1; num <= 5; num++) {
                    if(queue.peek() - num <= 0){
                        queue.poll();
                        queue.offer(0);
                        break label;
                    }
                    queue.offer(queue.poll()-num);
                }
            }
            while(!queue.isEmpty()){
                bw.write(queue.poll() + " ");
            }
            bw.write("\n");
            bw.flush();
        }
        bw.close();
    }
}
