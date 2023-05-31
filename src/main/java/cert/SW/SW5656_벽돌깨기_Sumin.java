package cert.SW;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SW5656_벽돌깨기_Sumin {
    static StringBuilder sb = new StringBuilder();
    static int N, W, H;
    static int[][] map;
    static int[][] copy;
    static int ans;
    static class Point{
        int x, y, val;

        public Point(int x, int y, int val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("testcase/SWE_5656.txt")); //주석
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int t=1; t <= T; t++) {
            String[] nmr = br.readLine().split(" ");
            N = Integer.parseInt(nmr[0]);
            W = Integer.parseInt(nmr[1]);
            H = Integer.parseInt(nmr[2]);
            map = new int[H][W];
            copy = new int[H][W];
            ans = Integer.MAX_VALUE;

            for(int i = 0; i < H; i++){
                String[] iStr = br.readLine().split(" ");
                for(int j = 0; j < W; j ++){
                    map[i][j] = Integer.parseInt(iStr[j]);
                }
            }

            chooseBall(0, map);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.print(sb);
        br.close();
    }

    private static void chooseBall(int cnt, int[][] prevmap) {
        if (cnt == N){
            //N개 떨구는 순서 다 정함
            ans = Math.min(ans, getRemain(copy));
            return;
        }
        for(int i = 0; i < W; i++){
            copyMap(prevmap);
            int toph = selectTop(i);
            if (toph == -1){
                chooseBall(cnt+1, prevmap);
            }
            else{
                breakBlock(toph, i);
                downBlock();
                chooseBall(cnt+1, copy);
            }
        }
    }

    private static void printMap() {
        System.out.println("print");
        for(int i = 0; i < H; i++){
            System.out.println(Arrays.toString(copy[i]));
        }
    }

    private static int getRemain(int[][] rmap) {
        int cnt = 0;
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if (rmap[i][j] != 0){
                    cnt ++;
                }
            }
        }
        return cnt;
    }

    private static void downBlock() {
        for(int i = 0; i < W; i++){
            Queue<Integer> remains = new LinkedList<>();
            for(int j = 0; j < H; j++){
                if (copy[j][i] != 0){
                    remains.add(copy[j][i]);
                }
            }
            for(int x = 0; x < H; x++){
                if (x < H - remains.size()){
                    copy[x][i] = 0;
                }
                else{
                    copy[x][i] = remains.poll();
                }
            }
        }
    }

    private static void breakBlock(int x, int y) {
        int [] dx = {-1, 1, 0, 0};
        int [] dy = {0, 0, 1, -1};
        Queue<Point> queue = new LinkedList<>();
        if(copy[x][y]>1) {
            queue.offer(new Point(x, y, copy[x][y]));
        }
        copy[x][y] = 0;

        while(!queue.isEmpty()) {
            Point now = queue.poll();
            for(int d=0; d<4; d++) {
                int nx = now.x;
                int ny = now.y;
                for(int k = 1; k< now.val; k++) {
                    nx += dx[d];
                    ny += dy[d];
                    if(inRange(nx, ny) && copy[nx][ny] != 0) {
                        if(copy[nx][ny] > 1) {
                            queue.offer(new Point(nx, ny, copy[nx][ny]));
                        }
                        copy[nx][ny] = 0;
                    }
                }
            }
        }
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    private static int selectTop(int j) {
        for(int i = 0; i < H; i++){
            if (copy[i][j] != 0){
                return i;
            }
        }
        return -1;
    }

    private static void copyMap(int[][] prevmap) {
        copy = new int[H][W];
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                copy[i][j] = prevmap[i][j];
            }
        }
    }
}
