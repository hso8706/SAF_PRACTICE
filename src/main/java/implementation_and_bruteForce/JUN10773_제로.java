package implementation_and_bruteForce;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class JUN10773_제로 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        Stack<Integer> jaminSum = new Stack<>();
        int K = Integer.parseInt(bf.readLine());
        for (int i = 0; i < K; i++) {
            int input = Integer.parseInt(bf.readLine());
            if(input == 0) jaminSum.pop();
            else jaminSum.push(input);
        }
        int sum = 0;

        while (!jaminSum.isEmpty()) {
            sum += jaminSum.pop();
        }
        bw.write(sum + "");
        bw.flush();
        bw.close();
    }
}
