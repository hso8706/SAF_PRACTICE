package solved;

import java.io.*;
import java.util.StringTokenizer;

public class JUN10026 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    문제풀이 요령
    : 주어진 입력값을 그래프로 치환하고, 간선으로 연결된 점접의 그룹을 나누는게 핵심
     */
    static int N;
    static String[][] boardForNormal;
    static String[][] boardForWeakness;

    static int[] dx = new int[]{-1, 0, 1, 0};//4방 탐색, 북-동-남-서
    static int[] dy = new int[]{0, 1, 0, -1};//4방 탐색, 북-동-남-서

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        boardForNormal = new String[N][N];
        boardForWeakness = new String[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < N; j++) {
                //일반인 board 초기화
                boardForNormal[i][j] = temp[j];
                //색약 board 초기화
                if (temp[j].equals("G")) boardForWeakness[i][j] = "R";
                else boardForWeakness[i][j] = temp[j];
            }
        }
    }
}
