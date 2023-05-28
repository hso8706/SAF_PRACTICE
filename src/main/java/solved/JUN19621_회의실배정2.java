package solved;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class JUN19621_회의실배정2 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // 시작 시간과 종료 시간이 있는 커스텀 클래스 작성
    // 해당 커스텀 클래스가 종료 시점으로 오름차순으로 정렬할 수 있도록 comparable 혹은 comparator 설정
    // 종료 시간이 같은 경우, 문제에 따라서 시작 시간 정렬도 필요할 수 있으니 확인할 것(예를 들면 시작하자마자 종료되는 회의가 있는 경우)
    static class Meeting implements Comparable<Meeting> {
        int start;
        int end;
        int num;

        public Meeting(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }

        @Override
        public int compareTo(Meeting m) {
            return this.end-m.end;
        }

        @Override
        public String toString() {
            return "Meeting{" +
                    "start=" + start +
                    ", end=" + end +
                    ", num=" + num +
                    '}';
        }
    }

    static int N;
    static Meeting[] meetings;
    static int maxValue;
    public static void main(String[] args) throws IOException {
        // 회의 커스텀 클래스를 참조형으로 하는 배열을 선언하고 입력받기
        N = Integer.parseInt(bf.readLine());
        meetings = new Meeting[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            meetings[i] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        // 입력받은 배열을 sort
        Arrays.sort(meetings);

        // 2번째 회의부터 순회하며 양립할 수 있는지 조건을 확인 및 가능하다면 결과 리스트에 저장
        // 조건: 저장된 리스트 가장 마지막 인덱스의 종료 시점보다 순회하는 미팅의 시작 시간이 같거나 같은 경우

        //재귀로 구현(백트래킹)
        booking(0, 0);
        System.out.println(maxValue);
    }

    private static void booking(int s, int num) {
        if(s > N-1){
//            System.out.println("=>" + num);
            maxValue = Math.max(maxValue, num);
            return;
        }

//        System.out.println(num + " " + meetings[s].num);
        booking(s+2, num+meetings[s].num);
        booking(s+1, num);
    }
}
