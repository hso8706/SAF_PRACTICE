package com.tree_01_230214;

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
    private boolean isFull() {
        return lastIndex == SIZE;
    }

    // 완전 이진 트리를 만드는 메서드
    // 완전 이진 트리는 값을 넣는 순서가 정해져 있으므로 인덱스 조절만 하면 된다.
    public void add(T e) {
        if (isFull()) return;
        nodes[++lastIndex] = e;
    }

    // 완전 이진 트를 bfs 형식으로 탐색하는 메서드
    public void bfs() {

        // 큐에는 탐색 노드의 번호(Integer)를 저장
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 루트 노드의 번호(인덱스) 저장

        int current = 0;
        while (!queue.isEmpty()) {
            current = queue.poll();
            System.out.println(nodes[current]);

            //현재 노드의 자식들을 큐에 넣어 순서를 기다리게 하기
            //완전 이진 트리 : 자식이 최대 2개

            //왼쪽 자식
            if (current * 2 <= lastIndex) queue.offer(current * 2);
            //오른쪽 자식
            if (current * 2 + 1 <= lastIndex) queue.offer(current * 2 + 1);
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
}

