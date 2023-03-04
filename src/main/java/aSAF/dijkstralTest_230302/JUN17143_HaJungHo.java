//package aSAF.dijkstralTest_230302;
//
//import java.io.*;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class JUN17143_HaJungHo {
//    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//    static StringTokenizer st;
//    /*
//    낚시왕
//    - 조건
//        - RxC 격자판, 낚시터는 (1,1)로 시작
//        - 상어: 1칸 차지, 속도 값 존재, 대문자로 상어 구분, 속력값 개별 할당, 방향값 개별 할당
//        - 낚시왕: (1,0)에 위치
//        - 1초 행동
//            - 낚시왕 오른쪽 한 칸 이동
//            - 같은 열에 위치한 제일 가까운 상어 캐치(낚시터에서 상어 제거)
//            - 상어 이동
//                - 정해진 속도(속력, 방향)로 이동
//                - 낚시터 범위를 넘어가는 경우에는 속력 유지, 방향 반대
//                - 상어가 한 칸에 겹치는 경우 가능, 하지만 큰 상어만 남고 나머지 제거됨
//     - 문제 해결
//        1. 상어 배치, 상어가 있는 칸은 상어의 인덱스로 마킹
//        2. 사람 이동 => 반복문
//        3. 상어 캐치 => 반복문
//        4. 상어 이동 => 메서드
//            - 상황 구현
//
//     - 해결 필요한 부분
//        - 왕복으로 움직여야하는 상황 해결 필요
//            - 해당 상황에서 nc 혹은 nr 에 남은 이동값이 저장되는데, 그래서 움직이질 않게됨. => 해결
//     */
//    static int R, C, M; //R x C: 격자판, M: 상어의 수
//    static Shark[][] field;
//    static Shark[] sharks;
//    static int sumOfLength;
//
//    static class Shark implements Comparable<Shark>{
//        int idx, r, c, s, d, z; // 인덱스, 행 | 열 | 속력 | 방향 | 상어 크기
//
//        public Shark(int idx, int r, int c, int s, int d, int z) {
//            this.idx = idx;
//            this.r = r;
//            this.c = c;
//            this.s = s;
//            this.d = d;
//            this.z = z;
//        }
//
//        @Override
//        public int compareTo(Shark o) { // 내림차순 정렬, 큰 놈이 먼저 자리 잡도록
//            return o.z - this.z;
//        }
//    }
//
//    public static void main(String[] args) throws IOException {
//        st = new StringTokenizer(bf.readLine());
//        R = Integer.parseInt(st.nextToken());
//        C = Integer.parseInt(st.nextToken());
//        M = Integer.parseInt(st.nextToken());
//        sumOfLength = 0;
//        field = new Shark[R + 1][C + 1];
//        sharks = new Shark[M+1];
//
//        createShark();
//        catchAndMove();
//        bw.write(sumOfLength + "");
//        bw.flush();
//        bw.close();
//    }
//
//    private static void catchAndMove() {
//        for (int i = 1; i < C + 1; i++) { // 낚시왕 이동
//            //step1. 낚시왕 이동(반복문)
//            for (int j = 1; j < R + 1; j++) { // 상어 탐색(열)
//                //step2. Catch
//                if (field[j][i] != null) {
//                    sumOfLength += field[j][i].z;
//                    field[j][i] = null;
//                    break;
//                }
//            }
//            System.out.println("캐치");
//            for (int j = 0; j < R + 1; j++) {
//                System.out.println(Arrays.toString(field[j]));
//            }
//            System.out.println();
//            //step3. 상어 이동
//            moveShark();
//            System.out.println("무브");
//            for (int j = 0; j < R + 1; j++) {
//                System.out.println(Arrays.toString(field[j]));
//            }
//            System.out.println();
//        }
//    }
//
//    private static void moveShark() {
//        for(Shark sk : sharks){
//            // 본인 위치 빈 자리로 상태 변화
//            if(field[sk.r][sk.c].idx == sk.idx) field[sk.r][sk.c] = null;
//
//            // 각 상어의 방향 구분
//            if(sk.d == 1 || sk.d ==2){ //방향 == 위, 아래
//                //이동거리 최적화
//                int res = sk.s % (R-1); // 같은 방향을 유지하는 cycle 제거 후 남은 거리
//                if(sk.d == 1){ // 위 방향
//                    if(sk.r - 1 > res){
//                        sk.r -= res;
//                    }
//                }
//                else { // 아래 방향
//
//                }
//            }
//            else { // 방향 == 우, 좌
//                int res = sk.s % (C-1);
//            }
//        }
//    }
//
//    private static void createShark() throws IOException {
//        for (int i = 1; i < M+1; i++) {
//            st = new StringTokenizer(bf.readLine());
//            int r = Integer.parseInt(st.nextToken()); //행
//            int c = Integer.parseInt(st.nextToken()); //열
//            int s = Integer.parseInt(st.nextToken()); //속력
//            int d = Integer.parseInt(st.nextToken()); //이동 방향, 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
//            int z = Integer.parseInt(st.nextToken()); //크기
//            sharks[i] = new Shark(i, r, c, s, d, z); //sharks 배열의 인덱스인 i는 무시할 것
//            field[r][c] = sharks[i];  // 인덱스1부터 시작
//        }
//        Arrays.sort(sharks); // 내림차순
//    }
//}
//