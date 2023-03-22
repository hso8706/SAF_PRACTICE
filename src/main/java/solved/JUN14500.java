package solved;

import java.io.*;
import java.util.*;

public class JUN14500 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 테트로미노
    - 테트로미노 : 폴리오미노 4개를 이어붙인 도형
    - 5종류의 테트로미노 존재
    - N X M 판 존재, 판의 각 칸에는 숫자 존재 (4 <= N, M <= 500)
    - 적절한 테트로미노를 놓았을때, 가려지는 칸의 숫자 총합이 최대인 경우 출력
    - 테트로미노 회전, 대칭 가능

    ### 제한 조건
    - 2초, 512MB => 넉넉한 편

    ### 문제 해결1. 모든 경우의 수
    - (각각의 테트로미노의 회전, 대칭의 경우의 수) * (각각의 테트로미노 경우의 수에서 판을 가리는 경우의 수)
    - 각각의 테트로미노의 회전, 대칭의 경우의 수 : 1, 2, 4, 4, 8
    - 각각의 테트로미노 경우의 수에서 판을 가리는 경우의 수 : ...
    => 될 것 같긴함. 다른 생각부터 해보기

    ### 문제 해결2. 테트로미노 생성 로직(낙찰, 가보자)
    - 2중 반복문으로 전체 board 순회
    - 4개 칸 수를 지정하고 4방 탐색을 결합해서 테트로미노를 생성하는 로직 구현
    - dfs 로 순회
    +  ㅗ ㅏ ㅜ ㅓ 모양을 만들지 못했음. => cnt == 3 인 경우에만 추가 로직 => 주석 처리 부분 다시 보기
     */
    static int N, M; //N: 세로, M: 가로
    static int[][] board;
    static int[] dn = new int[]{0, -1, 1, 0}; // 우 상 하 좌
    static int[] dm = new int[]{1, 0, 0, -1};
    static PriorityQueue<Integer> pq;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        pq = new PriorityQueue<>(Collections.reverseOrder()); // 내림차순
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                createTetromino(i, j, new boolean[N][M], 0, 0);
            }
        }
        bw.write(pq.poll() + "");
        bw.flush();
        bw.close();

    }
    //dfs 방식
    private static void createTetromino(int n, int m, boolean[][] visited, int cnt, int sum) {
        if(cnt == 4){
            pq.offer(sum);
            return;
        }
        visited[n][m] = true;
        sum += board[n][m];

        for (int i = 0; i < 4; i++) {
            int nn = n + dn[i];
            int nm = m + dm[i];
            if(nn < 0 || nm < 0 || nn >= N || nm >= M) continue;
            if(!visited[nn][nm]){
                createTetromino(nn, nm, visited, cnt+1, sum);
            }
//            if(cnt == 3){ //3줄짜리로 끝에서 생성될 경우 종료 후 가운데 채워진 테트로미노 생성, 방문 처리에서 제외
//                if((nn - n) == 1){ //세로
//                    nn = n;
//
//                    nm  = m + 1;
//                    if(nm < M) pq.offer(sum+board[nn][nm]);
//                    nm  = m - 1;
//                    if(nm >= 0) pq.offer(sum+board[nn][nm]);
//                }
//                else { //가로
//                    nm  = m;
//
//                    nn = n-1;
//                    if(nn >= 0) pq.offer(sum+board[nn][nm]);
//                    nn = n+1;
//                    if(nn < M) pq.offer(sum+board[nn][nm]);
//                }
//            }
        }
    }
}
