//package solving;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.ArrayDeque;
//import java.util.Arrays;
//import java.util.Deque;
//import java.util.StringTokenizer;
//
//public class JUN11726_2Xn타일링 {
//
//    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
//    static StringTokenizer st;
//
//    static int n;
//    static int[] selected;
//
//    public static void main(String[] args) throws IOException {
//
//        n = Integer.parseInt(bf.readLine());
//
//        int result = 0;
//        if(n == 1) bw.write(0 + "");
//        else {
//            result = 1 + combi(0, 0, );
//        }
//        Deque<Integer> deque = new ArrayDeque<>();
//        deque.offerFirst(2);
//
//        String temp = String.valueOf(deque.pollFirst());
//
//    }
//
//    private static void combi(int cnt, int start, int end, int totalCnt) {
//        if(cnt == end){
//            totalCnt++;
//            System.out.println(Arrays.toString(selected));
//            return;
//        }
//
//        for (int i = start; i < N; i++) {
//            combi(cnt +1, i+1, end);
//        }
//    }
//}
