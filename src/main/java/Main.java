import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int n;
    static int[] inputArr;
    static List<String> strList = new ArrayList<>();
    static Stack<Integer> stack;

    public static void main(String[] args) throws IOException {
        n = Integer.parseInt(bf.readLine());
        inputArr = new int[n+1];
        stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            inputArr[i] = Integer.parseInt(bf.readLine());
        }
        canMake();
    }

    private static void canMake() throws IOException {
        int idx = 0;
        for (int i = 1; i < n+1; i++) {
            stack.push(i);
            strList.add("+");
            while(stack.contains(inputArr[idx])){
                stack.pop();
                strList.add("-");
                idx++;
            }
        }
        if(stack.isEmpty()) {
            for (int i = 0; i < strList.size(); i++) {
                bw.write(strList.get(i) + "\n");
            }
        }
        else bw.write("NO");
        bw.flush();
        bw.close();
    }

}
