package steps.ac_generalMath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUN2903 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    static int[] result;
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bf.readLine());
        result = new int[16];
        makeResult(3, 2, 1);
        System.out.println(result[N]);
    }

    private static void makeResult(int n, int step, int idx) {
        if(idx == 16) return;
        result[idx] = (int) Math.pow(n,2);
        makeResult(n+step, step*2, idx+1);
    }

}
