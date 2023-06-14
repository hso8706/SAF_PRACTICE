package solving;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class JUN1629_곱셈 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    /*
    ### 지수 법칙
    a^(m+n) = a^n * a^m

    ### 모듈어 성질
    (A*B)%C = (A%C * B%C)%C
     */
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        //지수(B) 분해 => 짝수, 홀수


        System.out.println(doPow(A, B, C));
    }

    private static long doPow(int A, int B, int C) {
        if(B == 0) return 1;

        long result = doPow(A, B/2, C);
        if(B%2 == 0) return result * result % C;
        else return (result * result % C) * A % C;
    }

}
