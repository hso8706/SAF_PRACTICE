package solved;

import java.io.*;
import java.util.*;

public class JUN1764 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 듣보잡
    - 명단 구하기 프로그램
    - 들어보지 못한 사람, 본 적이 없는 사람
    ==> 들어보지 못한 사람, 본 적이 없는 사람, 두 그룹 모두에 속하는 사람 출력
    
    ### 문제 해결
    - 사람의 이름을 key 값으로하고 하는 맵 생성
    - 듣도 못한 사람을 받고, value에 1 카운트
    - 보도 못한 사람을 받으며 value에 누적 카운트
    - 카운트가 2인 map의 key값을 출력
    - 출력 시 정렬 필요
     */
    static int N, M; //N: 듣지 못한 사람 수, M: 보지 못한 사람 수
    static HashMap<String, Integer> nameList = new HashMap<>();
    static ArrayList<String> printList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String temp = bf.readLine();
            nameList.put(temp, 1);
        }
        for (int i = 0; i < M; i++) {
            String temp = bf.readLine();
            if (nameList.containsKey(temp)) {
//                nameList.put(temp, 2);
                printList.add(temp);
            }
        }
        Collections.sort(printList);
        bw.write(printList.size() + "\n");
        for (String str : printList) {
            bw.write(str + "\n");
        }
        bw.flush();
        bw.close();
    }
}
