package steps.ae_rectangle_triangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN15894 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        long n = Integer.parseInt(bf.readLine());
        long answer = 0;
        //위, 아래
        answer += (n*2);
        //좌, 우
        answer += (n*2);
        System.out.println(answer);
    }
}
