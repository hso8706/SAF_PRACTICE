package solved;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class JUN10828 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    /*
    ### stack 구현 문제
    -
     */
    static int N; // 명령어 개수
    static ArrayList<Integer> stack = new ArrayList<>();// 스택 구현 리스트
    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bf.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            stackFunction(st);
        }
        bw.flush();
        bw.close();
    }

    private static void stackFunction(StringTokenizer st) throws IOException {
        String command = "";
        int pushNum = 0;
        command = st.nextToken();

        switch (command){
            case "push":
                pushNum = Integer.parseInt(st.nextToken());
                stack.add(pushNum);
                break;
            case "pop":
                if(stack.isEmpty()) bw.write(-1 + "\n");
                else {
                    bw.write(stack.get(stack.size()-1)+"\n");
                    stack.remove(stack.size()-1);
                }
                break;
            case "size":
                bw.write(stack.size()+"\n");
                break;
            case "empty":
                if (stack.isEmpty()) bw.write(1 + "\n");
                else bw.write(0 + "\n");
                break;
            case "top":
                if(stack.isEmpty()) bw.write(-1 + "\n");
                else bw.write(stack.get(stack.size()-1)+"\n");
                break;

        }
    }
}
