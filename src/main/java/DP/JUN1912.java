package DP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1912 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int n;
    static int[] nums;
    public static void main(String[] args) throws IOException {

        n = Integer.parseInt(bf.readLine());
        nums = new int[n+1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i < n+1; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int maxSum = nums[1];
        int currentSum = nums[1];

        for (int i = 2; i < n+1; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }

        System.out.println(maxSum);

    }

}
