package aSAF.tree_01_230214;

import java.io.*;
import java.util.StringTokenizer;

public class SW1233_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {

        //height : 2^h를 통해 노드의 층 구함
        for (int tc = 1; tc < 11; tc++) {
            int N = Integer.parseInt(bf.readLine());
            String[][] inputNode = new String[N+1][3];
            int validNum = -1;

            for (int i = 1; i < N+1; i++) {
                inputNode[i] = bf.readLine().split(" ");
            }
            for (int i = 1; i < N+1; i++) {
                if(inputNode[i].length == 4 && (!inputNode[i][1].equals("+") && !inputNode[i][1].equals("-") && !inputNode[i][1].equals("*") && !inputNode[i][1].equals("/"))){
                    validNum = 0;
                    break;
                }
                if(inputNode[i].length == 2 && (inputNode[i][1].equals("+") || inputNode[i][1].equals("-") || inputNode[i][1].equals("*") || inputNode[i][1].equals("/"))){
                    validNum = 0;
                    break;
                }
                validNum = 1;
            }

            System.out.println("#"+ tc+ " " + validNum);

        }
    }
}
