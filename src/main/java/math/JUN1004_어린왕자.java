package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN1004_어린왕자 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    해결방법 1.

    - 몇 개의 행성계가 출발점, 도착점을 포함하는지 개수 파악
    - (출발점-원 중심지)가 반지름보다 작은 행성계만 파악
     */
    static class Planet {
        int cx;
        int cy;
        int r;

        public Planet(int cx, int cy, int r) {
            this.cx = cx;
            this.cy = cy;
            this.r = r;
        }
    }
    static Planet[] systems;

    public static void main(String[] args) throws IOException {


        int tc = Integer.parseInt(bf.readLine());
        int x1, y1, x2, y2;
        int n;
        int result;

        for (int i = 1; i <= tc ; i++) {
            //결과값 초기화
            result = 0;

            //출발점, 도착점 입력
            st = new StringTokenizer(bf.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            //행성계 입력
            n = Integer.parseInt(bf.readLine());
            systems = new Planet[n+1];
            for (int j = 1; j <= n; j++) {
                st = new StringTokenizer(bf.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());
                systems[j] = new Planet(cx, cy, r);
            }

            //행성계 포함여부 파악
            for(Planet p : systems) {
                if(p == null) continue;
                if(isContained(x1,y1,p) && isContained(x2, y2, p)) continue;
                else if(isContained(x1,y1,p)) result++;
                else if(isContained(x2,y2,p)) result++;
            }

            bw.write(result+"\n");

        }

        bw.flush();
        bw.close();

    }

    private static boolean isContained(int x, int y, Planet p) {

        double d_pow = Math.pow((x - p.cx), 2) + Math.pow((y - p.cy), 2);
        double r_pow = Math.pow(p.r, 2);

        return d_pow < r_pow;

    }

}
