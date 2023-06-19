package topologysort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class JUN2056_작업 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N; // 수행할 작업 개수
    static ArrayList<Integer>[] adjList; // 선행, 후행 관계를 갖는 리스트 배열: 배열 인덱스를 선행(from), 해당 인덱스의 리스트 값을 후행(to)으로 넣는 것이 포인트 
    static int[] inDegree; // 각 정점의 인접 차수를 저장하는 배열: 배열 인덱스를 to값으로 설정
    static int[] time; // 각 작업의 시간을 저장할 배열; 해당 문제의 추가 옵션, 위상 정렬을 실시하면 다양한 경우의 수가 나올 수 있기 때문에 이러한 특정 조건을 주어 하나의 답을 도출하도록 유도한다.

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        adjList = new ArrayList[N+1];
        inDegree = new int[N+1];
        time = new int[N+1];

        int amount, from;
        for (int i = 1; i < N+1; i++) {
            adjList[i] = new ArrayList<>();

            st= new StringTokenizer(bf.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            amount = Integer.parseInt(st.nextToken());
            if(amount != 0){
                for (int j = 0; j < amount; j++) {
                    from = Integer.parseInt(st.nextToken());
                    adjList[from].add(i);
                    inDegree[i]++;
                }
            }
        }

        // 위상 정렬 실시
        System.out.println(topologySort());
    }

    private static int topologySort() {

        int[] result = new int[N+1];

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            result[i] = time[i];
            // 인접(진입) 차수가 0이면 해당 vertex(to)를 queue 에 제공
            if(inDegree[i] == 0) queue.offer(i);
        }

        //queue 가 빌 때까지 작업
        while(!queue.isEmpty()){
            int current = queue.poll(); // to의 값이 꺼내짐

            for (int next : adjList[current]){
                result[next] = Math.max(result[next], (time[next] + result[current]));
                inDegree[next]--; //현재 조회된 노드의 다음 노드의 진입 차수
                if(inDegree[next] == 0){ //다음 노드 진입 차수를 1 감소시켰을때, 0이면 queue 넣음
                    queue.offer(next);
                }
            }

        }

        int max = 0;
        for (int i = 1; i < N+1; i++) {
            max = Math.max(max,result[i]);
        }
        return max;
    }
}
