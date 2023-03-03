package aSAF.dijkstralTest_230302;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN1753_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    최단 경로
    : 시작점에서 다른 모든 정점으로의 최단 경로를 출력
     */
    static int V, E; // V: 정점의 개수, E: 간선의 개수
    static int start; // 시작 정점 번호
    static int[] vertex; // 간선 번호 배열
    static int[] distance; // 거리 배열
    static boolean[] visited; // 방문 여부 배열


    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        vertex = new int[V+1];
        visited = new boolean[V+1];
        distance = new int[V+1];
        start = Integer.parseInt(bf.readLine());
        // 남의 코드 깔끔한거로 정리하기 => 영서
    }
}
