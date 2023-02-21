package exercise.prac03;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree<T> {
    private Object[] nodes;
    private final int SIZE;
    private int lastIndex;

    public Tree(int SIZE) {
        this.SIZE = SIZE;
        nodes = new Object[this.SIZE + 1];
    }

    private boolean isFull(){
        return lastIndex == SIZE;
    }

    public void add(T e){
        if(isFull()) return;
        nodes[++lastIndex] = e;
    }

    public void bfs(){
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(1);

        int current = 0;
        while(!queue.isEmpty()){
            current = queue.poll();
            //queue 요소 작업

            //왼쪽 자식 인덱스
            if(current * 2 <= lastIndex) queue.offer(current * 2);
            //오른쪽 자식 인덱스
            if(current * 2 + 1 <= lastIndex) queue.offer(current * 2 + 1);
        }
    }
}
