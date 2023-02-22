package aSAF.compare_01_230210;

import java.util.Comparator;

//Comparator:
public class WriterTitleComparator implements Comparator<Writer> {

    //정렬 기준을 나타내는 메소드
    @Override
    public int compare(Writer o1, Writer o2) {
        
        return o1.bookTitle.compareTo(o2.bookTitle); // bookTitle 기준으로 오름차순 정렬
    }
}
