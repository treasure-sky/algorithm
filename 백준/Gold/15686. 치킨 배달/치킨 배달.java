import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static class Point {
        byte x;
        byte y;

        public Point(byte x, byte y) {
            this.x = x;
            this.y = y;
        }
    }

    static byte getDistance(Point p1, Point p2) {
        return (byte) (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));
    }

    static int m;
    static int resultDistance = Integer.MAX_VALUE;

    static ArrayList<Point> house = new ArrayList<>();
    static ArrayList<Point> chicken = new ArrayList<>();
    static ArrayList<Integer> selectedChickenIdx = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // input
        for (byte i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (byte j = 0; j < n; j++) {
                byte input = Byte.parseByte(st.nextToken());

                if (input == 0) { // 빈 칸일 때
                    continue;
                }

                if (input == 1) { // 집일 때
                    house.add(new Point(i, j));
                    continue;
                }

                if (input == 2) { // 치킨집일 때
                    chicken.add(new Point(i, j));
                }
            }
        }

        dfs(0);

        System.out.println(resultDistance);

    }

    /**
     * @param idx 현재 폐업할지 정해야 하는 치킨집의 인덱스
     */
    static void dfs(int idx) {
        if (selectedChickenIdx.size() >= 1) {
            AtomicInteger sumDistance = new AtomicInteger();
            house.forEach(housePoint -> {
                int minDistance = Integer.MAX_VALUE; // 특정 집에서 선택한 치킨집들과의 치킨거리 중 최소 거리
                for (int chickenIdx : selectedChickenIdx) {
                    minDistance = Math.min(minDistance, getDistance(housePoint, chicken.get(chickenIdx)));
                }
                sumDistance.addAndGet(minDistance);
            });
            resultDistance = Math.min(resultDistance, sumDistance.get());
        }

        if (selectedChickenIdx.size() >= m || idx >= chicken.size()) { // 종료조건
            return;
        }

        int nextIdx = idx + 1;
        dfs(nextIdx); // 지금 치킨집은 폐업했을 때
        selectedChickenIdx.add(idx);

        dfs(nextIdx); // 지금 치킨집 선택했을 때
        selectedChickenIdx.remove(selectedChickenIdx.size() - 1);


    }
}
