package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1182_부분수열의합 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
     ### 부분수열의 합
     - 수열 중 부분 수열의 합이 특정한 값(S)이 되는 경우 구하기

     ### 해결1.
     - 모든 부분 집합을 구하고, 각각의 부분 집합의 원소의 합을 구해서 S와 비교
        - 2초, 256MB, N<=20(2^20 == 대충 100만) 가능할 듯

     ### 해결2.
     - bfs 식으로 queue 사용하여 풀기
        - 훨씬 효율적이네
     */
    static int N, S;
    static int[] inputArr;
    static boolean[] isSelected;
    static int[] groupS;
    static int totalCnt;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        inputArr = new int[N];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }
        isSelected = new boolean[N];
        subset(0);
        bw.write(totalCnt + "");
        bw.flush();
        bw.close();
    }

    private static void subset(int cnt) throws IOException {
        if(cnt == N){
            groupS = new int[N];
            int checkTF = 0;
            for (int i = 0; i < N; i++) {
                if(isSelected[i]) {
                    groupS[i] = inputArr[i];
                    checkTF++;
                }
            }
            if(checkTF == 0) return;
            if(checkS()) totalCnt++;
            return;
        }

        isSelected[cnt] = true;
        subset(cnt+1);
        isSelected[cnt] = false;
        subset(cnt+1);
    }

    private static boolean checkS() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += groupS[i];
        }
        return sum == S;
    }
}
