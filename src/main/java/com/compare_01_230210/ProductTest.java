package com.compare_01_230210;

import java.util.ArrayList;
import java.util.Collections;

public class ProductTest {
    public static void main(String[] args) {
        ArrayList<Product> list = new ArrayList<>();
        list.add(new Product(5, 4000, "사과"));
        list.add(new Product(3, 2000, "바나나"));
        list.add(new Product(2, 4000, "참외"));
        list.add(new Product(1, 7000, "배"));
        list.add(new Product(4, 1000, "밤"));

        Collections.sort(list);
        for(Product product : list){
            System.out.println(product);
        }
        System.out.println();

        Collections.sort(list, new ProductComparator());
        for(Product product : list){
            System.out.println(product);
        }
        System.out.println();
    }
}
