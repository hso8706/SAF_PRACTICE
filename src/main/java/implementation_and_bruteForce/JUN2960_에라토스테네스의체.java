package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN2960_에라토스테네스의체 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    많이 사용하는 개념 - 차라리 아예 외워둘 것
    ### 에라토스테네스의 체
    - N보다 작거나 같은 모든 소수를 찾는 알고리즘
    - 원하는 구간까지의 소수를 구할 수 있는 알고리즘
        - 현재 문제의 알고리즘에서 제거하는 값을 찾지 않고, p = true, 제거되는 값을 false 로 하는 것 같이 상태를 이용해서 소수를 구할 수 있음

     */
    static int N, K;
    static ArrayList<Integer> totalNums;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        totalNums = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            totalNums.add(i);
        }

        int removed = eratos();
        System.out.println(removed);
    }

    private static int eratos() {
        //totalNums 에 존재하는 수 중 가장 작은 소수(P)를 찾는다.
        //P를 지우고, P의 배수를 크기 순서대로 지워나간다.
        //totalNums 에 수가 모두 지워질 때까지 반복한다.
        int returnNum = 0;
        int turn = 1;
        out:
        while (true) {
            int p = findMinPrime();
            int n = 1;
            while (p * n <= totalNums.get(totalNums.size() - 1)) {//totalNums 마지막 값보다 작으면 진행
                boolean check = totalNums.remove(Integer.valueOf(p * n)); // p값에 해당되는 요소 제거
                if (!check) {
                    n++;
                    continue; // 제거할 요소가 없을 시 다음 배수 확인
                }
                if (turn == K) {
                    returnNum = p * n;
                    break out;
                }
                n++;
                turn++;
            }
        }
        return returnNum;
    }

    private static int findMinPrime() {
        for (int i : totalNums) {
            if (i == 2) {
                return 2;
            }
            if (findPrime(i)) {
                return i;
            }
        }
        return 0;
    }

    private static boolean findPrime(int i) {
        int cnt = 0;
        for (int j = 2; j <= i; j++) {
            if (i % j == 0) {
                cnt++;
            }
        }
        return cnt == 1;
    }
}
