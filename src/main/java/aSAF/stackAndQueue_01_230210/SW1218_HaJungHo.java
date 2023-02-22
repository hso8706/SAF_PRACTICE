package aSAF.stackAndQueue_01_230210;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class SW1218_HaJungHo {
    static int validation;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = 10;

        for (int tc = 1; tc < T + 1; tc++) {
            validation = -1;

            st = new StringTokenizer(bf.readLine());
            int len = Integer.parseInt(st.nextToken());
            String[] input = new String[len];
            input = bf.readLine().split("");
            Stack<String> stack = new Stack<>();

            label: for (String s : input) {
                if (s.equals("(") || s.equals("{") || s.equals("[") || s.equals("<") ) {
                    stack.push(s);
                }
                else {
                    if (stack.isEmpty()) {
                        validation = 0;
                        break;
                    }
                    switch (s) {
                        case ")":
                            if (!stack.pop().equals("(")){
                                validation = 0;
                                break label;
                            }
                            break;
                        case "}":
                            if (!stack.pop().equals("{")) {
                                validation = 0;
                                break label;
                            }
                            break;
                        case "]":
                            if (!stack.pop().equals("[")) {
                                validation = 0;
                                break label;
                            }
                            break;
                        case ">":
                            if (!stack.pop().equals("<")) {
                                validation = 0;
                                break label;
                            }
                            break;
                        default:
                            stack.pop();
                            validation = 1;
                            break;
                    }
                }
            }
            if (!stack.isEmpty()) {
                validation = 0;
                bw.write("#" + tc + " " + validation + "\n");
            } else {
                validation = 1;
                bw.write("#" + tc + " " + validation + "\n");
            }
            bw.flush();
        }
        bw.close();
    }
}
