package implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN14888_연산자끼워넣기 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 연산자 끼워넣기
    - N 개의 수, N-1 개의 연산자
    - 연산자
        - +, -, *, /
        - 연산자 우선 순위 없음. 왼쪽부터 오른쪽으로 순서대로 연산 진행
        - 나눗셈은 몫만 취함.
        - 음수 나눗셈은 양수로 전환하여 계산한 후 다시 음수로 변환
    - 2 <= N <= 11
        - 연산자 최대 10개 : 4^10 => 대략 100만, 완탐 가능

    ### 해결 1.
    - N-1 개의 순열 경우의 수 구하기
    - 하나의 경우의 수가 완성될 때마다 각 인덱스에 해당되는 값으로 연산하기
     */
    static int N;
    static int[] inputArr;
    static char[] operator;
    static boolean[] isSelected;
    static char[] selected;
    static int max;
    static int min;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        inputArr = new int[N];
        isSelected = new boolean[N-1];
        operator = new char[N - 1];
        selected = new char[N-1];
        max = Integer.MIN_VALUE;
        min = Integer.MAX_VALUE;


        //전체 수열 인풋 받기
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }

        //연산자 인풋 받기
        st = new StringTokenizer(bf.readLine());
        int idx = 0;
        for (int i = 0; i < 4; i++) {
            char op = 0;
            switch (i) {
                case 0:
                    op = '+';
                    break;
                case 1:
                    op = '-';
                    break;
                case 2:
                    op = '*';
                    break;
                case 3:
                    op = '/';
                    break;
            }
            int tempP = Integer.parseInt(st.nextToken());
            for (int j = 0; j < tempP; j++) {
                operator[idx] = op;
                idx++;
            }
        }

        permutation(0);
        bw.write(max +"\n" + min);
        bw.flush();
        bw.close();
    }

    private static void permutation(int cnt) {
        if (cnt == N-1){
            int result = inputArr[0];
            for (int i = 0; i < N-1; i++) {
                result = operating(i, result);
            }
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for (int i = 0; i < N-1; i++) {
            if(!isSelected[i]){
                isSelected[i] = true;
                selected[cnt] = operator[i] ;
                permutation(cnt+1);
                isSelected[i] = false;
            }
        }
    }

    private static int operating(int i, int result) {
        switch (selected[i]){
            case '+':
                result += inputArr[i+1];
                break;
            case '-':
                result -= inputArr[i+1];
                break;
            case '*':
                result *= inputArr[i+1];
                break;
            case '/':
                result /= inputArr[i+1];
                break;
        }
        return result;
    }
}
