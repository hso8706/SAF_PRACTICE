package solved.forA;

import java.io.*;
import java.util.StringTokenizer;

public class SW1486_장훈이의높은선반 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 장훈이의 높은 선반
    - 사람 탑...?ㅋㅋ;;
    - 최소 필요 높이(B)를 넘는 H의 합 중 최소값 구하기
    - 출력: 키의 합 중 최소값에서 선반 높이를 뺀 값

    ### 풀이1.
    - 부분 집합의 합들을 저장한 배열을 만들고 이 중에서 선택하여 값 출력
     */
    static int T, N, B; // T: tc, N: 점원 수, B: 선반 높이
    static int[] height; // 점원들 키 배열
    static int[] subSum; // 부분집합의 합 배열
    static boolean[] selected;
    static int minDif; // 부분집합의 합-B값

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            bw.write("#"+ tc + " ");
            st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            height = new int[N];
            subSum = new int[(int)Math.pow(2, N)];
            selected = new boolean[N];
            minDif = Integer.MAX_VALUE;

            st = new StringTokenizer(bf.readLine());
            for (int i = 0; i < N; i++) {
                height[i] = Integer.parseInt(st.nextToken());
            }
            subset(0);
            bw.write(minDif + "\n");
        }
        bw.flush();
        bw.close();
    }

    private static void subset(int cnt) {
        if(cnt == N){
            int tempSum = 0;
            for (int i = 0; i < N; i++) {
                if(selected[i]) tempSum += height[i];
            }
            int dif = tempSum - B;
            if (dif >= 0 && dif < minDif) minDif = dif;
            return;
        }

        selected[cnt] = true;
        subset(cnt+1);
        selected[cnt] = false;
        subset(cnt+1);
    }
}
