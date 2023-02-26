package solution;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2475 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int verifyNum = 0;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 5; i++) {
            verifyNum += Math.pow(Integer.parseInt(st.nextToken()), 2);
        }
        verifyNum %= 10;
        bw.write(verifyNum + "");
        bw.flush();
        bw.close();
    }
}
