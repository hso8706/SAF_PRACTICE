package steps.ac_generalMath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2720 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] moneyType = new int[]{25,10,5,1};
    static int T, C;
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int t = 1; t <= T; t++) {
            init();
            printReturnMoney(C,0);
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static void printReturnMoney(int money, int idx) throws IOException {
        if(idx==4) return;

        int returnType = money/moneyType[idx];
        int remainder = money%moneyType[idx];
        bw.write(returnType+" ");
        printReturnMoney(remainder, idx+1);
    }

    private static void init() throws IOException {
        C = Integer.parseInt(bf.readLine());
    }
}
