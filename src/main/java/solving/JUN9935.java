package solving;

import java.io.*;
import java.util.*;

public class JUN9935 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 문자열 폭발
     */

    //메모리 초과
//    public static void main(String[] args) throws IOException {
//        String total = bf.readLine();
//        String explosion = bf.readLine();
//
//        while(true){
//            if (total.contains(explosion)) total = total.replace(explosion, "");
//            else break;
//        }
//        if(total.equals("")) total = "FRULA";
//        bw.write(total);
//        bw.flush();
//        bw.close();
//    }
//    public static void main(String[] args) throws IOException {
//        ArrayDeque<String> total = new ArrayDeque<>();
//        String[] totalInput = bf.readLine().split("");
//        for (int i = 0; i < totalInput.length; i++) {
//            total.offer(totalInput[i]);
//        }
//
//    }
}
