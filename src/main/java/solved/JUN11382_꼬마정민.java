package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN11382_꼬마정민 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        long result = 0;
        for (int i = 0; i < 3; i++) {
            result += Long.parseLong(st.nextToken());
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
    }

}
