import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m; // 도시개수, 버스개수
    static Map<Integer, Integer>[] adj; // {node번호, 가중치}
    static int[] minDistance; // 최소 거리
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        minDistance = new int[n + 1];
        Arrays.fill(minDistance, 100000000); // 최소거리를 최댓값으로 초기화

        adj = new  Map[n+1];
        for (int i = 1; i < n+1; i++) {
            adj[i] = new HashMap<>();
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int inputStart = Integer.parseInt(st.nextToken());
            int inputEnd = Integer.parseInt(st.nextToken());
            int inputDistance = Integer.parseInt(st.nextToken());

            if (adj[inputStart].containsKey(inputEnd)) { // 버스의 비용이 가장 작은 것만 남겨두기
                adj[inputStart].put(inputEnd, Math.min(adj[inputStart].get(inputEnd), inputDistance));
            } else {
                adj[inputStart].put(inputEnd, inputDistance);
            }
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        minDistance[start] = 0;
        System.out.println(dijk(start, end));

    }

    public static int dijk(int start, int end) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1]; // distance 오름차순 정렬
            }
        });
        pq.add(new int[]{start, 0}); // {위치, 거리}
        while (!pq.isEmpty()) {
            int[] node = pq.poll();
            int nodeNum = node[0];
            int distance = node[1];

            if (nodeNum == end) {
                return distance;
            }

            for (int adjNode : adj[nodeNum].keySet()) { // adjNode는 node에 연결된 노드
                int adjNodeDistance = adj[nodeNum].get(adjNode); //adjNode까지의 거리
                if (distance + adjNodeDistance < minDistance[adjNode]) {
                    minDistance[adjNode] = distance + adjNodeDistance;
                    pq.add(new int[]{adjNode, minDistance[adjNode]});
                }
            }
        }
        return -1;
    }
}
