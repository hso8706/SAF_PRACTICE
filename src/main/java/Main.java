import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[]{1,1});

        System.out.println(list.contains(new int[]{1,1}));
    }
}
