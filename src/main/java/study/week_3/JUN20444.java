package study.week_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN20444 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 가로(x), 세로(y) => (x+1)*(y+1)개
    - 근데 조건이 너무 큼 => 이분 탐색 고
     */
    static long n, k;
    public static void main(String[] args) throws IOException {
        init();
        binarySearch(k);
        bw.flush();
        bw.close();
    }

    private static void binarySearch(long target) throws IOException {
        long low = 0;
        long high = n;

        while(low<=high){
            long mid = (low+high)/2;
            long x = mid;
            long y = n-mid;
            long total = (x+1)*(y+1);
            if(total == target) {
                bw.write("YES");
                return;
            }
            else if(total > target){
                high = mid - 1;
            }
            else {
                low = mid + 1;
            }
        }
        bw.write("NO");
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        n = Long.parseLong(st.nextToken());
        k = Long.parseLong(st.nextToken());

    }
}
