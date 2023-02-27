package aSAF.disJoinSet_230227;

import java.util.Arrays;

//유니온 작업
public class DisJoinSetTest {
    static int N; // 정점 수
    static int[] parents; // 대표자 배열

    public static void main(String[] args) {
        N = 5;
        parents = new int[5];

        //서로소 집합 세가지 작업
        //1. makeSet
        makeSet();
        System.out.println(Arrays.toString(parents));
        //2. union(a,b): 두 개의 정점(대표자 이용)을 합치는 작업
        System.out.println(canUnion(0,1));
        System.out.println(Arrays.toString(parents));
        System.out.println(canUnion(1,2 ));
        System.out.println(Arrays.toString(parents));
        System.out.println(canUnion(3, 4));
        System.out.println(Arrays.toString(parents));
        System.out.println(canUnion(0, 2));
        System.out.println(Arrays.toString(parents));
        System.out.println(canUnion(0, 4));
        System.out.println(Arrays.toString(parents));

        System.out.println("-------------------------------------");
        System.out.println(find(4));
        System.out.println(Arrays.toString(parents));

        System.out.println(find(3));
        System.out.println(Arrays.toString(parents));
        System.out.println(find(2));
        System.out.println(Arrays.toString(parents));
        System.out.println(find(0));
        System.out.println(Arrays.toString(parents));
        System.out.println(find(1));
        System.out.println(Arrays.toString(parents));
        System.out.println(find(4));
        System.out.println(Arrays.toString(parents));
    }
    static boolean findCycle(int a, int b){
        int x = find(a);
        int y = find(b);

        if(x == y)
            return true;
        else{
            canUnion(a, b);
            return false;
        }
    }

    //기존 union을 canUnion으로 수정
    private static boolean canUnion(int a, int b) {
        int aroot = find(a);
        int broot = find(b);

        if(aroot == broot)
            return false;
        parents[broot] = aroot;
        return true;
    }

    private static void union(int a, int b){
        int aroot = find(a);
        int broot = find(b);

        if (aroot < broot){
            parents[broot] = aroot;
        }
        else parents[aroot] = broot;
    }

    //특정 정점 a의 대표자를 찾아 리턴해주는 메소드
    private static int find(int a) {
        if(parents[a] == a)//자기가 대표자
            return a;

//        return find(parents[a]); // 못찾으면 부모의 대표자 찾기(재귀 호출)
        return parents[a] = find(parents[a]);

    }


    //각 정점별 대표자 정보를 넣는 초기화 작업
    private static void makeSet() {
        for (int i = 0; i < N; i++) {
            parents[i] = i; 
        }
    }
}
