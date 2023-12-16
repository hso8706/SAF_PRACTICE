package binarySearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN10816 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[] cards , numbers;
    public static void main(String[] args) throws IOException {

        init();
        isInIt();
    }

    private static void isInIt() throws IOException {
        for (int i = 0; i < M; i++) {
            bw.write((findEndIndex(numbers[i])-findStartIndex(numbers[i])+1)+" ");
        }
        bw.flush();
        bw.close();
    }

    private static int findStartIndex(int target) {
        int start = 0;
        int end = cards.length-1;
        int mid = 0;
        while(start <= end){
            mid = (start+end)/2;
            if (cards[mid]>=target) end=mid-1;
            else start = mid+1;
        }
        return start;
    }

    private static int findEndIndex(int target) {
        int start = 0;
        int end = cards.length-1;
        int mid = 0;
        while(start <= end){
            mid = (start+end)/2;
            if (cards[mid]>target) end = mid-1;
            else start=mid+1;
        }
        return end;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        cards = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(cards);
        M = Integer.parseInt(bf.readLine());
        numbers = new int[M];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
    }
}
