package Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN17143 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    # 낚시꾼 이동
    - 반복문 사용, 1씩 증가하여 열의 이동을 표현
    - 해당 열을 모두 탐색하고, 상어가 있으면 해당 상어가 잡음
    - 상어를 잡은 후 상어 이동 진행

    # 상어 이동
    - 상어 배열(sharks)을 순회. 순회 시, caught, eaten 을 확인하며 멀쩡한 상어만 조회
    - 상어가 작은 순으로 먼저 움직임으로써, 큰 상어가 자연스레 잡아먹는 상황으로 로직 구현
    - 벽을 넘어가는 이동
        - 가로
            - 최대 이동 거리: C-1
            - (속도)/(C-1) 값을 기준으로 판단
                - 몫: 홀수 -> 방향 전환, 나머지-1 만큼 이동(방향전환에 소모)
                - 몫: 짝수 -> 방향 유지, 나머지만큼 이동
                - 이동거리: shark.r*dr[d], shark.c*dc[d]
        - 세로
            - 최대 이동 거리: R-1
            - (속도)/(R-1) 값을 기준으로 판단
                - 몫: 홀수 -> 방향 전환, 나머지-1 만큼 이동(방향전환에 소모)
                - 몫: 짝수 -> 방향 유지, 나머지만큼 이동

    # 상어 클래스
    - 상어 크기를 오름차순으로 정렬하게 Comparable 구현
    - caught: 상어가 잡혔는지 안잡혔는지 나타내는 값
    - eaten: 상어가 먹혔는지 나타내는 값
    - 초기화 후 정렬
    - 정렬 후 map 에 상어 번호(index) 적용
     */
    static class Shark implements Comparable<Shark>{
        int r;
        int c;
        int s;
        int d;
        int z;
        boolean caught;
        boolean eaten;

        public Shark(){};

        public Shark(int r, int c, int s, int d, int z, boolean caught, boolean eaten) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
            this.caught = caught;
            this.eaten = eaten;
        }

        @Override
        public int compareTo(Shark o){
            return this.z - o.z;
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }
    static int R,C,M; //격자판 크기, 상어 수
    static int[][] map; //R+1, C+1
    static int[][] tempMap; //임시 맵
    static Shark[] sharks; //M+1
    static int[] dr = new int[]{0, -1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        //초기화
        init();
        //낚시꾼 이동
        catcherMove();
        //잡은 상어 무게 측정
        int answer = measuringWeight();
        System.out.println(answer);
    }

    private static int measuringWeight() {
        int totalWeight = 0;
        for(Shark s : sharks){
            if (s.caught) totalWeight += s.z;
        }
        return totalWeight;
    }

    private static void catcherMove() {
//        printMap();
//        System.out.println("------init-----");

        for (int c = 1; c <= C; c++) {
            //상어 낚시
            lookAndCatch(c);
//            System.out.print("catch: ");
//            printCaught();
//            printMap();
            //상어 이동
//            System.out.println("--------move-------");
            sharksMove();
//            printMap();
//            System.out.println("----------temp--------");
//            printTempMap();
        }
    }

    private static void printTempMap() {
        for (int i = 1; i < R+1; i++) {
            System.out.println(Arrays.toString(tempMap[i]));
        }
    }

    private static void printCaught() {
        for(int i=1; i<M+1; i++){
            if(sharks[i].caught) System.out.print(i +" ");
        }
        System.out.println();
    }

    private static void printMap() {
        for (int i = 1; i < R+1; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
    }

    private static void sharksMove() {
        //가상 맵에서 움직인 번호를 마킹 후, 현실 맵에 적용
//        cloneMap();
        tempMap = new int[R+1][C+1];
        int rowCycle = (C-1)*2;
        int columnCycle = (R-1)*2;
        for (int i = 1; i < M+1; i++) {
            if(sharks[i].caught || sharks[i].eaten) continue;
            int rest = 0;
            if(isRowDirection(sharks[i])){//가로 방향
                rest = sharks[i].s%rowCycle;
                int r = sharks[i].r;
                int c = sharks[i].c;
                int d = sharks[i].d;
                for (int j = 0; j < rest; j++) {
                    int nc = c + dc[d];
                    if(nc < 1){
                        d = (d == 3)? 4:3;
                        nc = 2;
                    }
                    else if(nc > C){
                        d = (d == 3)? 4:3;
                        nc = C-1;
                    }
                    c = nc;
                }
                sharks[i].d = d;
                sharks[i].c = c;
            }
            else{
                rest = sharks[i].s%columnCycle;
                int r = sharks[i].r;
                int c = sharks[i].c;
                int d = sharks[i].d;
                for (int j = 0; j < rest; j++) {
                    int nr = r + dr[d];
                    if(nr < 1){
                        d = (d == 1)? 2:1;
                        nr = 2;
                    }
                    else if(nr > R){
                        d = (d == 1)? 2:1;
                        nr = R-1;
                    }
                    r = nr;
                }
                sharks[i].d = d;
                sharks[i].r = r;
            }
            //이미 존재하는 상어가 있다면, 먹힘 처리
            if(tempMap[sharks[i].r][sharks[i].c] != 0){
                int sharkNum = tempMap[sharks[i].r][sharks[i].c];
                sharks[sharkNum].eaten = true;
            }
            //새로운 상어로 덮어 씌우기
            tempMap[sharks[i].r][sharks[i].c] = i;
        }
        applyRealMap();
    }

    private static void applyRealMap() {
        for (int i = 1; i < R+1; i++) {
            for (int j = 1; j < C+1; j++) {
                map[i][j] = tempMap[i][j];
            }
        }
    }

    private static boolean isRowDirection(Shark s) {
        return s.d == 3 || s.d == 4;
    }

    private static void lookAndCatch(int c) {
        for (int r = 1; r <= R; r++) {
            if(map[r][c] != 0) {
                int sharkNum = map[r][c];
                sharks[sharkNum].caught = true;
                return;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[R+1][C+1];
        sharks = new Shark[M+1];
        sharks[0] = new Shark();
        for (int i = 1; i < M+1; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks[i] = new Shark(r,c,s,d,z,false,false);
        }
        //상어 크기별 오름차순 정렬
        Arrays.sort(sharks);
        //현실 맵에 번호 마킹
        for (int i = 1; i < M+1; i++) {
            int r = sharks[i].r;
            int c = sharks[i].c;
            map[r][c] = i;
        }
    }

}
