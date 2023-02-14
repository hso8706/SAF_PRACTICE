package com.LinkedList_01_230213;

public class LinkedListStack<E> implements IStack<E> {

    private Node<E> top;
    @Override
    public void push(E e) {
        // top으로 삽입
        top = new Node<E>(e, top);

    }

    @Override
    public E pop() {
        if(isEmpty()){
            System.out.println("공백스택이어서 불가능합니다.");
            return null;
        }
        Node<E> popNode = top;
        top = popNode.link;
        popNode.link = null;

        return popNode.data;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public int size() {
        return 0;
    }
}
