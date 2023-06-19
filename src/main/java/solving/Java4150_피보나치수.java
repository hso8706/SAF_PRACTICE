package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Java4150_피보나치수 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(bf.readLine());
        int F0=0, F1=1, F2=1;

        /*
        F(n) = F(n-2) + F(n-1); n>=2
         */
        for(int i=2; i<=n; i++) {
            F2 = F1 + F0; // 피보나치 수열 계산
            F0 = F1; // F0 갱신
            F1 = F2; // F1 갱신
        }

        System.out.println(F2);
    }
}
