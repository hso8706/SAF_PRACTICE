import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        int max = Integer.MIN_VALUE;
        int[] where = new int[2];

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < 9; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(max < temp) {
                    max = temp;
                    where[0] = i+1;
                    where[1] = j+1;
                }
            }
        }
        bw.write(max + "\n" + where[0] + " " + where[1]);
        bw.flush();
        bw.close();


    }
}
