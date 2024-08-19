import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int range = 100001;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int subin = Integer.parseInt(st.nextToken());
        int dong = Integer.parseInt(st.nextToken());

        if (subin >= dong) {
            System.out.print(subin - dong);
        } else {
            
            PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
                @Override
                public int compare(int[] o1, int[] o2) {
                    return o1[1] - o2[1];
                }
            });
            queue.add(new int[]{subin, 0});
            int[] visited = new int[range];
            for (int i = 0; i < range; i++) {
                visited[i] = Integer.MAX_VALUE;
            }
            visited[subin] = 0;
            while (!queue.isEmpty()){
                int[] now = queue.poll();
                int nowIdx = now[0];
                int nowTime = now[1];

                if (nowIdx == dong){
                    System.out.print(nowTime);
                    break;
                }
                if (nowIdx * 2 < range && visited[nowIdx * 2] > nowTime) {
                    queue.add(new int[]{nowIdx * 2 , nowTime});
                    visited[nowIdx * 2] = nowTime;
                }
                if (nowIdx + 1 < range && visited[nowIdx + 1] > nowTime) {
                    queue.add(new int[]{nowIdx + 1, nowTime + 1});
                    visited[nowIdx + 1] = nowTime + 1;
                }
                if (nowIdx - 1 >= 0 && visited[nowIdx - 1] > nowTime) {
                    queue.add(new int[]{nowIdx - 1, nowTime + 1});
                    visited[nowIdx - 1] = nowTime + 1;
                }
            }
        }
    }
}
