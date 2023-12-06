package steps.ag_bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2231 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String N;
    public static void main(String[] args) throws IOException {

        N = bf.readLine();
        int tN = Integer.parseInt(N);
        int answer = 0;

        for (int i = 1; i <= tN; i++) {
            String[] sI = String.valueOf(i).split("");
            int sum = findSum(i, sI);
            if(sum == tN){
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    private static int findSum(int i, String[] sI) {
        int result = 0;

        for(String s : sI){
            result += Integer.parseInt(s);
        }

        return result+i;
    }
}
