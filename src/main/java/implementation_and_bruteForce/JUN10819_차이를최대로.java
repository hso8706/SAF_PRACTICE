package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN10819_차이를최대로 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - N개의 정수 배열
    - 배열의 순서를 바꿔서 순서대로 인접한 두 요소의 차이들을 모두 더한 값의 최대를 구하기
    - 오해 요소
        - 인접한 두 요소가 한 그룹으로 모두 그룹핑 되는줄 알았음.
        - 근데 그냥 연속되는 수의 차이를 모두 더한 값이었다.

    1. 순열 사용
    - 모든 경우의 수에서 가장 큰 값을 구하면 된다.
     */
    static int N;
    static int[] arr;
    static int[] temp;
    static boolean[] isSelected;
    static int sum;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        arr = new int[N];
        temp = new int[N];
        isSelected = new boolean[N];
        sum = Integer.MIN_VALUE;
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        permu(0);
        System.out.println(sum);
    }

    private static void permu(int cnt) {
        if(cnt == N){
            int tempSum = 0;
            for (int i = 0; i < N-1; i++) {
                tempSum += Math.abs(temp[i] - temp[i+1]);
            }
            sum = Math.max(sum, tempSum);
        }

        for (int i = 0; i < N; i++) {
            if(!isSelected[i]){
                isSelected[i] = true;
                temp[cnt] = arr[i];
                permu(cnt+1);
                isSelected[i] = false;
            }
        }
    }
}
