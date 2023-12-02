package steps.ac_generalMath;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN2869 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int A,B,V;
    public static void main(String[] args) throws IOException {

        init();
        snailClimbing();
    }

    //시간초과를 고려한 로직 구현
    private static void snailClimbing() {
        int result = 1;

        int aim = V-A;
        int perDay = A-B;
        int remainder = aim%perDay;

        if(remainder!=0){
            result += aim/perDay + 1;
        }
        else {
            result += aim/perDay;
        }
        System.out.println(result);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
    }

}
