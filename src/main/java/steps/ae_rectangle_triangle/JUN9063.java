package steps.ae_rectangle_triangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN9063 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        int lx = Integer.MAX_VALUE;
        int rx = Integer.MIN_VALUE;
        int dy = Integer.MAX_VALUE;
        int uy = Integer.MIN_VALUE;
        int N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int cx = Integer.parseInt(st.nextToken());
            int cy = Integer.parseInt(st.nextToken());
            lx = Math.min(lx, cx);
            rx = Math.max(rx, cx);
            dy = Math.min(dy, cy);
            uy = Math.max(uy, cy);
        }
        System.out.println((rx-lx)*(uy-dy));
    }
}
