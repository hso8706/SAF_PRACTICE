package aSAF.recur_02_230207;

import java.util.Scanner;

public class SW1210_HaJungHo {
    public static void main(String[] args) {
        // 사다리 타기, 결승 지점과 연결되는 시작점 찾기
        // 반환값: 도착점에 대응되는 출발점 좌표 반환(x 좌표만 반환)
        // 0 == 빈 곳
        // 1 == 길
        // 2 == 도착 지점
        // 사다리 타기 판 크기 : 100 x 100

        Scanner sc = new Scanner(System.in);
//        int T = 1;
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //입력 1: tc 번호
            int tcNum = sc.nextInt();
            //사다리 타기 빈 공간 생성
            int[][] ladderBoard = new int[100][100];
            //도착지점 좌표 등록 변수
            int endX = -1;
            int endY = -1;
            //입력 2: 사다리 타기 길 코드
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 100; j++) {
                    ladderBoard[i][j] = sc.nextInt();
                    if (ladderBoard[i][j] == 2){
                        endX = j;
                        endY = i;
                    }
                }
            }
            int result = backwardsWay(endX, endY, ladderBoard);
            System.out.printf("#%d %d\n", test_case, result);

        }
    }

    private static int backwardsWay(int x, int y, int[][] ladderBoard){
        int xP = x;
        int yP = y;
        // 반복문 횟수: n, 100 <= n < 200;
        // 종료 조건: 반복문 끝 or yP == 0
        // 방향 지정
        int direction = 0;
        // 상 == 1
        // 좌 == 2
        // 우 == 3
        if (xP == 0 || xP == 99) direction = 1;
        else if (ladderBoard[yP][xP - 1] == 1) direction = 2;
        else if (ladderBoard[yP][xP + 1] == 1) direction = 3;
        else direction = 1;

        while(true){

            if (yP == 0) return xP;
            if (direction == 1){
                yP -= 1;
                if (xP - 1 >= 0 && ladderBoard[yP][xP -1] == 1) direction = 2;
                else if (xP + 1 <= 99 && ladderBoard[yP][xP+1] == 1) direction = 3;
            }
            else if (direction == 2) {
                xP -= 1;
                if (xP - 1 < 0){
                    direction = 1;
                    continue;
                }
                if (ladderBoard[yP][xP - 1] != 1) direction = 1;
            }
            else if (direction == 3) {
                xP += 1;
                if (xP + 1 > 99){
                    direction = 1;
                    continue;
                }
                if (ladderBoard[yP][xP +1] != 1) direction = 1;
            }
        }
    }
}
