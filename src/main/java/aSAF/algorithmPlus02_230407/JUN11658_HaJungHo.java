package aSAF.algorithmPlus02_230407;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUN11658_HaJungHo {
    static int N,M;
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        tree=new long[N+1]; // 이진수 비트를 사용할 때, 편의를 위해서 인덱스 1부터 시작
        st=new StringTokenizer(br.readLine());

        for (int i = 1; i < N+1; i++) {
            // 초기 입력 배열을 저장하지 않고, 입력값을 받으면서 동시에 펜윅 트리를 통해 배열을 초기화하는 과정
            // 2의 배수 구간마다 합이 지정된다.
            update(i,Long.parseLong(st.nextToken()));
        }
        System.out.println(Arrays.toString(tree));
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < M; i++) {
            st=new StringTokenizer(br.readLine());
            int a=Integer.parseInt(st.nextToken());
            int b=Integer.parseInt(st.nextToken());
            long s=sum(a,b);
            sb.append(s).append("\n");
        }
        System.out.println(sb.toString());
    }
    static void update(int i, long diff) {
        while(i<N+1) {
            tree[i]+=diff;
            i+=( i & -i); // 부분 합이 저장되는 배열 인덱스를 구하기 위한 연산, 인덱스로 비트를 사용
            // (i & -i) : 우리가 아는 이진수 형태만 남게 만듬
            // 비트연산자 &: and 개념의 비트 연산자, 같은 비트 자리에 1이 있어야 1이 됨.
        }
    }
    static long sum(int i) {
        long result=0;
        while(i>0) {
            result+=tree[i];
            i-=( i & -i);
        }
        return result;
    }
    static long sum(int i, int j) {
        return sum(j)-sum(i-1);
    }
}
