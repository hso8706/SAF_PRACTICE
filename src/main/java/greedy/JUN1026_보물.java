package greedy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN1026_보물 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    해결방법 1.

    - 가장 작은 수 x 가장 큰 수
    - 순서대로 각각의 배열에서 값을 하나씩 뽑기
     */
    static int min, max, n, result;
    static int[] A, B;
    static boolean[] visit_A, visit_B;

    public static void main(String[] args) throws IOException {

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        result = 0;

        n = Integer.parseInt(bf.readLine());

        A = new int[n];
        B = new int[n];
        visit_A = new boolean[n];
        visit_B = new boolean[n];

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < n; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            max = isMaxValue(max);
            min = isMinValue(min);
            System.out.println(max +" " +min);

            result += max * min;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();

    }

    private static int isMinValue(int min) {
        int m = min;
        int idx = -1;

        for (int i = 0; i < n; i++) {
            if(m >= B[i] && !visit_B[i]) {
                m = B[i];
                idx = i;
            }
        }
        visit_B[idx] = true;

        return m;
    }

    private static int isMaxValue(int max) {
        int m = max;
        int idx = -1;

        for (int i = 0; i < n; i++) {
            if(max <= A[i] && !visit_A[i]) {
                max = A[i];
                idx = i;
            }
        }
        visit_A[idx] = true;

        return m;
    }

}
