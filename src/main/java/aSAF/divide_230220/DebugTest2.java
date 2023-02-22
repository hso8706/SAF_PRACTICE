package aSAF.divide_230220;

import java.util.Arrays;

public class DebugTest2 {
    public static void main(String[] args) {
        int[][] map = {
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5},
                {1,2,3,4,5}
        };

        int[][] result = new int[5][5];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if((i+j)%3 == 0)
                    result[i][j] = map[i][j] * 2;
            }
        }
        System.out.println(Arrays.deepToString(result));
    }
}
