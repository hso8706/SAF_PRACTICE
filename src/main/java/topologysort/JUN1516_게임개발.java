package topologysort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN1516_게임개발 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N;
    static int[] inDegree;
    static ArrayList<Integer>[] adjList;
    static int[] time;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        inDegree = new int[N+1];
        time = new int[N+1];
        adjList = new ArrayList[N+1];
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken()); // 1번 시간
            int check = Integer.parseInt(st.nextToken());//2번 체크
            while(check != -1){
                int from = check;
                adjList[from].add(i);
                inDegree[i]++;
                check = Integer.parseInt(st.nextToken()); // 갱신
            }
        }

        int[] result = topologysort();
        for (int i = 1; i < N+1; i++) {
            bw.write(result[i]+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static int[] topologysort() {
        int[] rTime = new int[N+1];

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            rTime[i] = time[i];
            if(inDegree[i] == 0) queue.offer(i);
        }

        while(!queue.isEmpty()){
            int current = queue.poll();

            for(int next : adjList[current]){
                //위상 정렬
                inDegree[next]--;
                if(inDegree[next]==0) queue.offer(next);

                //시간 계산
                rTime[next] = Math.max(rTime[next], (rTime[current] + time[next]));
            }
        }

        return rTime;
    }
}
