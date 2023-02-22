package aSAF.compare_01_230210;
//Comparable: 클래스 정의할 때, 동시에 정렬 기준을 설정하는 interface
public class Writer implements Comparable<Writer> { //제네릭 타입 : 누구와 비교할 것인가
    int no;
    String firstName;
    String lastName;
    String bookTitle;

    public Writer(int no, String firstName, String lastName, String bookTitle) {
        this.no = no;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bookTitle = bookTitle;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "no=" + no +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bookTitle='" + bookTitle + '\'' +
                '}';
    }

    //정렬 기준을 표시하는 메소드
    @Override
    public int compareTo(Writer other) {

        //int 자료형 비교
        //this.no : 기준
        //other.no : 비교 대상
        //결과 : 음수, 0 ,양수
        //결과가 음수이거나 0이면, 객체들의 자리이 이동이 없음.(오름차순이 기본값이기 때문에)
        //결과가 양수이면, 객체들의 자리이 이동이 있음.(오름차순이 기본값이기 때문에)
//        int x = this.no - other.no; //this 가 앞에 있는 기준이면, 오름차순 정렬
//        int x = other.no - this.no; //this 가 뒤에 있는 기준이면, 내림차순 정렬

//        return x; //return this.no - other.no;

        //string 자료형 비교
//        return this.lastName.compareTo(other.lastName);
        int x = this.lastName.compareTo(other.lastName);
        if (x == 0) { //1차 비교 조건에서 값이 같은 경우, 다른 기준 조건으로 해결하기 위함.
            x = other.no - this.no;
        }
        return x;
    }
}
