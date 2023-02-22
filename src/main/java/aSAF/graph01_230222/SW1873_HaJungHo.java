package aSAF.graph01_230222;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW1873_HaJungHo {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T;
    static int H, W; //H: 맵의 높이, W: 맵의 너비
    static String[][] map; //map: H x W 이차원 배열
    static int N; //N: 사용자 입력 문자열 길이
    static String[] userInput;

    static class Tank {
        static int x;
        static int y;
    }

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(bf.readLine());
        for (int tc = 1; tc < T + 1; tc++) {
            //초기화 파트
            st = new StringTokenizer(bf.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            map = new String[H][W];
            for (int i = 0; i < H; i++) {
                String[] temp = bf.readLine().split("");
                for (int j = 0; j < W; j++) {
                    map[i][j] = temp[j];
                    if (temp[j].equals("^") || temp[j].equals("v") || temp[j].equals("<") || temp[j].equals(">")) {
                        Tank.x = i;
                        Tank.y = j;
                    }
                }
            }
            N = Integer.parseInt(bf.readLine());
            userInput = new String[N];
            userInput = bf.readLine().split("");
            //입력 확인
//            for (int i = 0; i < H; i++) {
//                System.out.println(Arrays.toString(map[i]));
//            }
//            System.out.println(Arrays.toString(userInput));
            bw.write("#" + tc + " ");
            getStarted();
            for (int i = 0; i < H; i++) {
                for (int j = 0; j <W; j++) {
                    bw.write(map[i][j] + "");
                }
                bw.write("\n");
            }
        }
        bw.flush();
        bw.close();
    }

    private static void getStarted() {
        for (int i = 0; i < N; i++) {
            if (userInput[i].equals("S")) {
                shooting();
            } else if (userInput[i].equals("U")) {
                map[Tank.x][Tank.y] = "^";
                if ((Tank.x - 1) >= 0 && map[Tank.x-1][Tank.y].equals(".")) {
                    map[Tank.x][Tank.y] = ".";
                    Tank.x -= 1;
                    map[Tank.x][Tank.y] = "^";
                }
            } else if (userInput[i].equals("D")) {
                map[Tank.x][Tank.y] = "v";
                if ((Tank.x + 1) <= H-1 && map[Tank.x+1][Tank.y].equals(".")) {
                    map[Tank.x][Tank.y] = ".";
                    Tank.x += 1;
                    map[Tank.x][Tank.y] = "v";
                }
            } else if (userInput[i].equals("L")) {
                map[Tank.x][Tank.y] = "<";
                if ((Tank.y - 1) >= 0 && map[Tank.x][Tank.y-1].equals(".")) {
                    map[Tank.x][Tank.y] = ".";
                    Tank.y -= 1;
                    map[Tank.x][Tank.y] = "<";
                }
            } else if (userInput[i].equals("R")) {
                map[Tank.x][Tank.y] = ">";
                if ((Tank.y + 1) <= W-1 && map[Tank.x][Tank.y+1].equals(".")) {
                    map[Tank.x][Tank.y] = ".";
                    Tank.y += 1;
                    map[Tank.x][Tank.y] = ">";
                }
            }
        }
    }
    private static void shooting() {
        int x = Tank.x;
        int y = Tank.y;
        switch (map[Tank.x][Tank.y]) {
            case "^":
                x -= 1;
                while (x >= 0) {
                    if (map[x][y].equals("*")) {
                        map[x][y] = ".";
                        break;
                    }
                    else if(map[x][y].equals("#")) break;
                    x -= 1;
                }
                break;
            case "v":
                x += 1;
                while (x <= H - 1) {
                    if (map[x][y].equals("*")) {
                        map[x][y] = ".";
                        break;
                    }
                    else if(map[x][y].equals("#")) break;
                    x += 1;
                }
                break;
            case "<":
                y -= 1;
                while (y >= 0) {
                    if (map[x][y].equals("*")) {
                        map[x][y] = ".";
                        break;
                    }
                    else if(map[x][y].equals("#")) break;
                    y -= 1;
                }
                break;
            case ">":
                y += 1;
                while (y <= W - 1) {
                    if (map[x][y].equals("*")) {
                        map[x][y] = ".";
                        break;
                    }
                    else if(map[x][y].equals("#")) break;
                    y += 1;
                }
                break;
        }
    }
}
