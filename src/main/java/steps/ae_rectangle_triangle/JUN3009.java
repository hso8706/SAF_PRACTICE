package steps.ae_rectangle_triangle;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class JUN3009 {

    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[][] points;
    public static void main(String[] args) throws IOException {

        init();
        findAnotherPoint();
        output();
    }

    private static void output() throws IOException {
        bw.write(points[3][0] +" "+ points[3][1]);
        bw.flush();
        bw.close();
    }

    private static void findAnotherPoint() {

        //x축 찾기
        if(points[0][0] == points[1][0]){
            points[3][0] = points[2][0];
        }
        else if(points[1][0] == points[2][0]){
            points[3][0] = points[0][0];
        }
        else {
            points[3][0] = points[1][0];
        }
        //y축 찾기
        if(points[0][1] == points[1][1]){
            points[3][1] = points[2][1];
        }
        else if(points[1][1] == points[2][1]){
            points[3][1] = points[0][1];
        }
        else {
            points[3][1] = points[1][1];
        }
    }

    private static void init() throws IOException {

        points = new int[4][2];
        for(int i=0; i<3; i++){
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[i][0] = x;
            points[i][1] = y;
        }

    }

}
