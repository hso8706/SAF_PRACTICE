package aSAF.dijkstralTest_230302;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN17143_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    낚시왕
    - 조건
        - RxC 격자판, 낚시터는 (1,1)로 시작
        - 상어: 1칸 차지, 속도 값 존재, 대문자로 상어 구분, 속력값 개별 할당, 방향값 개별 할당
        - 낚시왕: (1,0)에 위치
        - 1초 행동
            - 낚시왕 오른쪽 한 칸 이동
            - 같은 열에 위치한 제일 가까운 상어 캐치(낚시터에서 상어 제거)
            - 상어 이동
                - 정해진 속도(속력, 방향)로 이동
                - 낚시터 범위를 넘어가는 경우에는 속력 유지, 방향 반대
                - 상어가 한 칸에 겹치는 경우 가능, 하지만 큰 상어만 남고 나머지 제거됨
     - 문제 해결
        1. 상어 배치, 상어가 있는 칸은 상어의 인덱스로 마킹
        2. 사람 이동 => 반복문
        3. 상어 캐치 => 반복문
        4. 상어 이동 => 메서드
            - 상황 구현

     - 해결 필요한 부분
        - 왕복으로 움직여야하는 상황 해결 필요
            - 해당 상황에서 nc 혹은 nr 에 남은 이동값이 저장되는데, 그래서 움직이질 않게됨.
     */
    static int R, C, M; //R x C: 격자판, M: 상어의 수
    static int[][] field;
    static Shark[] sharks;
    static int sumOfLength;

    static class Shark implements Comparable<Shark> {
        int idx, r, c, s, d, z; // 인덱스, 행 | 열 | 속력 | 방향 | 상어 크기

        public Shark(int idx, int r, int c, int s, int d, int z) {
            this.idx = idx;
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }

        @Override
        public int compareTo(Shark o) {
            return this.z - o.z; // 상어 크기 순 오름차순 => 겹쳤을 경우, 알아서 먹힘
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sumOfLength = 0;
        field = new int[R + 1][C + 1];
        sharks = new Shark[M];

        createShark();
        catchAndMove();
//        for (int i = 0; i < M; i++) {
//            System.out.println(sharks[i].idx);
//        }
        bw.write(sumOfLength + "");
        bw.flush();
        bw.close();
    }

    private static void catchAndMove() {
        for (int i = 1; i < C + 1; i++) { // 낚시왕 이동
            //step1. 낚시왕 이동(반복문)
            for (int j = 1; j < R + 1; j++) { // 상어 탐색(열)
                //step2. Catch
                if (field[j][i] != 0) {
                    Shark caught = findShark(field[j][i]); // 잡힌 상어, 상어 인덱스 1부터 시작
                    field[j][i] = 0;
                    caught.idx = 0;
                    sumOfLength += caught.z;
                    break;
                }
            }
//            System.out.println("캐치");
//            for (int j = 0; j < R + 1; j++) {
//                System.out.println(Arrays.toString(field[j]));
//            }
//            System.out.println();
            //step3. 상어 이동
            moveShark();
//            System.out.println("무브");
//            for (int j = 0; j < R + 1; j++) {
//                System.out.println(Arrays.toString(field[j]));
//            }
//            System.out.println();
        }
    }

    private static Shark findShark(int idx) {
        for (Shark sk : sharks) {
            if (sk.idx == idx)
                return sk; // 얕은 복사
        }
        return null;
    }
    private static void deleteShark(int idx) {
        for (Shark sk : sharks) {
            if (sk.idx == idx){
                sk.idx =0;
            }
        }
    }

    private static void moveShark() {
        int[][] temp = new int[R + 1][C + 1];


        for (Shark sk : sharks) { // sharks 전체 순회
            if (sk.idx == 0) continue; // 낚시터에 없는 상어 패스
            for (int i = 0; i < R + 1; i++) {
                System.arraycopy(field[i], 0, temp[i], 0, temp[i].length);
            }
            if (temp[sk.r][sk.c] == sk.idx) temp[sk.r][sk.c] = 0; // 해당 상어 현재 공간 빈자리 처리

            //남은 움직임 거리를 기준으로 반복문 해결하는 방법 찾기
            int res = sk.s;
            while (res != 0) {
                switch (sk.d) {
                    case 1: // 위로
                        if (sk.r - 1 < res) { // 남은 이동수가 칸을 넘어가는 경우
                            res -= sk.r - 1; // 남은 이동수 갱신, 방향 전환 후 재이동
                            sk.r = 1; // 끝으로 이동
                            sk.d = 2; // 방향 전환
                        } else { // 남은 이동수가 칸을 넘지 않는 경우
                            sk.r -= res;
                            res = 0;
                        }
                        break;
                    case 2: // 아래로
                        if (R - sk.r < res) { // 남은 이동수가 칸을 넘어가는 경우
                            res -= R - sk.r; // 남은 이동수 갱신, 방향 전환 후 재이동
                            sk.r = R; // 끝으로 이동
                            sk.d = 1; // 방향 전환
                        } else { // 남은 이동수가 칸을 넘지 않는 경우
                            sk.r += res;
                            res = 0;
                        }
                        break;
                    case 3: // 우로
                        if (C - sk.c < res) { // 남은 이동수가 칸을 넘어가는 경우
                            res -= C - sk.c; // 남은 이동수 갱신, 방향 전환 후 재이동
                            sk.c = C; // 끝으로 이동
                            sk.d = 4; // 방향 전환
                        } else { // 남은 이동수가 칸을 넘지 않는 경우
                            sk.c += res;
                            res = 0;
                        }
                        break;
                    case 4: // 좌로
                        if (sk.c - 1 < res) { // 남은 이동수가 칸을 넘어가는 경우
                            res -= sk.c - 1; // 남은 이동수 갱신, 방향 전환 후 재이동
                            sk.c = 1; // 끝으로 이동
                            sk.d = 3; // 방향 전환
                        } else { // 남은 이동수가 칸을 넘지 않는 경우
                            sk.c -= res;
                            res = 0;
                        }
                        break;
                }
            }
            // 상어가 다 움직인 후 도착 상태
            temp[sk.r][sk.c] = sk.idx;
            if(field[sk.r][sk.c] != 0 && (findShark(field[sk.r][sk.c]).z > findShark(temp[sk.r][sk.c]).z)) continue;
            if(field[sk.r][sk.c] != 0 && (findShark(field[sk.r][sk.c]).z < findShark(temp[sk.r][sk.c]).z)) {
                deleteShark(field[sk.r][sk.c]);
            }
            for (int i = 0; i < R + 1; i++) {
                System.arraycopy(temp[i], 0, field[i], 0, field[i].length);
            }
        }
    }

    private static void createShark() throws IOException {
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken()); //행
            int c = Integer.parseInt(st.nextToken()); //열
            int s = Integer.parseInt(st.nextToken()); //속력
            int d = Integer.parseInt(st.nextToken()); //이동 방향, 1: 위, 2: 아래, 3: 오른쪽, 4: 왼쪽
            int z = Integer.parseInt(st.nextToken()); //크기
            field[r][c] = i + 1; // 낚시터 칸을 상어 인덱스로 마킹, 마킹은 1부터 시작
            sharks[i] = new Shark(i + 1, r, c, s, d, z); //sharks 배열의 인덱스인 i는 무시할 것
        }
        Arrays.sort(sharks); // 상어 크기 순 오름차순 => 겹쳤을 경우, 알아어 먹어짐
    }
}
    