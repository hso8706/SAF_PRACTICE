package aSAF.MST_230228;

//프림 알고리즘 : 정점 중심으로 MST를 만들어가는 알고리즘

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class PrimTest {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br = new BufferedReader(new StringReader(src));

        int N = Integer.parseInt(br.readLine().trim()); //정점 개수 == 5
        int[][] map = new int[N][N];//인접 행렬

        boolean[] visited = new boolean[N];//해당 정점이 MST에 포함되었는지 체크
        int[] minEdge = new int[N];//한 정점과 연결된 다른 간선들의 비용 중 최소값
        //예시. minEdge[3] = 5; 정점3과 연결된 여러 간선 비용 중 최소값
        
        //인접 행렬 값
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
            minEdge[i] = Integer.MAX_VALUE;
        }
//        System.out.println(Arrays.toString(minEdge));
        //시작 정점 정하기
        minEdge[0] = 0;
        int minVertex, minCost, total = 0;
//        System.out.println(Arrays.toString(minEdge));

        for (int i = 0; i < N; i++) { // 모든 정점에 대해
            minCost = Integer.MAX_VALUE;
            minVertex = 0;

            for (int j = 0; j < N; j++) {
                //1. MST에 포함되지 않는 정점 중에서 minEdge배열에 들어있는 값과 기존 최소값과 비교해서 더 작은 값 결정
                if(!visited[j] && minCost > minEdge[j]){
                    minCost = minEdge[j];//0
                    minVertex = j;//0
                }
            }
//            System.out.println(i + ": 최소 정점-" + minVertex + ", 최소비용-" + minCost);
            
            total += minCost;// 누적 비용
            visited[minVertex] = true;//방문 처리. MST에 포함시킴
            
            //(MST에 속하지 않는 정점 중) 자신이 가지고 있는 최소 비용과 방금 결정된 최소 비용과 비교해서 업데이트
            for (int k = 0; k < N; k++) {
                //반복문
                //1.방문 여부 확인 && 2.길 존재 여부 확인 && 3.현재 선택된 간선이 최소 비용 간선인지 확인
                if(!visited[k] && map[minVertex][k] != 0 && minEdge[k] > map[minVertex][k]){
                    minEdge[k] = map[minVertex][k];
                }
            }
//            System.out.println(Arrays.toString(minEdge));
        }
        System.out.println(total);
    }

    static String src = "5\r\n"+
            "0 5 10 8 7 \r\n"+
            "5 0 5 3 6 \r\n"+
            "10 5 0 1 3 \r\n"+
            "8 3 1 0 1 \r\n"+
            "7 6 3 1 0\r\n";
}
