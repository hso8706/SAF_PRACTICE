package simulation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class JUN1063 {
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    /*
    - 왕: 1
    - 돌: 2
     */
    static int[][] board;
    static Map<String, Integer> boardIdx;
    static Map<String, Integer> moveIdx;
    static String[] transY = new String[]{"","A","B","C","D","E","F","G","H"};
    static int[] transX = new int[]{0,8,7,6,5,4,3,2,1};
    static int[] dx = new int[]{0,0,1,-1,-1,-1,1,1};
    static int[] dy = new int[]{1,-1,0,0,1,-1,1,-1};
    static int N;
    static int kx,ky,sx,sy;

    public static void main(String[] args) throws IOException {

        init();
        moveTheKing();
        System.out.println(transY[ky]+""+transX[kx]+"\n"+transY[sy]+""+transX[sx]);
    }

    private static void moveTheKing() throws IOException {
        for (int i = 0; i < N; i++) {
            int order = moveIdx.get(bf.readLine());

            int nkx = kx + dx[order];
            int nky = ky + dy[order];
            if(nkx < 1||nky<1||nkx>=9||nky>=9) continue;
            if(nkx == sx && nky == sy){
                int nsx = sx + dx[order];
                int nsy = sy + dy[order];
                if(nsx<1||nsy<1||nsx>=9||nsy>=9) continue;
                sx = nsx;
                sy = nsy;
            }
            kx = nkx;
            ky = nky;
        }
    }

    private static void init() throws IOException {

        board = new int[9][9];
        boardIdx = new HashMap<>();
        boardIdx.put("A", 1);boardIdx.put("1", 8);
        boardIdx.put("B", 2);boardIdx.put("2", 7);
        boardIdx.put("C", 3);boardIdx.put("3", 6);
        boardIdx.put("D", 4);boardIdx.put("4", 5);
        boardIdx.put("E", 5);boardIdx.put("5", 4);
        boardIdx.put("F", 6);boardIdx.put("6", 3);
        boardIdx.put("G", 7);boardIdx.put("7", 2);
        boardIdx.put("H", 8);boardIdx.put("8", 1);
        moveIdx = new HashMap<>();
        moveIdx.put("R",0);
        moveIdx.put("L",1);
        moveIdx.put("B",2);
        moveIdx.put("T",3);
        moveIdx.put("RT",4);
        moveIdx.put("LT",5);
        moveIdx.put("RB",6);
        moveIdx.put("LB",7);

        st = new StringTokenizer(bf.readLine());
        int who = 1;
        for (int i = 0; i < 2; i++) {
            String[] key = st.nextToken().split("");
            int y = boardIdx.get(key[0]);
            int x = boardIdx.get(key[1]);
            board[x][y] = who;
            if(who==1) {
                kx = x;
                ky = y;
            }
            else {
                sx = x;
                sy = y;
            }
            who++;
        }
        N = Integer.parseInt(st.nextToken());
    }
}
