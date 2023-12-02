package steps.ad_divisor_multiple_primeNum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JUN9506 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static List<Integer> divisors;
    public static void main(String[] args) throws IOException {
        while(true){
            init();
            if(n==-1) break;
            if(isPerfect()){
                printPerfect();
            }
            else {
                bw.write(n + " is NOT perfect.");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean isPerfect() {
        int result = 0;
        for(int i : divisors){
            result += i;
        }
        return result == n;
    }

    private static void printPerfect() throws IOException {
        for(int i=0; i<divisors.size(); i++){
            if(i==0){
                bw.write(n+" = "+divisors.get(i));
            }
            else {
                bw.write(" + "+divisors.get(i));
            }
        }
    }

    private static void init() throws IOException {
        n = Integer.parseInt(bf.readLine());

        divisors = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            if(n%i == 0) divisors.add(i);
        }
    }

}
