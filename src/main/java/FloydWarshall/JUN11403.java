package FloydWarshall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN11403 {
    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N;
    static int[][] adjMatrix;
    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(bf.readLine());
        adjMatrix = new int[N+1][N+1];
        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 1; j < N + 1; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i < N+1; i++) {
                for (int j = 1; j < N+1; j++) {
                    if(adjMatrix[i][k] == 1 && adjMatrix[k][j] == 1) adjMatrix[i][j] = 1;
                }
            }
        }
        for (int i = 1; i < N+1; i++) {
            for (int j = 1; j < N+1; j++) {
                bw.write(adjMatrix[i][j] + " ");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

}
