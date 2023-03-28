package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN10845 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### 큐 구현
     */

    static int N; // 명령어 개수
    static ArrayList<Integer> queue = new ArrayList<>();// 스택 구현 리스트
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            queueFunction(st);
        }
        bw.flush();
        bw.close();
    }

    private static void queueFunction(StringTokenizer st) throws IOException {
        String command = "";
        int pushNum = 0;
        command = st.nextToken();

        switch (command){
            case "push":
                pushNum = Integer.parseInt(st.nextToken());
                queue.add(pushNum);
                break;
            case "pop":
                if(queue.isEmpty()) bw.write(-1 + "\n");
                else {
                    bw.write(queue.get(0)+"\n");
                    queue.remove(0);
                }
                break;
            case "size":
                bw.write(queue.size()+"\n");
                break;
            case "empty":
                if (queue.isEmpty()) bw.write(1 + "\n");
                else bw.write(0 + "\n");
                break;
            case "front":
                if(queue.isEmpty()) bw.write(-1 + "\n");
                else bw.write(queue.get(0)+"\n");
                break;
            case "back":
                if(queue.isEmpty()) bw.write(-1 + "\n");
                else bw.write(queue.get(queue.size()-1)+"\n");
                break;

        }
    }
}