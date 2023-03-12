package pocket;

public class PocketExam1 {
    public static void main(String[] args) {
        double radian1 = Math.atan2(2, 2); // 45
        double radian2 = Math.atan2(2, -2); // 135
        double radian3 = Math.atan2(-2, -2); // -135
        double radian4 = Math.atan2(-2, 2); // -45

        System.out.println(radian1 * 180 / Math.PI);
        System.out.println(radian2 * 180 / Math.PI);
        System.out.println(radian3 * 180 / Math.PI);
        System.out.println(radian4 * 180 / Math.PI);

        System.out.println(get_distance(0, 0, 0, 10, 5, 5));


    }

    static double get_targetDegree(int x1, int y1, int x2, int y2) {
        int width = x2 - x1;
        int height = y2 - y1;

        double radian = Math.atan2(height, width);

        // 목적구가 수구의 좌 상단에 위치
        if (x1 >= x2 && y1 < y2) {
            return 180 + (180 / Math.PI * radian);
        }
        // 목적구가 수구의 좌 하단에 위치
        else if (x1 >= x2 && y1 >= y2) {
            return 180 + 180 + (180 / Math.PI * radian);
        }
        // 목적구가 우하단
        else if (x1 < x2 && y1 >= y2) {
            return 180 + (180 / Math.PI * radian);
        }
        // 목적구가 우 상단
        else if (x1 < x2 && y1 < y2) {
            return 180 / Math.PI * radian;
        }

        return 0;
    }

    static double get_distance(double x1, double y1, double x2, double y2, double x3, double y3) {
        // ax + by + c = 0
        // 0,0
        // 2,2

        // y - y1 = m * (x - x1) -> mx -y + -(m * x1) + y1
        //-y + c = 0
        double a = x1 == x2 || y1 == y2 ? 0 : y2 - y1 / x2 - x1;
        double b = -1;
        double c = x1 != x2 && y1 == y2 ? y1 : x1 == x2 && y1 != y2 ? 0 : y1 - (a * x1);

        if(a == 0 && c  == 0) return Math.abs(x1-x3);
        else if(a == 0 && c == y1 && c == y2) return Math.abs(y1-y3);
        else return Math.abs(a * x3 + b * y3 + c) / Math.sqrt(a * a + b * b);

    }
}