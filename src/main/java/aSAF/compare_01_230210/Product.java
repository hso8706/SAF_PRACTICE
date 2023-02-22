package aSAF.compare_01_230210;

//1. 기본 정렬 : num 기준 정렬
//2. ProductPriceComparator.java : 가격 기준으로 오름차순 정렬
    //가격이 같으면 이름 내림차순 정렬
//3. ProductTest.java (main)
    //ArrayList 안에 Product 생성 후 add
    //정렬 2가지 모두 확인

public class Product  implements Comparable<Product>{
    int num;
    int price;
    String name;

    @Override
    public String toString() {
        return "Product{" +
                "num=" + num +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }

    public Product(int num, int price, String name) {
        this.num = num;
        this.price = price;
        this.name = name;
    }

    @Override
    public int compareTo(Product other) {
        int x = this.num - other.num;
        return x;
    }
}
