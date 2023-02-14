package com.stackAndQueue_01_230210;

import java.util.ArrayDeque;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        //Queue 는 interface!! 구현체로 생성해야함
        Queue<String> q = new ArrayDeque<>();
        q.offer("kim");
        q.offer("lee");
        q.offer("park");
        q.offer("choi");

        System.out.println(q.size());
        System.out.println(q.poll());
        System.out.println(q.poll());

        System.out.println(q.peek());
        System.out.println(q.size());
        System.out.println(q.isEmpty());
    }
}
