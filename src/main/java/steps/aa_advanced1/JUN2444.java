package steps.aa_advanced1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2444 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    public static void main(String[] args) throws IOException {

        init();

        printRhombus(1);
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
    }

    private static void printRhombus(int i) throws IOException {
        if(i==n){
            for (int j = 0; j < 2*i-1; j++) {
                bw.write("*");
            }
            bw.write("\n");
            return;
        }

        printRow(i);
        printRhombus(i+1);
        printRow(i);
    }

    private static void printRow(int i) throws IOException {
        for (int j = n-i; j > 0; j--) {
            bw.write(" ");
        }
        for (int j = 0; j < i*2-1; j++) {
            bw.write("*");
        }
        bw.write("\n");
    }


}
