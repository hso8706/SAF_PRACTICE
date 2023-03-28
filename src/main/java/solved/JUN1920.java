package solved;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN1920 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    /*
    ### 수 찾기
    - 자연수 입력값 보니까 이중 반복문이면 바로 터질 각
    - N개의 정수 중 정수 하나 X를 구하기
    => 이분 탐색
    - 반으로 나누며 탐색
    - 정렬 필.수.
     */
    static int N, M; // N: 존재하는 정수 개수, M: 확인할 정수 개수
//    static int[] nArray, mArray;
    static int[] nArray;
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        nArray = new int[N];
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nArray[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(nArray);
        M = Integer.parseInt(bf.readLine());
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
//            mArray[i] = Integer.parseInt(st.nextToken());
            int key = Integer.parseInt(st.nextToken());
            if(binarySearch(key)) bw.write(1+"\n");
            else bw.write(0+"\n");
        }
        bw.flush();
        bw.close();
    }

    private static boolean binarySearch(int key) {
        int start = 0; // key 를 찾아나가며 계속 갱신될 시작점
        int end = nArray.length - 1; // key 를 찾아나가며 계속 갱신될 종료점
        int mid = 0; // 시작점이 달라짐에 따라 갱신될 중간점

        while(start <= end){
            mid = (start + end) / 2;
            if(nArray[mid] == key) // key 값 찾음; 중간 지점이 key 가 되도록 반복문 유도
                return true;
            else if(nArray[mid] < key) // 중간점보다 key 값이 큰 경우
                start = mid + 1; // 시작점을 중간점 바로 다음으로 설정
            else // 중간점보다 key 값이 작은 경우
                end = mid - 1; // 종료지점을 중간점 바로 이전으로 설정
        }
        return false; // 마지막 구간까지 값을 찾지 못하는 경우
    }

}
