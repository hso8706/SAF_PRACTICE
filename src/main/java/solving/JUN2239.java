//package solving;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.StringTokenizer;
//
//public class JUN2239 {
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//    static StringTokenizer st;
//    /*
//    ### 스도쿠
//    - 잠정 포기;;
//    ### 문제 해결 1.
//    - 빈 칸의 모든 값을 채워준 후 맞는 값이 있을 때 출력
//        - 빈 칸을 채울 때 스도쿠 조건을 넣을 수 있으면 더욱 좋음
//     */
//    static int[][] board = new int[9][9];
//    public static void main(String[] args) throws IOException {
//
//        for (int i = 0; i < 9; i++) {
//            char[] temp= bf.readLine().toCharArray();
//            for (int j = 0; j < 9; j++) {
//                board[i][j] = temp[j] - '0';
//            }
//        }
//        if(fillTheBoard(0, false)){
//            for (int i = 0; i < 9; i++) {
//                bw.write(Arrays.toString(board[i])+"\n");
//            }
//        }
//    }
//
//    private static boolean fillTheBoard(int i, boolean flag) {
//        if(i==9){
//            if(checkCol() && checkSection()) {
//                return false;
//            }
//            return true;
//        }
//
//        while (!flag){
//            int cnt = 9;
//            for (int j = 0; j < 9; j++) {
//                while(checkRow(board[i], cnt)) {
//                    cnt--;
//                }
//                if(board[i][j] == 0) board[i][j] = cnt;
//            }
//            flag = fillTheBoard(i+1, flag);
//        }
//        return flag;
//    }
//
//    // 가로 한 줄을 확인하고 칸을 채울 숫자를 파악하기 위한 메서드
//    private static boolean checkRow(int[] board, int cnt) {
//        for (int i = 0; i < 9; i++) {
//            if(board[i] == cnt) return true;
//        }
//        return false;
//    }
//}
