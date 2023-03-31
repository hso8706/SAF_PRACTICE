package aSAF.DynamicProgramming4_230331;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1194_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 달이 차오른다, 가자
    - 달 얘긴 쓸모없고 미로 탈출에 집중 => 구현 문제일 것 같음

     */
    
    static int N, M; //N: 세로, M: 가로
    static String[][] maze;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < M; j++) {
                maze[i][j] = temp[j];
            }
        }



    }
}
