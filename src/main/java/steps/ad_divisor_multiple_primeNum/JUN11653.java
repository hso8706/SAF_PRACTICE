package steps.ad_divisor_multiple_primeNum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class JUN11653 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /*
    # 시간초과
    - 소수 집합을 미리 설정
    - 소수 집합을 순회하며 나눠지는 소수가 있을때마다 출력 저장
    - 소수 집합 내에 나눠지는 수가 없을때까지 반복

    # 성공-164ms
    - 필요할 때마다 소수인지 검증하고 출력

    # 성공-124ms
    - 소수를 구하는 반복문 내에서 출력하기
     */
    static int N;
    public static void main(String[] args) throws IOException {

        init();
        printFactorization();

    }

    private static void printFactorization() throws IOException {
        int number = N;
        for (int i = 2; i*i <= number ; i++) {
            while(number%i == 0){
                bw.write(i+"\n");
                number /= i;
            }
        }
        if(number != 1) bw.write(number + "\n");
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {

        N = Integer.parseInt(bf.readLine());
    }
}
