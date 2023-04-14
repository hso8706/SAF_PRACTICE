package solved.implementation_and_bruteForce;

import java.io.*;
import java.util.StringTokenizer;

public class JUN1436_영화감독숌 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String apocalypse = "666";
        String result = "";
        int head = 0;
        int tail = 0;
        int N = Integer.parseInt(bf.readLine());

        for (int i = 1; i < N+1; i++) {
            if(head%10 != 6){
                if(head == 0) result = apocalypse;
                else result = head + apocalypse;
                head++;
            }
            else {
                int sixCnt = howManySix(head);
                if(sixCnt == 1){
                    result = head + "66" + tail;
                    tail++;
                    if (tail == 10){
                        head++;
                        tail = 0;
                    }
                }
                else if (sixCnt == 2){
                    if(tail < 10) result = head + "60" + tail;
                    else result = head + "6" + tail;
                    tail++;
                    if (tail == 100){
                        head++;
                        tail = 0;
                    }
                }
                else {
                    if(tail < 10) result = head + "00" + tail;
                    else result = head + "" + tail;
                    tail++;
                    if (tail == 1000){
                        head++;
                        tail = 0;
                    }
                }
            }
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

    private static int howManySix(int head) {
        String headStr = String.valueOf(head);
        char[] headChar = headStr.toCharArray();
        int cntSix = 0;
        for (int i = 0; i < headChar.length; i++) {
            if (headChar[i] == '6') cntSix++;
            else cntSix = 0;
        }
        return cntSix;
    }
}