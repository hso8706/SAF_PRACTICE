package sc;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SlidingWindowExample {

    public static void main(String[] args) {
        int n = 100;
        int k = 7000;
        int[][] grid = generateRandomGrid(n);

        System.out.print("[");
        for (int i = 0; i < n; i++) {
            if(i==0) System.out.print("[");
            else System.out.print(",[");
            for (int j = 0; j < n; j++) {
                // 모든 격자를 동일한 색으로 설정
                if(j==n-1) System.out.print(grid[i][j]+"]");
                else System.out.print(grid[i][j]+",");
            }
        }
        System.out.println("]");
//        System.out.println(solution(4,3,new int[][]{{1,2,2,2},{1,2,1,1},{1,2,2,1},{3,2,1,1}}));
//        System.out.println(solution(3,2,new int[][]{{1,1,1},{1,2,2},{1,2,4}}));
//        System.out.println(solution(2,4,new int[][]{{1,2},{3,4}}));
        System.out.println(solution(n, k, grid));
    }

    public static int solution(int n, int k, int[][] grid){
        int answer = 0;

        result: for(int squareSide=n; squareSide>=1; squareSide--){
            for(int rangeX=0; rangeX<=n-squareSide; rangeX++){
                for(int rangeY=0; rangeY<=n-squareSide; rangeY++){
                    int[] colors = new int[11];
                    int maxColorCount =0;
                    int otherColorCount = 0;

                    for(int x=rangeX; x<rangeX+squareSide; x++){
                        for(int y=rangeY; y<rangeY+squareSide; y++){
                            int currentColor = grid[x][y];
                            colors[currentColor]++;
                            maxColorCount = Math.max(maxColorCount, colors[currentColor]);
                        }
                    }
                    otherColorCount = squareSide*squareSide - maxColorCount;
//                    System.out.println("시작점: ("+rangeX+","+rangeY+"), 최대값: "+maxColorCount +", 다른값: "+otherColorCount);
                    if(otherColorCount <= k){
                        answer = squareSide* squareSide;
                        break result;
                    }
                }
            }
        }
        return answer;
    }

    public static int[][] generateRandomGrid(int n) {
        int[][] grid = new int[n][n];
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 1부터 10까지의 랜덤한 정수를 격자에 할당
                grid[i][j] = random.nextInt(10) + 1;
            }
        }

        return grid;
    }
}
