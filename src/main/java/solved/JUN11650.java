package solved;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN11650 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 좌표 정렬하기
    - x 좌표가 오름차순으로 증가하는 순서로 정렬
    - x 좌표가 같다면 y 좌표가 오름차순으로 증가하는 순서로 정렬
    => Comparable, Comparator 사용
     */
    static class Point implements Comparable<Point>{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if(this.x == o.x){ // x 좌표가 같은 경우
                return this.y - o.y; // y 좌표 오름차순
            }
            return this.x - o.x; // x 좌표 오름차순(기본값)
        }
    }
    static int N;
    static Point[] pArr;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        pArr = new Point[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            pArr[i] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(pArr);
        for(Point p : pArr){
            bw.write(p.x + " " + p.y + "\n");
        }
        bw.flush();
        bw.close();
    }
}
