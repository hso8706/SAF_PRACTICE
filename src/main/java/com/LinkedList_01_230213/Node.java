package com.LinkedList_01_230213;

public class Node<T> {

    T data;
    Node<T> link;

    public Node(T data) {
        this.data = data;
    }

    public Node(T data, Node<T> link) {
        this.data = data;
        this.link = link;
    }
    
    //디버깅 목적
    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", link=" + link +
                '}';
    }
}
