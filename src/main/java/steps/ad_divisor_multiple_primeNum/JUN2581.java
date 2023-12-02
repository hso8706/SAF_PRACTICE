package steps.ad_divisor_multiple_primeNum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JUN2581 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int M, N;
    static List<Integer> primes;
    public static void main(String[] args) throws IOException {

        init();
        makePrimes();
        output();
    }

    private static void output() throws IOException {
        if(primes.size()==0){
            bw.write(-1+"");
        }
        else {
            int result = 0;
            for(int prime : primes){
                result += prime;
            }
            bw.write(result+"\n");
            bw.write(primes.get(0)+"");
        }
        bw.flush();
        bw.close();
    }

    private static void makePrimes() {
        for (int i = M; i <= N ; i++) {
            if(isPrime(i)) primes.add(i);
        }
    }

    private static boolean isPrime(int i) {
        boolean checker = true;
        if (i==1) return false;
        for (int j = 1; j <= i; j++) {
            if(j != 1 && j != i && i%j==0) checker = false;
        }
        return checker;
    }

    private static void init() throws IOException {
        M = Integer.parseInt(bf.readLine());
        N = Integer.parseInt(bf.readLine());
        primes = new ArrayList<>();
    }
}
