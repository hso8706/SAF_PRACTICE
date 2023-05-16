package forA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SW2115_벌꿀채취 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 정사각형 벌통
    - 각 칸은 꿀의 양
    - N: 벌통의 size, M: 벌통의 개수
    - 두 명의 일꾼
        - 각 일꾼은 M개의 벌통만큼 가로로 연속되도록 선택 할 수 있음
        - 두 명의 일꾼은 선택한 벌통이 겹치면 안됨.
        - 하나의 벌통에서 채취한 꿀은 하나의 통에 담는다. (여러 통을 합치지 않는다)
        - 각 일꾼이 채취할 수 있는 꿀의 양은 한정되어있다(C)

    - 일꾼이 담당할 벌통 정하기
        - 조합 이용
            - 각 일꾼의 시작점을 정한다.
            - 각 일꾼의 시작점의 거리가 M 이상이어야 한다. (M 미만은 겹친다고 판단할 수 있음.)
    - 담당 벌통들에서 어떤 벌통들의 꿀을 떠야 최대의 이익이 나는지
        - 담당 벌통 배열의 부분 집합을 구하고, 부분 집합의 요소 총합이 C 이하인지 판단
        - 최대 꿀양을 갱신하며 진행
     */
    static int T, N, M, C;
    static int[][] combs;
    static int[] a_worker;
    static int[] b_worker;
    static int maxValue;
    static boolean[] isSelected_A;
    static boolean[] isSelected_B;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t < T + 1; t++) {
            bw.write("#" + t + " ");

            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            combs = new int[N][N];
            a_worker = new int[M];
            b_worker = new int[M];
            maxValue = 0;
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    combs[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < N; i++) {//a
                for (int j = 0; j <= N - M; j++) {//a
                    for (int aw = 0; aw < M; aw++) {//a_combs
                        a_worker[aw] = combs[i][j + aw];
                    }
                    for (int a = i; a < N; a++) {//b
                        if (a == i) {
                            if (j + M >= N) {
                                continue;
                            }
                            for (int b = j + M; b <= N - M; b++) {//b
                                for (int bw = 0; bw < M; bw++) {//b_combs
                                    b_worker[bw] = combs[a][b + bw];
                                }
                                //maxValue 갱신
                                // 부분 집합
                                isSelected_A = new boolean[M];
                                subset_A(0);
                            }
                        } else {
                            for (int b = 0; b <= N - M; b++) {//b
                                for (int bw = 0; bw < M; bw++) {//b_combs
                                    b_worker[bw] = combs[a][b + bw];
                                }
                                //maxValue 갱신
                                isSelected_A = new boolean[M];
                                subset_A(0);
                            }
                        }
                    }
                }
            }
            bw.write(maxValue + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void subset_A(int cnt) {
        if (cnt == M) {
            int check = 0;
            int sum = 0;
            for (int i = 0; i < M; i++) {
                if (isSelected_A[i]) {
                    check += a_worker[i];
                }
            }
            if (check <= C) {
                for (int i = 0; i < M; i++) {
                    if (isSelected_A[i]) {
                        sum += Math.pow(a_worker[i], 2);
                    }
                }
                isSelected_B = new boolean[M];
                subset_B(0, sum);
            }
            return;
        }

        isSelected_A[cnt] = true;
        subset_A(cnt + 1);
        isSelected_A[cnt] = false;
        subset_A(cnt + 1);
    }

    private static void subset_B(int cnt, int a_sum) {
        if (cnt == M) {
            int check = 0;
            int sum = 0;
            for (int i = 0; i < M; i++) {
                if (isSelected_B[i]) {
                    check += b_worker[i];
                }
            }
            if (check <= C) {
                for (int i = 0; i < M; i++) {
                    if (isSelected_B[i]) {
                        sum += Math.pow(b_worker[i], 2);
                    }
                }
                maxValue = Math.max(maxValue, (a_sum + sum));
            }
            return;
        }

        isSelected_B[cnt] = true;
        subset_B(cnt + 1, a_sum);
        isSelected_B[cnt] = false;
        subset_B(cnt + 1, a_sum);
    }
}
