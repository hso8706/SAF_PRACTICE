package aSAF.graph03_230224;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN10026_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static String[][] paintForNormalPerson;
    static String[][] paintForColorWeakness;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] groups;
    static int cnt;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        paintForNormalPerson = new String[N][N];
        paintForColorWeakness = new String[N][N];

        for (int i = 0; i < N; i++) {
            String[] temp = bf.readLine().split("");
            for (int j = 0; j < N; j++) {
                paintForNormalPerson[i][j] = temp[j];

                if(temp[j].equals("G")) paintForColorWeakness[i][j] = "R";
                else paintForColorWeakness[i][j] = temp[j];
            }
        }
        visited = new boolean[N][N];
        groups =new int[N][N];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    groups[i][j] = cnt;
                    dfsForNormalPeople(i, j, paintForNormalPerson[i][j]);
                    cnt++;
                }
            }
        }
        bw.write(cnt + " ");
        visited = new boolean[N][N];
        groups =new int[N][N];
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    groups[i][j] = cnt;
                    dfsForColorWeakness(i, j, paintForColorWeakness[i][j]);
                    cnt++;
                }
            }
        }
        bw.write(cnt + "");
        bw.flush();
        bw.close();
    }

    private static void dfsForNormalPeople(int x, int y, String st) {
        for (int k = 0; k < 4; k++) {
            int ox = x + dx[k];
            int oy = y + dy[k];
            if(ox<0 || ox>=N || oy<0 || oy>=N) continue;
            if(paintForNormalPerson[ox][oy].equals(st) && !visited[ox][oy]){
                visited[ox][oy] = true;
                groups[ox][oy] = cnt;
                dfsForNormalPeople(ox,oy,st);
            }
        }
    }

    private static void dfsForColorWeakness(int x, int y, String st) {
        //4방 탐색
        //걸린길로 더 진행하게끔 재귀 호출
        for (int k = 0; k < 4; k++) {
            int ox = x + dx[k];
            int oy = y + dy[k];
            if(ox<0 || ox>=N || oy<0 || oy>=N) continue;
            if(paintForColorWeakness[ox][oy].equals(st) && !visited[ox][oy]){
                visited[ox][oy] = true;
                groups[ox][oy] = cnt;
                dfsForColorWeakness(ox,oy,st);
            }
        }
    }
}
