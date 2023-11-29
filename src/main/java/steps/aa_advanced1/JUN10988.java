package steps.aa_advanced1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN10988 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static String str;
    public static void main(String[] args) throws IOException {

        init();

        System.out.println(checkPalindrome());
    }

    private static int checkPalindrome() {
        int result = 1;
        int end = str.length();
        for (int i = 0; i < end; i++) {
            if (str.charAt(i) != str.charAt(end - i - 1)) {
                result = 0;
                break;
            }
        }

        return result;
    }

    private static void init() throws IOException {
        str = bf.readLine();
    }

}
