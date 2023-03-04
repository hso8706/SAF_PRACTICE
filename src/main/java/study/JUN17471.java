package study;

import java.io.*;
import java.util.*;

public class JUN17471 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 게리맨더링
    - 문제 이해
    1. 선거구를 나눈다(== 그래프를 이분한다)
        - 나뉜 서브 그래프는 연결(인접)되어 있어야 한다.
    2. 두 개의 선거구의 각 구역 번호(인구수)의 합을 각각 구한다(== 각 서브 그래프의 정점의 합)
    3. 2에서 구한 합들의 차이가 최소인 경우일 때의 합의 차이 값을 출력한다.

    - 문제 해결
    1. 그래프를 두 그룹으로 나누기
        - 부분 집합 사용
        - 뽑힌 그룹(red), 뽑히지 않은 그룹(blue)
    2. 나눠진 그룹이 가능한 방법(연결 여부)인지 확인
        - bfs 혹은 dfs
        - 연결 입력 자료 사용
    3. 그룹의 인구 합 차이 확인 및 갱신
        - 1,2번이 만족됐을 경우에 합을 확인
        - min 값보다 합이 작으면 갱신
     */
    static int N; // 구역의 개수
    static int[] population; // 인구 수
    static HashMap<Integer, ArrayList<Integer>> section; // 구역 연결 정보 || key: Index, value: list=>인접 정보

//    static int[] selected;
    static List<Integer> selected; // 가변적으로 사용해야함.
//    static int[] notSelected;
    static List<Integer> notSelected; // 가변적으로 사용해야함.
    static boolean[] isSelected; // 부분 집합용
    static boolean[] visited; // bfs 용
    static int min; // 합의 최소를 담는 변수

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        population = new int[N+1]; // 인덱스 1부터 관리
        section = new HashMap<>();

        st = new StringTokenizer(bf.readLine()); // 1 ~ N 구역의 인구수(== 구역의 번호)
        for (int i = 1; i < N+1; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N+1; i++) {
            st = new StringTokenizer(bf.readLine()); // 각 구역의 인접 정보
            section.put(i, new ArrayList<>()); // 각 구역(인덱스)마다 저장할 리스트 생성
            int num = Integer.parseInt(st.nextToken()); //인접 정보 1번: 구역 개수
            for (int j = 0; j < num; j++) {// 인접 구역 개수만큼 반복
                section.get(i).add(Integer.parseInt(st.nextToken()));//인접 정보 2~n번: 각 구역 인덱스
            }
        }
//        for (int i = 1; i < N+1; i++) { // 인접 정보 입력 확인
//            bw.write(section.get(i) + "\n");
//        }
        isSelected = new boolean[N+1];
        min = Integer.MAX_VALUE;

        separate(1); // 1번 인덱스부터 부분 집합 나누기

        if(min == Integer.MAX_VALUE) bw.write(-1 + ""); // 선거구가 올바르게 나눠지지 못한 경우
        else bw.write(min+"");
        bw.flush();
        bw.close();

    }

    private static void separate(int cnt) {
        if (cnt == N+1){ // 기저 조건
            selected = new ArrayList<>(); // 선택된 리스트 선언
            notSelected = new ArrayList<>(); // 선택되지 않은 리스트 선언

            for (int i = 1; i < N+1; i++) { // 선택, 비선택 그룹 초기화
                if(isSelected[i]) selected.add(i);
                else notSelected.add(i);
            }

            //0집합 혹은 전체 요소 제외
            if(selected.size() == 0 || selected.size() == N) return;

            visited = new boolean[N+1]; // 두 그룹이 나눠지는 순간마다 방문 배열 초기화
            // 각 그룹의 연결 여부 확인
            bfs(selected); //selected 리스트에 존재하는 인덱스를 true 로 변경
            bfs(notSelected); //notSelected 리스트에 존재하는 인덱스를 true 로 변경

            // 1. 연결 안 된 상황 => 종료
            for (int i = 1; i < N+1; i++) {
                if(!visited[i]) return; //true 가 아닌 것이 있다 == 연결이 안 된 구역이 있다.
            }
            // 2. 연결 된 상황 => 합의 차이 구하기
            int sumA = 0;
            int sumB = 0;
            for(int n : selected){
                sumA += population[n];
            }
            for(int n : notSelected){
                sumB += population[n];
            }
            if (min > Math.abs(sumA - sumB)) min = Math.abs(sumA - sumB); // min 값 갱신

            return;
        }

        // 부분 집합 나누는 핵심 코드
        isSelected[cnt] = true;
        separate(cnt+1);
        isSelected[cnt] = false;
        separate(cnt+1); // 2개의 재귀 호출이 반복문과 비슷한 느낌을 줌
    }

    private static void bfs(List<Integer> list) {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(list.get(0));
        visited[list.get(0)] = true;

        while(!queue.isEmpty()){
            int current = queue.poll();

            for (int idx : section.get(current)){
                if(!visited[idx] && list.contains(idx)){
                    queue.offer(idx);
                    visited[idx] = true;
                }
            }
        }


    }
}
