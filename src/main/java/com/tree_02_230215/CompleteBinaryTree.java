package com.tree_02_230215;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 완전 이진 트리 : 1차원 배열 구현
public class CompleteBinaryTree<T> { // 제네릭 사용

    private Object[] nodes;
    private final int SIZE;
    private int lastIndex;

    public CompleteBinaryTree(int size) { // size == 노드의 개수
        this.SIZE = size;
        nodes = new Object[size + 1]; // 배열의 0인덱스를 쓰지 않기 때문에
    }

    // 포화 상태 여부 판단 메서드
    private boolean isFull(){
        return lastIndex == SIZE;
    }
    // 완전 이진 트리를 만드는 메서드
    // 완전 이진 트리는 값을 넣는 순서가 정해져 있으므로 인덱스 조절만 하면 된다.
    public void add(T e){
        if(isFull()) return;
        nodes[++lastIndex] = e;
    }
    
    // 완전 이진 트를 bfs 형식으로 탐색하는 메서드
    public void bfs(){
        
        // 큐에는 탐색 노드의 번호(Integer)를 저장
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 루트 노드의 번호(인덱스) 저장
        
        int current = 0;
        while(!queue.isEmpty()){
            current = queue.poll();
            System.out.println(nodes[current]);
        
            //현재 노드의 자식들을 큐에 넣어 순서를 기다리게 하기
            //완전 이진 트리 : 자식이 최대 2개
            
            //왼쪽 자식
            if(current*2 <= lastIndex) queue.offer(current*2);
            //오른쪽 자식
            if (current*2 +1 <= lastIndex) queue.offer(current*2 + 1);
        }
    }

    public void bfs2() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(1);
        int current, size, level = 0;

        while (!queue.isEmpty()) {
            size = queue.size();

            System.out.print("level " + level + " : ");

            while (--size >= 0) {
                current = queue.poll();
                System.out.print(nodes[current] + "\t");

                if (current * 2 <= lastIndex)
                    queue.offer(current * 2);

                if (current * 2 + 1 <= lastIndex)
                    queue.offer(current * 2 + 1);
            }
            System.out.println();
            ++level;
        }
    }
    // 이쁘게 뽑기 위한 메서드
    public void dfsByPreOrder(){
        System.out.print("PreOrder : ");
        dfsByPreOrder(1);
        System.out.println();
    }
    // 전위 순회 메서드; 사실상 bfs 에서 크게 바뀐 게 없음.
    // queue 를 쓰는지 stack 혹은 재귀 스택을 사용하는지를 구분하고 어떤 코드가 어떤 의미인지 파악하면됨.
    private void dfsByPreOrder(int current){
        System.out.print(nodes[current] + "\t");

        //왼쪽 자식
        if(current*2 <= lastIndex) dfsByPreOrder(current*2);
        //오른쪽 자식
        if (current*2 +1 <= lastIndex) dfsByPreOrder(current*2 + 1);
    }

    // 이쁘게 뽑기 위한 메서드
    public void dfsByInOrder(){
        System.out.print("InOrder : ");
        dfsByInOrder(1);
        System.out.println();
    }
    // 중위 순회
    private void dfsByInOrder(int current){

        //왼쪽 자식
        if(current*2 <= lastIndex) dfsByInOrder(current*2);
        System.out.print(nodes[current] + "\t");
        //오른쪽 자식
        if (current*2 +1 <= lastIndex) dfsByInOrder(current*2 + 1);
    }
    // 이쁘게 뽑기 위한 메서드
    public void dfsByPostOrder(){
        System.out.print("PostOrder : ");
        dfsByPostOrder(1);
        System.out.println();
    }
    // 중위 순회
    private void dfsByPostOrder(int current){

        //왼쪽 자식
        if(current*2 <= lastIndex) dfsByPostOrder(current*2);
        //오른쪽 자식
        if (current*2 +1 <= lastIndex) dfsByPostOrder(current*2 + 1);

        System.out.print(nodes[current] + "\t");

    }
    //bfs 로직에서 queue 를 스택으로만 바꿔도 dfs 방식으로 순회하는 것이 가능하다.
    //왜 가능한지 생각해볼 것
    public void dfs() {
        Stack<Integer> stack = new Stack<>();

        stack.push(1);
        int current, size, level = 0;

        while (!stack.isEmpty()) {
            size = stack.size();

            System.out.print("level " + level + " : ");

            while (--size >= 0) {
                current = stack.pop();
                System.out.print(nodes[current] + " ");
                // 오른쪽 자식
                if (current * 2 + 1 <= lastIndex)
                    stack.push(current * 2 + 1);
                // 왼쪽 자식
                if (current * 2 <= lastIndex)
                    stack.push(current * 2);
            }
            System.out.println();
            ++level;
        }
    }
}
