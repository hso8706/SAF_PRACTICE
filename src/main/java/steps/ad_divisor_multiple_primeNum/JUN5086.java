package steps.ad_divisor_multiple_primeNum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN5086 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int first, second;
    static boolean isEnd;
    public static void main(String[] args) throws IOException {

        while(true){
            init();
            if(isEnd){
                bw.flush();
                bw.close();
                break;
            }
            checker();
        }
    }

    private static void checker() throws IOException {
        boolean isFactor = false;
        boolean isMultiple = false;

        if(first > second){
            isMultiple = first % second == 0;
        } else {
            isFactor = second % first == 0;
        }

        if(!isFactor && !isMultiple){
            bw.write("neither");
        }
        else if(isMultiple){
            bw.write("multiple");
        }
        else {
            bw.write("factor");
        }
        bw.write("\n");
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        first = Integer.parseInt(st.nextToken());
        second = Integer.parseInt(st.nextToken());
        if(first==0 && second==0){
            isEnd = true;
        }
    }

}
