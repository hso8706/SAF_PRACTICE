package aSAF.dijkstralTest_230302;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN17413_HaJungHo {
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
        for (int i = 0; i < R+1; i++) {
            System.out.println(Arrays.toString(field[i]));
        }
        System.out.println();
        catchAndMove();

//        for (int i = 0; i < M; i++) {
//            System.out.println(sharks[i].z);
//        }
        bw.write(sumOfLength+"");
        bw.flush();
        bw.close();
    }

    private static void catchAndMove() {
        for (int i = 1; i < C + 1; i++) { // 낚시왕 이동
            //step1. 낚시왕 이동(반복문)
            for (int j = 1; j < R + 1; j++) { // 상어 탐색(열)
                //step2. Catch
                if (field[j][i] != 0) {
                    Shark caught = findShark(field[j][i]); // 잡힌 상어, 상어 인덱스 0부터 시작
                    field[j][i] = 0;
                    sumOfLength += caught.z;
                    break;
                }
            }
            //step3. 상어 이동

            moveShark();
            for (int n = 0; n < R+1; n++) {
                System.out.println(Arrays.toString(field[n]));
            }
        }
    }

    private static Shark findShark(int idx) {
        for (Shark sk : sharks){
            if (sk.idx == idx)
                return sk;
        }
        return null;
    }

    private static void moveShark() {
        for (Shark sk : sharks) { // sharks 전체 순회
            if (field[sk.r][sk.c] == 0) continue; // 낚시터에 없는 상어 패스
            field[sk.r][sk.c] = 0;



            int nr = sk.r, nc= sk.c; // 새로 지정될 상어 좌표(세로, 가로), 임의
            while(1<=nr && nr<=R && 1<=nc && nc<=C){
                switch (sk.d) {
                    case 1: // 위로
                        nr = sk.r - sk.s; // 상어 현재 열에서 속력만큼 위로 이동
                        if (nr < 1) { // 낚시터를 벗어난 경우
                            //1. 방향 전환
                            //2. 넘어가고 남은 칸 이동수만큼 전환된 방향으로 이동
                            sk.d = 2;
                            nr = 1 + (sk.s - (sk.r - 1)); // 1에서 시작 + (이동하고 남은 이동수)
                            sk.r = nr;
                        }
                        break;
                    case 2: // 아래로
                        nr = sk.r + sk.s; // 상어 현재 열에서 속력만큼 아래로 이동
                        if (nr > R) { // 낚시터를 벗어난 경우
                            //1. 방향 전환
                            //2. 넘어가고 남은 칸 이동수만큼 전환된 방향으로 이동
                            sk.d = 1;
                            nr = R - (sk.s - (R - sk.r)); // R에서 시작 - (이동하고 남은 이동수)
                            sk.r = nr;
                        }
                        break;
                    case 3: // 우로
                        nc = sk.c + sk.s; // 상어 현재 열에서 속력만큼 우로 이동
                        if (nc > C) { // 낚시터를 벗어난 경우
                            //1. 방향 전환
                            //2. 넘어가고 남은 칸 이동수만큼 전환된 방향으로 이동
                            sk.d = 4;
                            nc = R - (sk.s - (C - sk.c)); // R에서 시작 - (이동하고 남은 이동수)
                            sk.c = nc;
                        }
                        break;
                    case 4: // 좌로
                        nc = sk.c - sk.s; // 상어 현재 열에서 속력만큼 좌로 이동
                        if (nc < 1) { // 낚시터를 벗어난 경우
                            //1. 방향 전환
                            //2. 넘어가고 남은 칸 이동수만큼 전환된 방향으로 이동
                            sk.d = 3;
                            nc = 1 + (sk.s - (sk.c - 1)); // 1에서 시작 + (이동하고 남은 이동수)
                            sk.c = nc;
                        }
                        break;
                }
            }
            field[sk.r][sk.c] = sk.idx;
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
            field[r][c] = i + 1; // 낚시터 칸을 상어 인덱스로 마킹, 마킹은 1부터 인덱스는 0부터 시작
            sharks[i] = new Shark(i+1, r, c, s, d, z);
        }
        Arrays.sort(sharks); // 상어 크기 순 오름차순 => 겹쳤을 경우, 알아어 먹어짐
    }
}
    