package solving;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class JUN11053 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 가장 긴 증가하는 부분 수열
    ...? 그냥 중복 제거하면 되는거 아닌가 싶은디
    => 입력되는 순서(인덱스)도 중요
    => queue와 visited 사용해보자
     */
    static int N;
    static int maxLen;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        maxLen = Integer.MIN_VALUE;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
        }

        bw.flush();
        bw.close();
    }
}
