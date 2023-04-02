package solved;

import java.io.*;
import java.util.StringTokenizer;

public class JUN15652 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    - 중복 조합 뽑기
    - 오름차순 정렬
     */
    static int N, M;
    static int[] NArr;
    static int[] selected;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        NArr = new int[N+1];
        selected = new int[M+1];
        for (int i = 1; i <= N; i++) {
            NArr[i] = i;
        }

        makingArr(1, 1);
        bw.flush();
        bw.close();
    }

    private static void makingArr(int cnt, int start) throws IOException {
        if(cnt == M+1){
            for (int i = 1; i <= M; i++) {
                bw.write(selected[i] + " ");
            }
            bw.write("\n");
            return;
        }

        for (int i = start; i <= N; i++) {
            selected[cnt] = NArr[i];
            makingArr(cnt+1, i);
        }
    }
}
