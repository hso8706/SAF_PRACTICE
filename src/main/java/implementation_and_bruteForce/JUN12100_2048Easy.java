package implementation_and_bruteForce;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN12100_2048Easy {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 2048(Hard)
     */
    static int N;
    static int[][] board;
    static int[][] temp;


    static char[] dir = new char[]{'u', 'd', 'l', 'r'};
    static char[] dirCase;
    static int maxBlock;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dirCase = new char[10];
        maxBlock = 0;
        makeDirCase(0);
        System.out.println(maxBlock);
    }

    private static void makeDirCase(int cnt) {
        if (cnt == 10) {
            moveBlocks(); // 하나의 경우의 수 완성(5번 모두 이동)
            findMax(); // 완성된 경우의 수에서 최대값 찾기
            return;
        }

        for (int i = 0; i < 4; i++) {
//            if (cnt!=0 && dirCase[cnt-1] == dir[i]) continue;
            dirCase[cnt] = dir[i];
            makeDirCase(cnt + 1);
        }
    }

    private static void moveBlocks() {
        // temp 은 매번 새로 초기화
        temp = new int[N][N]; //복사해서 사용
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = board[i][j];
            }
        }
        // dirCase 순서대로 기울이기
        for (int i = 0; i < 5; i++) {
            switch (dirCase[i]) {
                case 'u':
                    for (int y = 0; y < N; y++) {
                        int stackIdx = 1; // 합쳐지지 않는 경우에 블록이 쌓이는 위치
                        for (int x = 1; x < N; x++) {
                            if (temp[x][y] == 0) continue;
                            //블록이 놓인 곳(stackIdx-1)의 정보
                            //1. 블록의 값이 0 => 이전에 과정에서 블록이 합쳐져서 생기는 상황
                            if (temp[stackIdx - 1][y] == 0) {
                                //0인 곳으로 이동하는 개념
                                temp[stackIdx - 1][y] = temp[x][y];
                                temp[x][y] = 0;
                                //그냥 이동만 한 경우는 stackIdx 의 변화를 주면 안된다.
                            }
                            //2. 블록의 값이 움직이는 블록의 값과 다름
                            else if (temp[stackIdx - 1][y] != temp[x][y]) {
                                //쌓일 위치(stackIdx)로 블록 이동
                                int tempValue = temp[x][y]; // stackIdx == y 인 경우에 값이 사라짐을 방지하기 위해서
                                temp[x][y] = 0;
                                temp[stackIdx][y] = tempValue;
                                stackIdx++;
                            }
                            //3. 블록의 값이 움직이는 블록의 값과 같음
                            else if (temp[stackIdx - 1][y] == temp[x][y]) {
                                //쌓여진 블록의 값과 이동할 블록의 값이 같으면 합쳐짐
                                //두 번 이상 합쳐지지 않으므로 다음 블록은 쌓일 수 밖에 없음 => stackIdx 증가
                                temp[stackIdx - 1][y] += temp[x][y];
                                temp[x][y] = 0;
                                stackIdx++;
                            }
                        }
                    }
                    break;
                case 'd':
                    for (int y = 0; y < N; y++) {
                        int stackIdx = N - 2; // 합쳐지지 않는 경우에 블록이 쌓이는 위치
                        for (int x = N - 2; x >= 0; x--) {
                            if (temp[x][y] == 0) continue;
                            //블록이 놓인 곳(stackIdx+1)의 정보
                            //1. 블록의 값이 0 => 이전에 과정에서 블록이 합쳐져서 생기는 상황
                            if (temp[stackIdx + 1][y] == 0) {
                                //0인 곳으로 이동하는 개념
                                temp[stackIdx + 1][y] = temp[x][y];
                                temp[x][y] = 0;
                                //그냥 이동만 한 경우는 stackIdx 의 변화를 주면 안된다.
                            }
                            //2. 블록의 값이 움직이는 블록의 값과 다름
                            else if (temp[stackIdx + 1][y] != temp[x][y]) {
                                //쌓일 위치(stackIdx)로 블록 이동
                                int tempValue = temp[x][y]; // stackIdx == y 인 경우에 값이 사라짐을 방지하기 위해서
                                temp[x][y] = 0;
                                temp[stackIdx][y] = tempValue;
                                stackIdx--;
                            }
                            //3. 블록의 값이 움직이는 블록의 값과 같음
                            else if (temp[stackIdx + 1][y] == temp[x][y]) {
                                //쌓여진 블록의 값과 이동할 블록의 값이 같으면 합쳐짐
                                //두 번 이상 합쳐지지 않으므로 다음 블록은 쌓일 수 밖에 없음 => stackIdx 증가
                                temp[stackIdx + 1][y] += temp[x][y];
                                temp[x][y] = 0;
                                stackIdx--;
                            }
                        }
                    }
                    break;
                case 'l':
                    for (int x = 0; x < N; x++) {
                        int stackIdx = 1; // 합쳐지지 않는 경우에 블록이 쌓이는 위치
                        for (int y = 1; y < N; y++) {
                            if (temp[x][y] == 0) continue;
                            //블록이 놓인 곳(stackIdx-1)의 정보
                            //1. 블록의 값이 0 => 이전에 과정에서 블록이 합쳐져서 생기는 상황
                            if (temp[x][stackIdx - 1] == 0) {
                                //0인 곳으로 이동하는 개념
                                temp[x][stackIdx - 1] = temp[x][y];
                                temp[x][y] = 0;
                                //그냥 이동만 한 경우는 stackIdx 의 변화를 주면 안된다.
                            }
                            //2. 블록의 값이 움직이는 블록의 값과 다름
                            else if (temp[x][stackIdx - 1] != temp[x][y]) {
                                //쌓일 위치(stackIdx)로 블록 이동
                                int tempValue = temp[x][y]; // stackIdx == y 인 경우에 값이 사라짐을 방지하기 위해서
                                temp[x][y] = 0;
                                temp[x][stackIdx] = tempValue;
                                stackIdx++;
                            }
                            //3. 블록의 값이 움직이는 블록의 값과 같음
                            else if (temp[x][stackIdx - 1] == temp[x][y]) {
                                //쌓여진 블록의 값과 이동할 블록의 값이 같으면 합쳐짐
                                //두 번 이상 합쳐지지 않으므로 다음 블록은 쌓일 수 밖에 없음 => stackIdx 증가
                                temp[x][stackIdx - 1] += temp[x][y];
                                temp[x][y] = 0;
                                stackIdx++;
                            }
                        }
                    }
                    break;
                case 'r':
                    for (int x = 0; x < N; x++) {
                        int stackIdx = N - 2; // 합쳐지지 않는 경우에 블록이 쌓이는 위치
                        for (int y = N - 2; y >= 0; y--) {
                            if (temp[x][y] == 0) continue;
                            //블록이 놓인 곳(stackIdx+1)의 정보
                            //1. 블록의 값이 0 => 이전에 과정에서 블록이 합쳐져서 생기는 상황
                            if (temp[x][stackIdx + 1] == 0) {
                                //0인 곳으로 이동하는 개념
                                temp[x][stackIdx + 1] = temp[x][y];
                                temp[x][y] = 0;
                                //그냥 이동만 한 경우는 stackIdx 의 변화를 주면 안된다.
                            }
                            //2. 블록의 값이 움직이는 블록의 값과 다름
                            else if (temp[x][stackIdx + 1] != temp[x][y]) {
                                //쌓일 위치(stackIdx)로 블록 이동
                                int tempValue = temp[x][y]; // stackIdx == y 인 경우에 값이 사라짐을 방지하기 위해서
                                temp[x][y] = 0;
                                temp[x][stackIdx] = tempValue;
                                stackIdx--;
                            }
                            //3. 블록의 값이 움직이는 블록의 값과 같음
                            else if (temp[x][stackIdx + 1] == temp[x][y]) {
                                //쌓여진 블록의 값과 이동할 블록의 값이 같으면 합쳐짐
                                //두 번 이상 합쳐지지 않으므로 다음 블록은 쌓일 수 밖에 없음 => stackIdx 증가
                                temp[x][stackIdx + 1] += temp[x][y];
                                temp[x][y] = 0;
                                stackIdx--;
                            }
                        }
                    }
                    break;
            }
        }
    }

    private static void findMax() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (maxBlock < temp[i][j]) maxBlock = temp[i][j];
            }
        }
    }
}

/*
반례
https://www.acmicpc.net/board/view/61812
 */