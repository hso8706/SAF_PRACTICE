package steps.ag_bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN1018 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static char[][] map;
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1};
    static int minValue;
    public static void main(String[] args) throws IOException {

        init();
        for (int i = 0; i <= N-8; i++) {
            for (int j = 0; j <= M-8; j++) {
                //잘라낸 판의 모든 점에서 시작해보기
                for (int k = 0; k < 8; k++) {
                    for (int l = 0; l < 8; l++) {
                        //8*8 잘라내기
                        char[][] tempMap = cloneMap(i, j);
                        logic(k,l,tempMap);
                    }
                }
            }
        }
        System.out.println(minValue);
    }

    private static void logic(int x, int y, char[][] tempMap) {
        int cnt = 0;
        boolean[][] visited = new boolean[8][8];
        Deque<int[]> deque = new ArrayDeque<>();
        deque.offer(new int[]{x,y});
        visited[x][y] = true;

        while(!deque.isEmpty()){
            int[] current = deque.poll();
            int cx = current[0];
            int cy = current[1];
            char cColor = tempMap[cx][cy];

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if(nx<0||ny<0||nx>=8||ny>=8) continue;
                if(!visited[nx][ny]){
                    char nColor = tempMap[nx][ny];
                    if(cColor == nColor){
                        tempMap[nx][ny] = cColor == 'W' ? 'B' : 'W';
                        cnt++;
                    }
                    visited[nx][ny] = true;
                    deque.offer(new int[]{nx, ny});
                }
            }
        }
        minValue = Math.min(minValue, cnt);
    }

    private static char[][] cloneMap(int x, int y) {
        char[][] result = new char[8][8];
        for (int i = x; i < x+8; i++) {
            for (int j = y; j < y+8; j++) {
                result[i-x][j-y] = map[i][j];
            }
        }
        return result;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = bf.readLine().toCharArray();
        }

        minValue = Integer.MAX_VALUE;
    }
}
