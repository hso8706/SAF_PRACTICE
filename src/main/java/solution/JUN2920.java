package solution;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN2920 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int[] scale;
    static int[] asc = new int[]{1,2,3,4,5,6,7,8};
    static int[] desc = new int[]{8,7,6,5,4,3,2,1};

    public static void main(String[] args) throws IOException {
        scale = new int[8];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < 8; i++) {
            scale[i] = Integer.parseInt(st.nextToken());
        }
        if (Arrays.equals(scale, asc)) bw.write("ascending");
        else if (Arrays.equals(scale, desc)) bw.write("descending");
        else bw.write("mixed");
        bw.flush();
        bw.close();
    }
}
