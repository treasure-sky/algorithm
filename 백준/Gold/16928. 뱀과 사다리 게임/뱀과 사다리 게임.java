import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken())+ Integer.parseInt(st.nextToken());

        Map<Integer, Integer> route = new HashMap<>();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            route.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Set<Integer> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>(); // {위치, 주사위 굴린 횟수}
        queue.add(new int[]{1, 0});
        visited.add(1);

        while (true) {
            int[] temp = queue.poll();
            int now = temp[0];
            int level = temp[1];

            if (now == 100) {
                System.out.println(level);
                break;
            }

            for (int i = 1; i <= 6; i++) {
                int next = now + i;
                if(next > 100 || visited.contains(next)) continue;

                if (route.containsKey(next)) {
                    next = route.get(next);
                }

                queue.add(new int[]{next, level + 1});
                visited.add(next);
            }

        }

    }
}
