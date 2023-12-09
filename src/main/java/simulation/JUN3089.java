package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class JUN3089 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    static int N, M;
    static TreeSet<Integer>[] xSet;
    static TreeSet<Integer>[] ySet;
    static int[] orders;
    public static void main(String[] args) throws IOException {
        init();
        execute();
    }

    private static void execute() throws IOException {
        int x = 100_000;
        int y = 100_000;
        for(int d : orders){
            if(d==0){
                y = xSet[x].higher(y);
            }
            else if (d==1){
                x= ySet[y].higher(x);
            }
            else if (d==2){
                y = xSet[x].lower(y);
            }
            else {
                x = ySet[y].lower(x);
            }
        }
        bw.write((x-100_000)+" "+(y-100_000));
        bw.flush();
        bw.close();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        xSet = new TreeSet[200_000];
        ySet = new TreeSet[200_000];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int x = Integer.parseInt(st.nextToken())+100_000;
            int y = Integer.parseInt(st.nextToken())+100_000;
            if(xSet[x] == null){
                xSet[x] = new TreeSet<>();
            }
            if(ySet[y] == null){
                ySet[y] = new TreeSet<>();
            }
            xSet[x].add(y);
            ySet[y].add(x);
        }
        char[] temp = bf.readLine().toCharArray();
        orders = new int[M];
        for (int i = 0; i < M; i++) {
            if(temp[i]=='U') orders[i]=0;
            else if(temp[i]=='R') orders[i]=1;
            else if(temp[i]=='D') orders[i]=2;
            else orders[i]=3;
        }
    }
}
