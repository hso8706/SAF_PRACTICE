    package aSAF.solution_230303;
    
    import java.io.*;
    import java.util.*;
    
    public class JUN17144_HaJungHo {
        static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringTokenizer st;
    
        /*
        ### 미세먼지 안녕!
        - RxC 집 격자판
        - 미세먼지
            - 위치: (r,c), A 만큼 존재
            - 사방 확산
            - 공기청정기가 있거나, 칸이 없으면 확산 안함
            - 확산의 양: A/5 한 만큼, 소수점 버림(int)
            - 남은 양: A - (A/5 * 확산된 방향)
            - 확산으로 겹치는 부분 주의
        - 공기청정기
            - 1열 설치, 2행 차지(세로)
            - 공기청정기 위치에는 미세먼지가 없음
            - 공기청정기 윗 부분은 반시계방향 바람 순환, 아랫 부분은 시계 방향 바람 순환(즉, 중간을 가로지는 바람이 나가고 끝에서 양끝으로 갈라지는 바람)
            - 바람의 영향 : 미세먼지가 바람의 방향대로 1칸 이동
            - 공기청정기로 들어온 미세먼지는 제거(정화)
            - 바람이 안닿는 곳은 그대로 유지된다.
        - T초 후 미세먼지 양 구하기
    
        ### 문제 해결
        1. 미세먼지 확산 파트
            - bfs, 입력받을 때부터 큐에 넣기
        2. 공기청정기 바람 파트

        ### 놓쳤던 부분들
        - 동시성
            - 확산이 동시에 이루어지는 것처럼 구현해야하는 것을 놓쳤음.
            - 만약 미세먼지가 인접해있으면, 인접한 미세먼지가 모두 확산된 후 값이 변해야한다
            => 해결: 가짜 맵 하나 더 구현
         */
    
        static int R, C, T; // R: 세로, C: 가로, T: 시간(사이클 횟수)
        static int[][] house;
        static int[][] temp; // 동시에 확산되는 환경을 구현하기 위한 배열
    //    static boolean[][] visited; // 방문 여부는 필요없을듯하다. 방문한 곳에 또 확산가능
        static Queue<int[]> queue = new ArrayDeque<>(); // 세로 좌표, 가로 좌표, 미세먼지 양, 시간
        static int[] dr = new int[]{1,0,-1,0};
        static int[] dc = new int[]{0,1,0,-1};
        static ArrayList<int[]> cleaner = new ArrayList<>(); // 공기청정기 좌표 저장
    
        public static void main(String[] args) throws IOException {
            st = new StringTokenizer(bf.readLine());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            house = new int[R+1][C+1];
            temp = new int[R+1][C+1];
    
            for (int i = 1; i < R+1; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 1; j < C+1; j++) {
                    house[i][j] = Integer.parseInt(st.nextToken());
                    if ( house[i][j] == -1){ // 공기청정기 좌표 저장
                        cleaner.add(new int[]{i, j});
                    }
                    else if (house[i][j] != 0){ // 미세먼지 좌표 저장
                        queue.offer(new int[]{i, j, house[i][j]}); // 미세먼지 좌표, 미세먼지 양
                    }
                }
            }
            //확산 1회 + 바람 이동 1회가 한 세트
            for (int t = 0; t < T; t++) {
                diffusion();
                airFlow();
                for (int i = 0; i < R + 1; i++) {
                    for (int j = 0; j < C + 1; j++) {
                        queue.offer(new int[]{i, j, house[i][j]});
                    }
                }
            }
            bw.write(howMuch()+"");
            bw.flush();
            bw.close();
        }
    
        private static int howMuch() {
            int amount = 0;
            for (int i = 1; i < R+1; i++) {
                for (int j = 1; j < C+1; j++) {
                    if(house[i][j] != -1 && house[i][j] != 0) amount += house[i][j];
                }
            }
            return amount;
        }
    
        private static void diffusion() {
            while(!queue.isEmpty()){
                int[] current = queue.poll();
                int cr = current[0];
                int cc = current[1];
                int dusts = current[2];
    
                for (int i = 0; i < 4; i++) {
                    int nr = cr + dr[i];
                    int nc = cc + dc[i];
                    if(nr<1 || nc<1 || nr>R || nc>C || house[nr][nc] == -1) continue;
                    if(current[2]>=5){//확산이 가능한 최소 미세먼지양
                        int separated = (int) current[2] / 5;
                        temp[nr][nc] += separated; // 중첩 미세먼지
                        dusts -= separated;
                    }
                }
                temp[cr][cc] += dusts; // 확산되고 남은 미세먼지
            }
            for (int i = 0; i < R+1; i++) {
                for (int j = 0; j < C+1; j++) {
                    if (house[i][j] == -1) continue;
                    house[i][j] = temp[i][j];
                    temp[i][j] = 0;
                }
            }
        }
    
        private static void airFlow() {
            int[] fr = new int[]{-1, 0, 1, 0}; // 상, 우, 하, 좌
            int[] fc = new int[]{0, 1, 0, -1};
            int direction = 1;
    
            Queue<int[]> upperAir = new ArrayDeque<>();
            Queue<int[]> lowerAir = new ArrayDeque<>();
    
            //윗 공기 순환 시작점
            int ur = cleaner.get(0)[0];
            int uc = cleaner.get(0)[1]+1;
            upperAir.offer(new int[]{ur,uc,house[ur][uc]}); // 시작 지점 좌표 + 미세먼지양
            house[ur][uc] = 0;
            //아랫 공기 순환 시작점
            int lr = cleaner.get(1)[0];
            int lc = cleaner.get(1)[1]+1;
            lowerAir.offer(new int[]{lr,lc,house[lr][lc]}); // 시작 지점 좌표 + 미세먼지양
            house[lr][lc] = 0;
            //윗 공기 순환 로직
            while(!upperAir.isEmpty()){
                int[] current = upperAir.poll();
                int cr = current[0];
                int cc = current[1];
                int dusts = current[2];
    
                int nr = cr + fr[direction];
                int nc = cc + fc[direction];
                if(nc > C) {
                    direction = 0; // 우측을 넘은 경우
                }
                else if(nr < 1) {
                    direction = 3; // 위쪽을 넘은 경우
                }
                else if(nc < 1) {
                    direction = 2; // 왼쪽을 넘은 경우
                }
                else if(nr > R) {
                    direction = 1; // 아래쪽을 넘은 경우
                }
                nr = cr + fr[direction];
                nc = cc + fc[direction];
                if(house[nr][nc] != -1){
                    upperAir.offer(new int[]{nr,nc,house[nr][nc]});
                    house[nr][nc] = dusts;
                }
            }
            direction = 1;
            // 아랫 공기 순환 로직
            while(!lowerAir.isEmpty()){
                int[] current = lowerAir.poll();
                int cr = current[0];
                int cc = current[1];
                int dusts = current[2];
    
                int nr = cr + fr[direction];
                int nc = cc + fc[direction];
                if(nc > C) {
                    direction = 2; // 우측을 넘은 경우
                }
                else if(nr < 1) {
                    direction = 1; // 위쪽을 넘은 경우
                }
                else if(nc < 1) {
                    direction = 0; // 왼쪽을 넘은 경우
                }
                else if(nr > R) {
                    direction = 3; // 아래쪽을 넘은 경우
                }
                nr = cr + fr[direction];
                nc = cc + fc[direction];
                if(house[nr][nc] != -1){
                    lowerAir.offer(new int[]{nr,nc,house[nr][nc]});
                    house[nr][nc] = dusts;
                }
            }
    //        System.out.println("공기청정기");
    //        for (int i = 0; i < R+1; i++) {
    //            System.out.println(Arrays.toString(house[i]));
    //        }
    //        System.out.println();
        }
    
    }
