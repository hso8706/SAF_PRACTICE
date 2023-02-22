package aSAF.compare_01_230210;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        int x = o1.price - o2.price;
        if (x==0){
            x = o2.name.compareTo(o1.name);
        }
        return x;
    }
}
