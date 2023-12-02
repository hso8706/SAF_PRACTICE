package steps.ad_divisor_multiple_primeNum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2501 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, K;
    static int[] numbers;
    public static void main(String[] args) throws IOException {

        init();
        makeNumbers();
        output();
    }

    private static void output() {
        int limit = K;
        int idx = 0;
        while(true) {
            if(numbers[idx] == 0) {
                idx++;
                continue;
            }
            limit--;
            if(limit==0) break;
            idx++;
            if(idx > N) {
                System.out.println(0);
                break;
            }
        }
        if(idx<=N) {
            System.out.println(numbers[idx]);
        }
    }

    private static void makeNumbers() {
        for (int i = 1; i <= N; i++) {
            if(N%i==0) numbers[i] = i;
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        numbers = new int[N+1];
    }

}
