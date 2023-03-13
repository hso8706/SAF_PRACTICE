package pocket.submit;

public class PocketTemp {
    static final int TABLE_WIDTH = 254;
    static final int TABLE_HEIGHT = 127;
    static final int NUMBER_OF_BALLS = 6;
    static final int[][] HOLES = { { 0, 0 }, { 127, 0 }, { 254, 0 }, { 0, 127 }, { 127, 127 }, { 254, 127 } };
    public static void main(String[] args) {
        /*
        ### 포켓볼 로직 원리
        1. 플레이어구, 목적구, 홀이 존재
        2. 목적구와 홀을 이은 직선의 연장선에서 접점을 갖는 구를 가정. 이를 가상구라고 칭함
        3. 플레이어구가 가상구의 위치로 진행하면 목적구는 홀에 들어가게됨.
            - 원의 절반이 홀에 걸치면 in으로 판정. 즉, 원의 중심이 홀 범위에 닿아야 한다.
        
        ### 포켓볼 로직 구현
        1. 플레이어구, 목적구, 홀의 위치 파악
            - 배열(좌표) 입력
            - 공과 홀의 반지름 존재
        2, 플레이어구를 기준으로 가상구까지의 거리(d)를 구함. 이는 플레이어구를 치는 세기를 결정하는 요소가 됨.
        3. 플레이어구에서 x축 혹은 y축을 향하는 선과 플레이어구에서 가상구를 향하는 선의 각도(c)를 구함. 이는 플레이어구를 치는 방향을 결정하는 요소가 됨

        ### 고려할 상황들
        - 어느 홀에 넣어야할지 선택
            - 넣을 수 있는 홀이 많지만 가장 유리한 홀 찾는 상황
                - 가상구, 플레이어구, 홀이 세 포인트의 삼각형일 때, 가상구 점 각도가 180도에 가까울 수록 넣기 쉽다
            - 홀을 향해 가는 길목에 장애물이 있는 상황
        - 곧장 목적구를 칠 수 있는지, 없는지
        
        ### 라이브러리
        - Math.asin(a), Math.acos(a), Math.atan(a);
        : 아크 사인, 아크 코사인, 아크 탄젠트
        : 라디안 값을 반환
        - Math.toRadians(d), Math.toDegrees(r);
        : 라디안과 degree를 변환하는 함수

        ### 영역
        - 당구대 : 127 X 254 (cm)
        - 구 지름: 5.73 (cm)
        */
        double r = 3;
        double playerY = 30;
        double playerX = 30;
        double aimY = 100;
        double aimX = 200;
        double holeY = 127;
        double holeX = 254;
        double unrealY;
        double unrealX;

        double degreeToAim = Math.atan(Math.abs(aimY-holeY)/Math.abs(aimX-holeX));
        unrealX = 200 - (2*r)*Math.cos(degreeToAim);
        unrealY = 100 - (2*r)*Math.sin(degreeToAim);
        System.out.println(unrealY +","+unrealX);

    }
}