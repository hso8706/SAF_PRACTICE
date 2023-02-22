package aSAF.divide_230220;

import java.util.Arrays;

public class BinarySearchTest {
    static int[] arr = {3, 11, 15, 20, 21, 29, 45, 59, 65, 72}; // 정렬되어있는 상태
//    static List<Integer>  list = Arrays.asList(){3, 11, 15, 20, 21, 29, 45, 59, 65, 72;}
    public static void main(String[] args) {
        System.out.println(Arrays.toString(arr));
        System.out.println(binarySearch(65)); // 8
        System.out.println(binarySearch(3)); // 0
        System.out.println(binarySearch(2)); // -1
        System.out.println(binarySearch(46)); // -1
        System.out.println(binarySearch(72)); // 9
        System.out.println("--------------------------");

        System.out.println(binarySearch2(65, 0, arr.length)); // 8
        System.out.println(binarySearch2(3, 0, arr.length)); // 0
        System.out.println(binarySearch2(2, 0, arr.length)); // -1
        System.out.println(binarySearch2(46, 0, arr.length)); // -1
        System.out.println(binarySearch2(72, 0, arr.length)); // 9

        System.out.println();
        System.out.println("--------------------------");
    }

    private static int binarySearch2(int key, int start, int end) { // 재귀를 이용한 방법
        if(start <= end){ // 재귀 호출
            int mid = (start + end) / 2;
            if(arr[mid] == key){
                return mid;
            }
            else if (arr[mid] < key)
                binarySearch2(key, mid + 1, end);
            else {
                binarySearch2(key, start, mid - 1);
            }

        }

        return 2;
    }

    private static int binarySearch(int key) { // 반복문을 이용한 방법
        int start = 0, end = arr.length-1, mid = 0;
        
        while(start <= end){
            mid = (start + end) / 2;
            if(arr[mid] == key)
                return mid;
            else if(arr[mid] < key) // key가 mid보다 오른편에 존재
                start = mid + 1; //start 지점 이동
            else
                end = mid - 1;    
        }
        return -1; // start가 end 지점을 넘어선 경우 => 데이터가 없음
    }
}
