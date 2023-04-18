package implementation_and_bruteForce;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN14889_스타트와링크 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    N/2개를 뽑는 조합
    => N/2, N/2의 두 개의 그룹 생성
    그룹 각각의 Sij, Sji를 다 더해주기
     */
    static int N;
    static int[][] startLink;
    static int[] totalM;
    static int[] groupA;
    static int[] groupB;
    static boolean[] selectM;
    static int minVal;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        startLink = new int[N + 1][N + 1];
        totalM = new int[N + 1];
        selectM = new boolean[N + 1];
        minVal = Integer.MAX_VALUE;
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < N + 1; j++) {
                startLink[i][j] = Integer.parseInt(st.nextToken());
            }
            totalM[i] = i;
        }
        pickMembers(1, 1);
        bw.write(minVal+"");
        bw.flush();
        bw.close();
    }

    private static void pickMembers(int cnt, int start) {
        if (cnt == (N / 2)+1) {
            groupA = new int[N + 1];
            groupB = new int[N + 1];
            for (int i = 1; i < N+1; i++) {
                if(selectM[i]) groupA[i] = i;
                else groupB[i] = i;
            }
            minVal = Math.min(minVal, difVal());
            return;
        }

        for (int i = start; i < N + 1; i++) {
            selectM[i] = true;
            pickMembers(cnt + 1, i + 1);
            selectM[i] = false;
        }
    }

    private static int difVal() {
        int dif = 0;
        //groupA
        int sumA = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = i+1; j < N+1; j++) {
                if(groupA[i] == 0 || groupA[j] == 0) continue;
                sumA += startLink[groupA[i]][groupA[j]] + startLink[groupA[j]][groupA[i]];
            }
        }
        //groupB
        int sumB = 0;
        for (int i = 1; i < N+1; i++) {
            for (int j = i+1; j < N+1; j++) {
                if(groupB[i] == 0 || groupB[j] == 0) continue;
                sumB += startLink[groupB[i]][groupB[j]] + startLink[groupB[j]][groupB[i]];
            }
        }
        dif = Math.abs(sumA-sumB);
        return dif;
    }
}
