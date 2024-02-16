import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] adj = new boolean[n + 1][n + 1];

        while (m-- > 0) { // 인접행렬 초기화
            st = new StringTokenizer(br.readLine());
            int person1 = Integer.parseInt(st.nextToken());
            int person2 = Integer.parseInt(st.nextToken());
            adj[person1][person2] = true;
            adj[person2][person1] = true;
        }

        int min = Integer.MAX_VALUE; // 케빈 베이컨 수의 최소값
        int minNode = Integer.MAX_VALUE; // min을 케빈 베이컨 수로 가지는 사람의 번호

        for (int i = 1; i <= n; i++) { // 기준 사람

            int kb = 0; // 케빈 베이컨 수

            Queue<int[]> queue = new LinkedList<>();
            Set<Integer> visited = new HashSet<>();
            queue.add(new int[]{i, 0}); // {현재 위치, 지나온 거리}
            visited.add(i);

            while (!queue.isEmpty()) {
                int[] temp = queue.poll();
                int now = temp[0];
                int distance = temp[1];

                kb += distance;

                for (int j = 1; j <= n; j++) {
                    // 방문한 노드는 다시 방문하지 않음
                    // (BFS로 순회시 나중에 순회하는 노드는 더 작은 거리를 가질 수 없음.)
                    if (adj[now][j] && !visited.contains(j)) { // 이어져있고 방문하지 않은 사람만
                        visited.add(j);
                        queue.add(new int[]{j, distance + 1});
                    }
                }
            }

            if (min >= kb) {
                if(min == kb && minNode < i) continue;
                min = kb;
                minNode = i;
            }
        }

        System.out.println(minNode);
    }
}
