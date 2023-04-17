package forA;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW2383_점심식사시간 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 점심 식사시간
    - NxN 방(4~10)
    - P: 사람(1~10), S: 계단 입구(2개)
    - 고려해야할 시간: 계단 입구까지 가는 시간 + 계단 입구 도착 후 계단을 내려가는 시간
        - 계단 입구로 이동하는 시간: 맨해튼 거리
        - 계단 입구에서 내려가는 시간
            - 분 당 한 칸
            - 계단은 한 번에 3명까지 내려갈 수 있음
            - 여러 명(3명 이하)이 계단을 동시에 내려갈 수 있음
            - 계단 길이(K)로 제공 => 계단 모두 내려가는데 K 분 걸림
    - 목표 : 모든 사람들이 계단을 전부 내려가 이동을 완료하는 최소 시간

    - 고려
        - 1. 어떤 사람부터 이동해야하는가
        - 2. 어느 계단으로 어떤 사람들(그룹)이 이동해야하는가
    ### 해결1.
    1. 사람 이동 배열을 만들어서 저장하고 풀기
        - 사람별로 2가지 경우의 수가 존재 => 총 20개
        - true, false 부분 집합으로 내려갈 계단 그룹 구하기
        - 목표 지점 정한 후 bfs 시작 => goStairs() : 계단 입구까지 가는 최소 거리
            - 최소 거리 값들을 배열에 저장
        - 시간에 따라 계단을 내려가는 로직 구현
            - 시간 배열(minutes)이 채워질 때마다 로직 실행
            - 전부 내려가는 시간의 최소값을 저장 및 갱신하는 변수 선언

    ### 해결2. 수업
    - 
     */
    static int T, N; // T: tc, N: map size
    static int[][] map; // 0: 빈 공간, 1: 사람, 2~: 계단 입구 및 계단 길이
    static boolean[][] visited; // bfs visited
    static int peopleCnt; // 사람 수 카운트
    static Pair[] people; // 사람 좌표 저장 배열, 사람 : 1 ~ 10
    static Pair stairA; // 계단A 좌표
    static Pair stairB; // 계단B 좌표
    static boolean[] target; // subset, 목표 계단, 사람 수 만큼 길이, false == 1번 계딴, true : 2번 계단
    //    static int[] minutes; // 사람별로 계단 입구까지 도달하는데 걸린 최소 시간을 저장하는 배열
    static Move[] minutes;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static int minTime;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            bw.write("#" + tc + " ");
            N = Integer.parseInt(bf.readLine());
            map = new int[N][N];
            people = new Pair[11];
            fillTheMap();
            target = new boolean[peopleCnt + 1];
            minTime = Integer.MAX_VALUE;

            whichStairs(1);
        }
    }

    private static void whichStairs(int cnt) {
        if (cnt == peopleCnt + 1) {
            if (!isPassed()) {
                /*
                 target idx : 사람 번호
                 target val : 목표 지점, false: 1번 계단, true: 2번 계단
                 minutes : 하나의 부분 집합이 완성됐을때 초기화, 각 사람 bfs 로직마다 값 채우기
                 => minutes 가 채워지면 계단 내려가는 로직 실행
                 */
                minutes = new Move[11];
                for (int i = 1; i < peopleCnt; i++) {
                    visited = new boolean[N][N];
                    visited[people[i].x][people[i].y] = true;
                    Move temp = new Move(people[i].x, people[i].y, 0, i, 0);
                    goStairs_bfs(temp);
                }
                downStairs();
            }
            return;
        }

        target[cnt] = false;
        whichStairs(cnt + 1);
        target[cnt] = true;
        whichStairs(cnt + 1);
    }

    private static void downStairs() {
        /*
        - 시간의 흐름
        - 흐르는 시간과 m이 같은 순간 계단 입구에 등장 => queue 에 넣기
        - queue 의 size 가 3이 될 때까지는 계속 queue 에 넣기
        - queue 의 size 가 3이 되는 순간부터는 넣지 않기, 계단 입구에 등장한 사람의 m(시간) 증가
        - queue 의 size 가 3일 때에는 queue 를 순회하며 꺼내고 k를 1씩 줄여가기
        - 각 사람의 k가 0인 순간에는 queue 에서 배출되도록 구현
         */
        Queue<Move> queueA = new ArrayDeque<>();
        Queue<Move> queueB = new ArrayDeque<>();

        int time = 0;
        int pollCnt = 0;
        while (pollCnt < 10) {
            time++;
            for (int i = 1; i < peopleCnt; i++) {
                if (map[stairA.x][stairA.y] == minutes[i].k) { // stairA가 타겟
                    if (time == minutes[i].m) {
                        if (queueA.size() < 3) queueA.offer(minutes[i]);
                        else {
                            for (int j = 0; j < 3; j++) {
                                Move cPerson = queueA.poll();
                                cPerson.k--;
                                if(cPerson.k != 0) queueA.offer(cPerson);
                                else pollCnt++;
                                // k==0이 되면 out
                            }
                            minutes[i].m++; // 입구에서 대기하면서 시간 보내는 과정
                        }
                    }

                }
                else { // stairB가 타겟
                    if (time == minutes[i].m) {
                        if (queueB.size() < 3) queueB.offer(minutes[i]);
                        else {
                            for (int j = 0; j < 3; j++) {
                                Move cPerson = queueB.poll();
                                cPerson.k--;
                                if(cPerson.k != 0) queueB.offer(cPerson);
                                else pollCnt++;
                                // k==0이 되면 out
                            }
                            minutes[i].m++; // 입구에서 대기하면서 시간 보내는 과정
                        }
                    }
                }
            }
            System.out.println(time +":"+ queueA.size());
            System.out.println(time +":"+ queueB.size());
            System.out.println();
        }
    }

    // 공집합 혹은 모든 집합인 경우 true 반환
    private static boolean isPassed() {
        int trueCnt = 0;
        int falseCnt = 0;

        for (int i = 1; i < peopleCnt + 1; i++) {
            if (target[i]) trueCnt++;
            else falseCnt++;
        }
        return trueCnt == peopleCnt || falseCnt == peopleCnt;
    }

    // 각 사람이 계단 입구까지 가는 최소 시간을 작성하는 메서드
    private static void goStairs_bfs(Move temp) {
        Queue<Move> queue = new ArrayDeque<>();
        queue.offer(temp);
        int idx = temp.idx;

        while (!queue.isEmpty()) {
            Move current = queue.poll();
            int cx = current.x;
            int cy = current.y;
            int cm = current.m;
            if (!target[idx]) { // stairA
                if (cx == stairA.x && cy == stairA.y) {
                    current.k = map[stairA.x][stairA.y];
                    minutes[idx] = current;
                    return;
                }
            }
            else { // stairB
                if (cx == stairB.x && cy == stairB.y) {
                    current.k = map[stairB.x][stairB.y];
                    minutes[idx] = current;
                    return;
                }
            }

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.offer(new Move(nx, ny, cm + 1, idx, 0));
                }
            }
        }
    }

    // 입력받는 지도 정보를 채우는 메서드
    private static void fillTheMap() throws IOException {
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    peopleCnt++; // 사람 수 카운트(인덱스)
                    people[peopleCnt] = new Pair(i, j); // 사람 좌표 저장
                } else if (map[i][j] > 1) {
                    if (stairA == null) stairA = new Pair(i, j);
                    else stairB = new Pair(i, j);
                }
            }
        }
    }

    //static class section
    static class Pair { // 좌표 저장을 위한 클래스
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Move implements Comparable<Move> { // 좌표 + 시간 저장을 위한 클래스
        int x; // x(세로) 좌표
        int y; // y(가로) 좦
        int m; // 시간
        int idx; // 사람 번호 => target, minutes
        int k; // 계단 카운트

        public Move(int x, int y, int m, int idx, int k) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.idx = idx;
            this.k = k;
        }

        @Override
        public int compareTo(Move o) {
            return this.m - o.m;
        }
    }
}
