package com.tree_02_230215;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 좌표의 정보를 담는 노드 클래스 생성
class Node {

    int y;
    int x;

    public Node(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class SW1861_HaJungHo {

    static int n; // board 의 크기를 결정하는 값
    static int[][] board; // n*n board
    static int[][] visited; // n*n board 의 지나간 자리 체크, 체크는 누적 방식
    static int answer; // 첫 시작점에 할당된 값
    static int maxCnt; // 최대로 움직인 방의 개수
    static int[] dx = { 1, 0, -1, 0 }; // 이동가능한 x 좌표 칸 수
    static int[] dy = { 0, 1, 0, -1 }; // 이동가능한 y 좌표 칸 수, x 좌표 칸 수와 쌍으로 활용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tcs = Integer.parseInt(br.readLine()); // 첫번째 입력, 테스트 케이스
        for (int tc = 1; tc < tcs + 1; tc++) { // tc 만큼 순회
            sb.append("#").append(tc).append(" "); // 출력 앞부분을 버퍼에 미리 저장
            n = Integer.parseInt(br.readLine()); // 두번째 입력, n
            board = new int[n][n]; // board 크기 초기화
            maxCnt = Integer.MIN_VALUE; // maxCnt 초기화
            for (int i = 0; i < n; i++) { // board 내용 초기화
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i = 0; i < n; i++) { // 방 개수(길찾기) 반복문
                for (int j = 0; j < n; j++) {
                    bfs(new Node(i, j)); // node 인스턴스를 인자로 제공
                }
            }

            sb.append(answer).append(" ").append(maxCnt).append("\n"); // 결과적으로 한 번의 tc 마다 시작점에 할당된 값과 최대 방의 개수가 반환됨
        }

        System.out.println(sb);
    }

    private static void bfs(Node node) {
        Queue<Node> que = new ArrayDeque<>(); // queue 사용 
        que.offer(node); // 첫 지점 queue 에 넣기, bfs 첫 단계
        visited = new int[n][n]; // 매번 새로운 visited 생성
        visited[node.y][node.x] = 1; // 첫 시작 지점 체크

        while (!que.isEmpty()) { // queue 비워질 때까지 순회
            Node nd = que.poll(); // 디큐
            for (int i = 0; i < 4; i++) { // 사방 순회 반복문
                int ny = nd.y + dy[i];
                int nx = nd.x + dx[i];
                // 조건 1. nx, ny 는 board 에 있는 좌표여야 한다.
                // 조건 2. (nx, ny) 좌표는 방문하지 않은 곳이어야 한다.
                // 조건 3. (nx, ny) 좌표에 할당된 값은 (x, y) 좌표에 할당된 값보다 1 많아야 한다.
                if (0 <= nx && nx < n && 0 <= ny && ny < n && visited[ny][nx] == 0 && board[ny][nx] == board[nd.y][nd.x] + 1) {
                    visited[ny][nx] = visited[nd.y][nd.x] + 1; // 지나간 길을 누적식으로 체크, 최대 방의 개수를 구할 수 있음
                    que.offer(new Node(ny, nx)); // (nx, ny) 좌표값에 대한 node 인스턴스를 enqueue
                    if (maxCnt < visited[ny][nx]) { // maxCnt 값과 현재 최대 방의 개수 비교
                        // 새로운 좌표의 방의 개수가 최대일 때, maxCnt 와 answer 값 갱신
                        answer = board[node.y][node.x];
                        maxCnt = visited[ny][nx];
                    } else if (maxCnt == visited[ny][nx]) { // maxCnt 가 최대 방의 개수랑 같을때
                        answer = Math.min(answer, board[node.y][node.x]); // answer 값 갱신
                    }
                }
            }
        }

    }

}
//thanks to 남곤