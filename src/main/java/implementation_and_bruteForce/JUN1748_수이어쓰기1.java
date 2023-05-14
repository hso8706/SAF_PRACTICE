package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JUN1748_수이어쓰기1 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bf.readLine());
        int len = 0;

        for (int i = 1; i <= N; i++) {
            if(i<10) len++;
            else if(i<100) len+=2;
            else if(i<100_0) len+=3;
            else if(i<100_00) len+=4;
            else if(i<100_000) len+=5;
            else if(i<100_000_0) len+=6;
            else if(i<100_000_00) len+=7;
            else if(i<100_000_000) len+=8;
            else len+=9;
        }
        System.out.println(len);
    }

}
