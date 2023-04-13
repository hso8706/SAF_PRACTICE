package solved.implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1436_영화감독숌 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String apocalypse = "666";
        String result = "";
        int head = 0;
        int tail = -1;
        int headTail = 0;
        int N = Integer.parseInt(bf.readLine());

        for (int i = 1; i < N+1; i++) {
            if(head%10 < 6){
                if(head == 0) result = apocalypse;
                else result = head + apocalypse;
                head++;
            }
            else {
                headTail = head / 10;
                tail++;
                if (headTail == 0) result = apocalypse + tail;
                else result = headTail + apocalypse + tail;
                if(tail == 9){
                    tail = -1;
                    head += 2;
                }
            }
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
    }
}