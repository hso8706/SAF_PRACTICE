package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN14890_경사로 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 길: 2N; 가로, 세로
    - 경사로: 높이=1, 길이=L(입력)
    - 지나갈 수 있는 길의 개수 출력

    - 가로 타입 순회, 세로 타입 순회로 종류를 나눌 것
    - 높이가 1이 차이나는 순간까지 길이를 카운팅하고 L과 비교하여 가능 여부 판단할 것
        - 높이가 2 이상 차이나면 바로 종료
        - 값이 변하는 순간 값이 작아지는지, 값이 커지는지 확인
     */
    static int N, L;
    static int[][] map;
    static int possible;
    static int[] high_row; // 가로길에서 최고점
    static int[] high_col; // 세로길에서 최고점
    static boolean[][][] runway;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        high_row = new int[N];
        high_col = new int[N];
        runway = new boolean[2][N][N]; // 0 가로, 1 세로

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int rowH = 0;
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                rowH = Math.max(rowH, map[i][j]);
                high_col[j] = Math.max(high_col[j], map[i][j]);
            }
            high_row[i] = rowH;
        }

        //(가로줄==길)인 상황
        for (int i = 0; i < N; i++) {
            int cLevel = map[i][0]; // 현재 높이(시작점의 높이)
            boolean isPossible = true;
            out:
            for (int j = 1; j < N; j++) { // 가로줄 하나 체크
                //높이가 달라지는 경우 체크
                //1) 높이가 높아지는 경우 => 현재 지점부터 이전으로 검사하여 경사로 놓을 수 있는 지 확인
                if (map[i][j] - cLevel == 1) {
                    int cnt = 1;
                    int ni = i;
                    int nj = j - 1;
                    int cCheck = map[ni][nj];
                    if (runway[0][ni][nj]) {
                        isPossible = false;
                        break out;
                    }
                    runway[0][ni][nj] = true;
                    while (cnt < L) {
                        nj -= 1;
                        if (nj < 0 || cCheck != map[ni][nj] || runway[0][ni][nj]) {
                            isPossible = false;
                            break out;
                        }
                        runway[0][ni][nj] = true;
                        cCheck = map[ni][nj];
                        cnt++;
                    }
                    cLevel = map[i][j];
                }
                //2) 높이가 낮아지는 경우 => 다음 지점부터 다음으로 검사하여 경사로 놓을 수 있는 지 확인
                else if (cLevel - map[i][j] == 1) {
                    int cnt = 1;
                    int ni = i;
                    int nj = j;
                    int cCheck = map[ni][nj];
                    if (runway[0][ni][nj]) {
                        isPossible = false;
                        break out;
                    }
                    runway[0][ni][nj] = true;
                    while (cnt < L) {
                        nj += 1;
                        if (nj >= N || cCheck != map[ni][nj] || runway[0][ni][nj]) {
                            isPossible = false;
                            break out;
                        }
                        runway[0][ni][nj] = true;
                        cCheck = map[ni][nj];
                        cnt++;
                    }
                    cLevel = map[i][j];
                }
                //3) 높이가 같은 경우 cLevel 갱신
                else if (cLevel == map[i][j]) {
                    cLevel = map[i][j];
                } else { // 4) 높이 차이가 2 이상 나는 경우
                    isPossible = false;
                    break out;
                }
            }
            if (isPossible) {
                possible++;
            }
        }
        //(세로줄==길)인 상황
        for (int i = 0; i < N; i++) {
            int cLevel = map[0][i]; // 현재 높이(시작점의 높이)
            boolean isPossible = true;
            out:
            for (int j = 1; j < N; j++) {
                //높이가 달라지는 경우 체크
                //1) 높이가 높아지는 경우 => 현재 지점부터 이전으로 검사하여 경사로 놓을 수 있는 지 확인
                if (map[j][i] - cLevel == 1) {
                    int cnt = 1;
                    int ni = i;
                    int nj = j - 1;
                    int cCheck = map[nj][ni];
                    if (runway[1][nj][ni]) {
                        isPossible = false;
                        break out;
                    }
                    runway[1][nj][ni] = true;
                    while (cnt < L) {
                        nj -= 1;
                        if (nj < 0 || cCheck != map[nj][ni] || runway[1][nj][ni]) {
                            isPossible = false;
                            break out;
                        }
                        runway[1][nj][ni] = true;
                        cCheck = map[nj][ni];
                        cnt++;
                    }
                    cLevel = map[j][i];
                }
                //2) 높이가 낮아지는 경우 => 다음 지점부터 다음으로 검사하여 경사로 놓을 수 있는 지 확인
                else if (cLevel - map[j][i] == 1) {
                    int cnt = 1;
                    int ni = i;
                    int nj = j;
                    int cCheck = map[nj][ni];
                    if (runway[1][nj][ni]) {
                        isPossible = false;
                        break out;
                    }
                    runway[1][nj][ni] = true;
                    while (cnt < L) {
                        nj += 1;
                        if (nj >= N || cCheck != map[nj][ni] || runway[1][nj][ni]) {
                            isPossible = false;
                            break out;
                        }
                        runway[1][nj][ni] = true;
                        cCheck = map[nj][ni];
                        cnt++;
                    }
                    cLevel = map[j][i];
                }
                //3) 높이가 같은 경우 cLevel 갱신
                else if (cLevel == map[j][i]) {
                    cLevel = map[j][i];
                } else { // 4) 높이 차이가 2 이상 나는 경우
                    isPossible = false;
                    break out;
                }
            }
            if (isPossible) {
                possible++;
            }
        }

        System.out.println(possible);
    }
}