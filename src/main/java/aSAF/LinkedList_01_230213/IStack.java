package aSAF.LinkedList_01_230213;

public interface IStack<T> {

    void push(T e);

    T pop(); //pop, peek 는 저장된 값에 따라 반환타입이 달라짐.
    T peek(); //pop, peek 는 저장된 값에 따라 반환타입이 달라짐.
    boolean isEmpty();
    int size();
}
