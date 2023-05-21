package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class JUN2931_가스관 {
    /*
    - 파이프 설치
    - R * C map
    - 7가지 블록
        - + 블럭의 경우는 수직으로 먼저, 그 후 수평으로 흐름
        - 지나갔다는 체크가 필요해보임, 혹은 방향을 지닌채로 이동
    - M => Z 방향으로 진행

    - 해커가 지운 칸의 좌표와 지워진 블록을 출력

     */
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static class Pair {
        int x;
        int y;
        int d;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Pair(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int R, C; //size
    static char[][] map;
    static int[] dx = {-1,0,1,0}; //북, 동, 남, 서
    static int[] dy = {0,1,0,-1};
    static List<Pair> crossList;
    static boolean[][] visited;
    //블럭 저장
    static char[] blocks = {'|', '-', '+', '1','2','3','4'};
    static Pair M;
    public static void main(String[] args) throws IOException {
        crossList = new ArrayList<>();
        //R, C 입력
        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        //map 초기화 및 입력
        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            char[] temp = bf.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                //M과 Z 좌표를 저장
                if(temp[j] == 'M') {
                    M = new Pair(i,j);
                }
                //+ 파이프 위치 저장 및 세로 파이프로 바꿔두기
                if(temp[j] == '+'){
                    crossList.add(new Pair(i,j));
                    temp[j] = '|';
                }
                map[i][j] = temp[j];
            }
        }
        // 첫 진행 방향 정하기
        for (int i = 0; i < 4; i++) {
            int nx = M.x + dx[i];
            int ny = M.y + dy[i];
            if(nx<0 || ny<0 || nx>=R || ny>=C || map[nx][ny] == '.')continue;
            if(i == 0 && (map[nx][ny] == '-' || map[nx][ny] == '2' || map[nx][ny] == '3')) continue;
            else if(i == 1 && (map[nx][ny] == '|' || map[nx][ny] == '1' || map[nx][ny] == '2')) continue;
            else if(i == 2 && (map[nx][ny] == '-' || map[nx][ny] == '1' || map[nx][ny] == '4')) continue;
            else if(i == 3 && (map[nx][ny] == '|' || map[nx][ny] == '3' || map[nx][ny] == '4')) continue;
            M = new Pair(M.x, M.y, i);
            break;
        }

        // M 좌표에서 bfs 시작
        visited = new boolean[R][C];
        bfs();
        bw.flush();
        bw.close();
    }

    private static void bfs() throws IOException {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.offer(M);

        while(!queue.isEmpty()){
            Pair current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            int cd = current.d;

            // '|'인 블록이 '+'인지 확인하고 진행
            if (map[cx][cy] == '|') {
                for (int j = 0; j < crossList.size(); j++) {
                    if (cx == crossList.get(j).x && cy == crossList.get(j).y) { //크로스인 경우 자리 이동 후 가로로 전환
                        map[cx][cy] = '-';
                    }
                }
            }

            int nx = cx + dx[cd];
            int ny = cy + dy[cd];
            int nd = cd;

            switch (map[nx][ny]) {
                case '1':
                    nd = cd == 3 ? 2 : 1;
                    break;
                case '2':
                    nd = cd == 2 ? 1 : 0;
                    break;
                case '3':
                    nd = cd == 2 ? 3 : 0;
                    break;
                case '4':
                    nd = cd == 1 ? 2 : 3;
                    break;
                case '.':
                    bw.write((nx+1)+" "+(ny+1)+" ");
                    boolean[] check = new boolean[4];
                    for (int i = 0; i < 4; i++) {
                        int nnx = nx + dx[i];
                        int nny = ny + dy[i];
                        if(nnx == nx && nny == ny) continue;
                        if(nnx<0 || nny<0 || nnx>=R || nny>=C || map[nnx][nny] == '.')continue;
                        if(i == 0 && (map[nnx][nny] == '-' || map[nnx][nny] == '2' || map[nnx][nny] == '3')) continue;
                        else if(i == 1 && (map[nnx][nny] == '|' || map[nnx][nny] == '1' || map[nnx][nny] == '2')) continue;
                        else if(i == 2 && (map[nnx][nny] == '-' || map[nnx][nny] == '1' || map[nnx][nny] == '4')) continue;
                        else if(i == 3 && (map[nnx][nny] == '|' || map[nnx][nny] == '3' || map[nnx][nny] == '4')) continue;
                        check[i] = true;
                    }
                    if(check[0] && check[1] && check[2] && check[3]){
                        bw.write("+");
                    }
                    else if(check[0] && !check[1] && check[2] && !check[3]){
                        bw.write("|");
                    }
                    else if(!check[0] && check[1] && !check[2] && check[3]){
                        bw.write("-");
                    }
                    else if(!check[0] && check[1] && check[2] && !check[3]){
                        bw.write("1");
                    }
                    else if(check[0] && check[1] && !check[2] && !check[3]){
                        bw.write("2");
                    }
                    else if(check[0] && !check[1] && !check[2] && check[3]){
                        bw.write("3");
                    }
                    else if(!check[0] && !check[1] && check[2] && check[3]){
                        bw.write("4");
                    }
                    return;
            }

            queue.offer(new Pair(nx,ny,nd));
        }
    }
}
