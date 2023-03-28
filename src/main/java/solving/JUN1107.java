package solving;

import java.io.*;
import java.util.*;

public class JUN1107 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 리모컨
    - 100에서 시작, N번까지의 최소 횟수
    - 버튼 중 고장난 숫자 버튼 존재

    ### 문제 해결
    - 0~9 숫자와 10(+), 11(-)을 요소로하는 배열
     */
    static int[] N;
    static int M; //N: 목표 채널, M: 고장난 버튼 개수
    static int[] remoteController = new int[]{0,1,2,3,4,5,6,7,8,9,10,11};
    static boolean[] errorNums = new boolean[12]; // 고장난 버튼 체크 배열
    static int cnt;
    public static void main(String[] args) throws IOException {
        String[] temp = bf.readLine().split("");
        N = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            N[i] = Integer.parseInt(temp[i]);
        }
        M = Integer.parseInt(bf.readLine());
        Arrays.fill(errorNums, true);

        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            errorNums[Integer.parseInt(st.nextToken())] = false;
        }

//        pressButton();

    }

//    private static void pressButton() {
//        for (int i = 0; i < N.length; i++) {
//            if(errorNums[N[i]]) cnt++;
//            else
//        }
//    }
}
