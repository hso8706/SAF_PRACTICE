    package aSAF.DynamicProgramming5_230403;

    import java.io.*;
    import java.util.*;

    public class JUN9205_HaJungHo {
        static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        static StringTokenizer st;
        /*
        ### 맥주 마시면서 걸어가기
        - 좌표와 인접리스트 bfs로 문제 해결
        - 좌표를 배열에 저장하고 좌표끼리의 거리가 1000 이하인 인덱스를 인접리스트로 설정
        - 인접리스트 bfs를 실행
        - bfs 실행 중 락페 지점에 도착하면 종료 후 happy 반환
         */
        static int t, n; // t: tc, n: 편의점 개수
        static class Spot{
            int x;
            int y;

            public Spot(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
        static Spot[] spots;
        static ArrayList<Integer>[] adjList;
        static boolean[]visited;

        public static void main(String[] args) throws IOException {
            t = Integer.parseInt(bf.readLine());
            for (int tc = 1; tc <= t; tc++) {
                n = Integer.parseInt(bf.readLine());
                spots = new Spot[n+2];
                adjList = new ArrayList[n+2];
                visited = new boolean[n+2];
                inputSpot();
                String status = AreYouHappy_bfs(0);
                bw.write(status + "\n");
            }
            bw.flush();
            bw.close();
        }

        private static String AreYouHappy_bfs(int start) {
            Queue<Integer> queue = new ArrayDeque();
            queue.offer(start);
            visited[start] = true;

            while(!queue.isEmpty()){
                int current = queue.poll();
                if(current == n+1) return "happy";

                for (int vertex : adjList[current]){
                    if(!visited[vertex]){
                        visited[vertex] = true;
                        queue.offer(vertex);
                    }
                }
            }
            return "sad";
        }

        private static void inputSpot() throws IOException {
            for (int i = 0; i < n+2; i++) {
                adjList[i] = new ArrayList<>();
                st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                spots[i] = new Spot(x, y);
            }
            for (int i = 0; i < n+2; i++) {
                for (int j = i; j < n+2; j++) {
                    int dist = Math.abs(spots[i].x - spots[j].x) + Math.abs(spots[i].y - spots[j].y);
                    if(dist <= 1000){
                        adjList[i].add(j);
                        adjList[j].add(i);
                    }
                }
            }
        }
    }
