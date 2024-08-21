import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            addEdge(map, node1, node2);
            addEdge(map, node2, node1);
        }

        // root에 연결된 자식 노드 큐에 넣기
        ArrayList<Integer> rootChildren = map.get(1);
        for (int child : rootChildren) {
            queue.add(new int[]{1, child});
        }

        int[] parents = new int[n + 1];

        while (!queue.isEmpty()) {
            int[] edge = queue.poll();
            if(parents[edge[1]] != 0) continue; // 이미 방문한 노드는 고려 안함
            parents[edge[1]] = edge[0];
            ArrayList<Integer> children = map.get(edge[1]);
            for (int child : children) {
                queue.add(new int[]{edge[1], child});
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i < n+1; i++) {
            sb.append(parents[i]).append("\n");
        }
        System.out.printf(sb.toString());





    }

    public static void addEdge(Map<Integer, ArrayList<Integer>> map, int key, int value) {
        if (map.containsKey(key)) {
            map.get(key).add(value);
        } else {
            ArrayList<Integer> arr = new ArrayList<>();
            arr.add(value);
            map.put(key, arr);
        }
    }
}
