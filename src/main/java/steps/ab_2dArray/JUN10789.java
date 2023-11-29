package steps.ab_2dArray;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN10789 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char[][] toy;
    static int maxLength;
    public static void main(String[] args) throws IOException {

        init();
        
        printToy();
    }

    private static void printToy() throws IOException {
        for (int i = 0; i < maxLength; i++) {
            for (int j = 0; j < 5; j++) {
                if(toy[j].length > i) bw.write(toy[j][i]);
            }
        }
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        toy = new char[5][];
        maxLength = 0;
        for (int i = 0; i < 5; i++) {
            toy[i] = bf.readLine().toCharArray();
            maxLength = Math.max(maxLength, toy[i].length);
        }
    }

}
