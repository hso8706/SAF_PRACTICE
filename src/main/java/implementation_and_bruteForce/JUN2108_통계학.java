package implementation_and_bruteForce;

import java.io.*;
import java.util.*;

public class JUN2108_통계학 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 통계학
    - 산술평균, 중앙값, 최빈값, 범위
     */
    static int N;
    static int[] input;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        input = new int[N];
        for (int i = 0; i < N; i++) {
            input[i] = Integer.parseInt(bf.readLine());
        }
        Arrays.sort(input);
        int result1 = mean();
        int result2 = median();
        int result3 = mode();
        int result4 = range();

        bw.write(result1+"\n");
        bw.write(result2+"\n");
        bw.write(result3+"\n");
        bw.write(result4+"\n");
        bw.flush();
        bw.close();
    }

    private static int range() {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        for (int i : input){
            maxVal = Math.max(maxVal, i);
            minVal = Math.min(minVal, i);
        }
        return maxVal-minVal;
    }

    private static int mode() {
        if(N == 1) return input[0];

        HashMap<Integer, Integer> temp = new HashMap<>();
        int maxVal = 1;

        for (int i = 0; i < N; i++) {
            if(temp.containsKey(input[i])) {
                int newVal = temp.get(input[i])+1;
                temp.put(input[i], newVal);

                maxVal = Math.max(maxVal, newVal);
            }
            else temp.put(input[i], 1);
        }
        ArrayList<Integer> forSort = new ArrayList<>();
        for (int key : temp.keySet()) {
            if(temp.get(key).equals(maxVal)){
                forSort.add(key);
            }
        }
        if(forSort.size() == 1) return forSort.get(0);
        else {
            Collections.sort(forSort);
            return forSort.get(1);
        }
    }

    private static int median() {
        int[] temp = new int[N];
        for (int i = 0; i < N; i++) {
            temp[i] = input[i];
        }
        return temp[N/2];
    }

    private static int mean() {
        int sum = 0;
        for (int i = 0; i < N; i++) {
            sum += input[i];
        }
        return Math.round((float) sum/N);
    }
}
