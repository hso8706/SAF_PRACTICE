package aSAF.tree_02_230215;

//우선순위 큐를 쓰기 위해선 비교 가능해야한다
//Comparable 을 구현하는 것만 가능하다.
public class Product implements Comparable<Product> {
    int num, price;

    public Product(int num, int price) {
        this.num = num;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "num=" + num +
                ", price=" + price +
                '}';
    }

    //정렬기준을 가지고 있는 메소드
    @Override
    public int compareTo(Product other) {
        return this.num - other.num; //asc
    }
}
