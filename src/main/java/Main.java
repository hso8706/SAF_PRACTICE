import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n;
    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(bf.readLine());
        Deque<Integer> deque = new ArrayDeque<>();

        while(n > 0){
            deque.offerFirst(n%2);
            n /= 2;
        }

        String several = "";
        int cnt0 = 0;
        int cnt1 = 0;
        while(!deque.isEmpty()){
            String temp = String.valueOf(deque.pollFirst());
            if(temp.equals("0")) cnt0++;
            else cnt1++;
            several += temp;
        }
    }
}