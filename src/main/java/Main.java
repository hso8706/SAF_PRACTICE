import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        ArrayList<Integer>[] tree = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            tree[i] = new ArrayList<>();
        }
        int[][] input = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            input[i][0] = Integer.parseInt(st.nextToken());
            input[i][1] = Integer.parseInt(st.nextToken());
        }


    }
}
