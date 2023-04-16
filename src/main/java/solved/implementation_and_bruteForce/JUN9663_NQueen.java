package solved.implementation_and_bruteForce;

import java.io.*;
import java.util.Map;
import java.util.StringTokenizer;

public class JUN9663_NQueen {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 해결 1.
    - 2차원 배열, 완탐
        - 미해결
    ### 해결 2.
    - 한 줄(col, row)에 하나의 퀸만이 놓일 수 있다는 사실을 기반으로 문제 해결
    - 인덱스가 column 번호인 1차원 배열, 값은 row 의 위치
    - 1번 컬럼의 1번 row부터 값을 채우기 시작
    - 다음 컬럼의 값을 채울 부분이 퀸의 공격 영역이 아닌지 확인하는 로직 구현
        - 포인트 1: 이전에 놓인 모든 퀸과 대조해야한다.
        - 포인트 2: 대각선 로직
            - 검색 결과 : col의 차이와 row의 차이가 같으면 대각선
     */
    static int N;
    static int[] board;
    static int totalCnt;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        board = new int[N + 1]; // 1,1 부터 시작
        totalCnt = 0;

        queen_dfs(1);
        bw.write(totalCnt + "");
        bw.flush();
        bw.close();
    }

    private static void queen_dfs(int colIdx) {
        if (colIdx == N + 1) {
            totalCnt++;
            return;
        }

        for (int i = 1; i < N + 1; i++) {
            board[colIdx] = i;
            if (canPut(colIdx)) { // 현재 위치에 놓은 경우 다음으로 넘어갈 수 있는지 판단
                queen_dfs(colIdx + 1);
            }
        }
    }

    private static boolean canPut(int current) {
        //1. 같은 col => 1차원 배열로 해결
        for (int i = 1; i < current; i++) {
            //2. 같은 row => 이전 배열 값과 같으면 같은 row
            if(board[i] == board[current]) return false; // 배열 중 같은 row 에 퀸이 있음

            //3. 같은 대각선
            // 좌측 대각선은 고려할 필요가 없음(이미 넘어간 column)
            // 우상단: (-1,+1), 우하단: (+1,+1)만 고려
            // col 의 차이와 row 의 차이가 같으면 대각선
            if(Math.abs(i-current) == Math.abs(board[i]- board[current])) return false;
        }

        return true;
    }
}
