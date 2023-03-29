package solved;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN10989 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 수 정렬하기 3
    - N개의 수가 주어졌을 때, 오름차순으로 정렬하기
     */
    static int N;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(arr);
        for (int i : arr){
            bw.write(i + "\n");
        }
        bw.flush();
        bw.close();
    }
}
