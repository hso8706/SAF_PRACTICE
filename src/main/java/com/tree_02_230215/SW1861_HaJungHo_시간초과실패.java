package com.tree_02_230215;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//답은 나옴, 근데 시간 초과
//아무래도 N이 10^3인데, 기본적으로 O(N^2)이면서 재귀까지 호출해서 그런 듯 함.
public class SW1861_HaJungHo_시간초과실패 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T, N;
    static int[][] roomArr;
    static PriorityQueue<Point> pq;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T+1; tc++) {
            N = Integer.parseInt(bf.readLine());
            roomArr = new int[N][N];
            pq = new PriorityQueue<>();

            bw.write("#" + tc + " ");
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < N; j++) {
                    roomArr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    findRoom(i, j, 1);
                }
            }
            bw.write(pq.poll().startPoint + " " + pq.poll().maxRoute + "\n");
            bw.flush();
        }
        bw.close();
    }

    private static void findRoom(int y, int x, int cnt) throws IOException {
        int[] dx = {1, 0, -1 ,0};
        int[] dy = {0, 1, 0 ,-1};
        Point point = new Point(roomArr[y][x], cnt);

        for (int i = 0; i < 4; i++) {
            if (x + dx[i] < 0 || x + dx[i] >= N || y + dy[i] < 0 || y + dy[i] >= N) continue;
            if (roomArr[y][x] + 1 == roomArr[y + dy[i]][x + dx[i]]){
                point.maxRoute++;
                findRoom(y + dy[i], x + dx[i], cnt + 1);
            }
        }
        point.startPoint -= cnt -1;
        pq.offer(point);
    }

    public static class Point implements Comparable<Point>{
        public int startPoint;
        public int maxRoute;

        public Point(int startPoint, int maxRoute) {
            this.startPoint = startPoint;
            this.maxRoute = maxRoute;
        }

        @Override
        public int compareTo(Point other) {
            int x = other.maxRoute - this.maxRoute;
            if(x == 0){
                x = this.startPoint - other.startPoint;
            }
            return x;
        }
    }
}
