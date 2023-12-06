package steps.ag_bruteforce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN19532 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int a,b,c,d,e,f;
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(bf.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        out: for (int x = -999; x <= 999; x++) {
            for (int y = -999; y <= 999; y++) {
                int first = a*x + b*y;
                int second = d*x + e*y;
                if(first==c && second==f){
                    bw.write(x +" " + y);
                    break out;
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
