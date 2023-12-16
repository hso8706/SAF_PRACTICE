package binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1920 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] A, numbers;
    public static void main(String[] args) throws IOException {

        init();
        isInIt();
    }

    private static void isInIt() throws IOException {
        for(int n : numbers){
            if(binarySearch(n)) bw.write("1\n");
            else bw.write("0\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean binarySearch(int target) {
        int start = 0;
        int end = A.length-1;
        int mid = 0;

        while(start <= end){
            mid = (start+end)/2;
            if(A[mid] == target) return true;
            else if (A[mid] < target) start = mid + 1;
            else end = mid - 1;
        }
        return false;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        A = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        M = Integer.parseInt(bf.readLine());
        numbers = new int[M];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }

    }
}
