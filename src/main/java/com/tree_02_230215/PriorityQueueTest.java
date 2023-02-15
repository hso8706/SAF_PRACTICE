package com.tree_02_230215;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
//        PriorityQueue<Product> pq = new PriorityQueue<>(); // asc 정렬, 기본값
        PriorityQueue<Product> pq = new PriorityQueue<>(Comparator.reverseOrder());

        pq = new PriorityQueue<>(new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.price - o1.price; // 가격 기준으로 Product를 비교하겠다.
            }
        }); // 커스텀 정렬 기준 제공

        pq.offer(new Product(6, 2000));
        pq.offer(new Product(5, 1500));
        pq.offer(new Product(4, 3900));
        pq.offer(new Product(9, 4500));
        pq.offer(new Product(2, 3300));
        pq.offer(new Product(1, 4000));
        pq.offer(new Product(3, 5600));
        pq.offer(new Product(8, 7700));
        pq.offer(new Product(7, 8000));

//        for (Product p : pq){
//            System.out.println(p); // Heap 자료 구조
//        }

        while(!pq.isEmpty()){
            System.out.println(pq.poll());
        }
    }
}
