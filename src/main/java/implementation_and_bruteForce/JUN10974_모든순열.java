package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN10974_모든순열 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    그냥 순열 로직
     */
    static int N;
    static int[] totals;
    static int[] selectedNum;
    static boolean[] isSelected;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        totals = new int[N];
        selectedNum = new int[N];
        isSelected = new boolean[N];

        for (int i = 1; i < N+1; i++) {
            totals[i-1] = i;
        }

        all_permutation(0);
        bw.flush();
        bw.close();

    }

    private static void all_permutation(int cnt) throws IOException {
        if(cnt == N){
            for (int i = 0; i < N; i++) {
                bw.write(selectedNum[i]+" ");
            }
            bw.write("\n");
            return;
        }

        for (int i = 0; i < N; i++) {
            if(!isSelected[i]){
                isSelected[i] = true;
                selectedNum[cnt] = totals[i];
                all_permutation(cnt+1);
                isSelected[i] = false;
            }
        }
    }
}
