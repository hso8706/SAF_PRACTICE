package math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN1002_터렛 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    문제 분석

    제공: 좌표1, 좌표2, 좌표1-좌표3의 거리, 좌표2-좌표3의 거리
    목표: 좌표3이 될 수 있는 가능성의 개수를 구하라
    제한조건
    - 목표 개수가 무한대이면 -1로 출력

    해결방법1
    - 두 원이 존재한다고 생각.
    - 두 원이 같은 중심을 갖는지부터 확인
    - (두 점의 차이)와 (두 반지름 차이)를 비교
        - (두 점의 차이)
     */
    static int tc, x1, y1, d1, x2, y2, d2;

    public static void main(String[] args) throws IOException {
        tc = Integer.parseInt(bf.readLine());

        for (int i = 1; i <= tc ; i++) {
            int result;

            st = new StringTokenizer(bf.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            d1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());
            d2 = Integer.parseInt(st.nextToken());

            if(isSameCircle(x1, y1, x2, y2, d1, d2)){
                result = -1;
            }
            else result = howManyContact(x1, y1, x2, y2, d1, d2);

            bw.write(result + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static int howManyContact(int x1, int y1, int x2, int y2, int d1, int d2) {
        double r_distance = Math.pow(Math.abs(x1 - x2), 2) + Math.pow(Math.abs(y1 - y2), 2);
        double d_distance_plus = Math.pow(Math.abs(d1 + d2), 2);
        double d_distance_minus = Math.pow(Math.abs(d1 - d2), 2);

        if (r_distance > d_distance_plus || r_distance < d_distance_minus) return 0;
        else if (r_distance == d_distance_plus || r_distance == d_distance_minus) return 1;
        else return 2;
    }

    private static boolean isSameCircle(int x1, int y1, int x2, int y2, int d1, int d2) {

        return (x1 == x2) && (y1 == y2) && (d1 == d2);
    }
}
