package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1347 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    /*
    - 완탐을 통해 시작 위치 파악
        - 첫 R,L,F 로 파악하기엔 무리가 있어보임(가지수)
    - 모든 시작점에서 order 를 따른 후 결과 중 '.'과 '#'만 출력
        = 50*50*50
    - 홍준이 클래스 생성
        - 좌표, 방향

    # 입력
    - 문자열 길이(0<N<50) => Map[50][50]
    - 문자열
     */
    static class Person {
        int x;
        int y;
        int d;

        public Person(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    static int N;
    static char[] order;
    static int[] dx = new int[]{-1,0,1,0};
    static int[] dy = new int[]{0,1,0,-1};
    public static void main(String[] args) throws IOException {

        init();
        findStartingPoint();
    }

    private static void findStartingPoint() throws IOException {
        out: for (int x = 0; x < 50; x++) {
            for (int y = 0; y < 50; y++) {
                boolean flag = carryOutOrder(x,y,2, new char[50][50]);
                if(flag) break out;
            }
        }
        bw.flush();
        bw.close();
    }

    private static boolean carryOutOrder(int x, int y, int d, char[][] map) throws IOException {
        Person hong = new Person(x,y,d);
        boolean flag = true;
        int minX = x, minY = y;
        int maxX = x, maxY = y;
        map[x][y] = '.';

        for(char o : order){
            if(o == 'L') {
                d -= 1;
                if(d == -1) d = 3;
            }
            else if(o == 'R'){
                d += 1;
                if(d == 4) d = 0;
            }
            else {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if(nx<0|| ny<0|| nx>=50|| ny>=50) {
                    flag = false;
                    break;
                }
                x = nx;
                y = ny;
                map[x][y] = '.';
                minX = Math.min(minX, nx);
                minY = Math.min(minY, ny);
                maxX = Math.max(maxX, nx);
                maxY = Math.max(maxY, ny);
            }
        }
        if(flag) {
            for (int i = minX; i <= maxX; i++) {
                for (int j = minY; j <= maxY; j++) {
                    if (map[i][j] == '.')
                        bw.write(map[i][j]);
                    else {
                        bw.write('#');
                    }
                }
                bw.write("\n");
            }
        }
        return flag;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        order = new char[N];
        order = bf.readLine().toCharArray();
    }
}
