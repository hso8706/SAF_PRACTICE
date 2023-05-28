package implementation_and_bruteForce;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class JUN5014_스타트링크 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    - 총 F층
    - 현재 S층
    - 링크 G층
    - 엘베 버튼: U, D
    - 입력 : F,S,G,U,D
    - 출력 : S => G로 가기 위해 눌러야하는 버튼의 최솟값

    - 입력받기
    - 횟수의 최솟값이므로 BFS를 활용
    - 현재 층이 목표층보다 작으면 U, 현재 층이 목표층보다 높으면 D
    - 현재 층이 목표층에 도달했을때 cnt를 String으로 반환
    - 도달하지 못하는 경우에는 지정 문자열 반환
     */

    static int F, S, G, U, D;
    static int[] df = new int[2];
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        df[0] = U;
        df[1] = -D;
        visited = new boolean[F+1];

        if((U==0 && S<G) || (D==0 && S>G)){
            System.out.println("use the stairs");
        }
        else System.out.println(bfs());
    }

    private static String bfs() {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{S, 0});
        visited[S] = true;

        while (!queue.isEmpty()){
            int[] current = queue.poll();
            int cf = current[0];
            int cc = current[1];
            if(cf == G){
                return String.valueOf(cc);
            }

            for (int i = 0; i < 2; i++) {
                int nf = cf + df[i];
                if(nf<=0 || nf>F) continue;
                if(!visited[nf]) {
                    visited[nf] = true;
                    queue.offer(new int[]{ nf, cc + 1 });
                }
            }
        }

        return "use the stairs";
    }
}
