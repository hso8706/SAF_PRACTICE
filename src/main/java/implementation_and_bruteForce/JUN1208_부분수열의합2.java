package implementation_and_bruteForce;

import java.io.*;
import java.util.*;

public class JUN1208_부분수열의합2 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
     ### 부분수열의 합
     - 수열 중 부분 수열의 합이 특정한 값(S)이 되는 경우 구하기

     ### 해결1.
     - 이전 해결: 모든 부분 집합을 구하고, 각각의 부분 집합의 원소의 합을 구해서 S와 비교
        - 불가, 조건이 타이트해짐
     - 반 토막 해결
        - 20개 부분 집합 == 약 100만
        - 반 토막 부분집합의 값과 해당 값이 나오는 개수를 모두 저장
        - 반 토막 두 개의 그룹을 이중 반복문으로 합쳐보면서 S가 나오는지 확인 및 totalCnt 증가
            - 두 그룹이 모두 공집합인 경우는 제외
     */
    static int N, S;
    static int[] inputA;
    static int[] inputB;
    static boolean[] isSelectedA;
    static boolean[] isSelectedB;
    static Map<Integer, Integer> groupA;
    static Map<Integer, Integer> groupB;

    static int totalCnt;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        inputA = new int[N/2];
        inputB = new int[N-N/2];
        isSelectedA = new boolean[N/2];
        isSelectedB = new boolean[N-N/2];
        groupA = new HashMap<>();
        groupB = new HashMap<>();

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            if(i < N/2) inputA[i] = Integer.parseInt(st.nextToken());
            else inputB[i-N/2] = Integer.parseInt(st.nextToken());
        }
        System.out.println(Arrays.toString(inputA));
        System.out.println(Arrays.toString(inputB));
        sevSubsetA(0);
        sevSubsetB(0);
        for (int key : groupA.keySet()){
            System.out.println(key + ":" + groupA.get(key));
        }
        for (int key : groupB.keySet()){
            System.out.println(key + ":" + groupB.get(key));
        }
        for (int keyA : groupA.keySet()) {
            for (int keyB : groupB.keySet()) {
                if(keyA+keyB == S) totalCnt += groupA.get(keyA) + groupB.get(keyB);
            }
        }
        bw.write(totalCnt + "");
        bw.flush();
        bw.close();
    }

    private static void sevSubsetA(int cnt) {
        if(cnt == N/2){
            int sum = 0;
            for (int i = 0; i < cnt; i++) {
                if(isSelectedA[i]) {
                    sum += inputA[i];
                }
            }
            if(groupA.containsKey(sum)) groupA.put(sum, groupA.get(sum)+1);
            else groupA.put(sum, 1);
            return;
        }

        isSelectedA[cnt] = true;
        sevSubsetA(cnt+1);
        isSelectedA[cnt] = false;
        sevSubsetA(cnt+1);
    }
    private static void sevSubsetB(int cnt) {
        if(cnt == N-N/2){
            int sum = 0;
            for (int i = 0; i < cnt; i++) {
                if(isSelectedB[i]) {
                    sum += inputB[i];
                }
            }
            if(groupB.containsKey(sum)) groupB.put(sum, groupB.get(sum)+1);
            else groupB.put(sum, 1);
            return;
        }

        isSelectedB[cnt] = true;
        sevSubsetB(cnt+1);
        isSelectedB[cnt] = false;
        sevSubsetB(cnt+1);
    }

}
