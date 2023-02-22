package aSAF.compare_01_230210;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class WriterTest {
    public static void main(String[] args) {
        ArrayList<Writer> list = new ArrayList<>();
        list.add(new Writer(3, "jane", "kim", "white house"));
        list.add(new Writer(1, "tommy", "lee", "green house"));
        list.add(new Writer(2, "harry", "park", "pink house"));
        list.add(new Writer(4, "bill", "hong", "black house"));
        list.add(new Writer(5, "pony", "kim", "red house"));

        //1. 기본 정렬
        Collections.sort(list); // Writer 내에서 정의된 정렬 사용
        for (Writer writer : list){
            System.out.println(writer);
        }
        System.out.println();

        //2. Comparator 사용
        Collections.sort(list, new WriterTitleComparator()); // Writer 내에서 정의된 정렬 사용 + WriterTitleComparator
        for(Writer writer: list){
            System.out.println(writer);
        }
        System.out.println();

        //3. 무명 클래스
        Collections.sort(list, new Comparator<Writer>() { //무명 클래스, interface를 생성하며 바로 구현까지 정의
            @Override
            public int compare(Writer o1, Writer o2) {
                return o1.firstName.compareTo(o2.firstName);
            }
        });

        //4. 람다식 표현
        Collections.sort(list, ((o1, o2) -> {
            Writer w1 = (Writer) o1;
            Writer w2 = (Writer) o2;
            return o1.firstName.compareTo(o2.firstName);
        }));

        for(Writer writer: list){
            System.out.println(writer);
        }
        System.out.println();
    }
}
