import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        List<int[]>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            adj[from].add(new int[]{to, cost});
            adj[to].add(new int[]{from, cost});
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // 1번 노드부터 시작
        pq.add(new int[]{1, 0});

        int acc = 0;
        int max = 0;
        int visitCnt = 0;
        boolean[] visited = new boolean[n + 1];

        while (!pq.isEmpty()) {
            if(visitCnt == n) break; // MST 형성 완료


            int[] cur = pq.poll();
            int curNode = cur[0];
            int curCost = cur[1];

            if(visited[curNode]) continue;

            for(int[] next : adj[curNode]){
                int nextNode = next[0];
                int nextCost = next[1];
                if(!visited[nextNode]) pq.add(new int[]{nextNode, nextCost});
            }

            visited[curNode] = true;
            visitCnt++;
            max = Math.max(max, curCost);
            acc += curCost;
        }

        // 가장 cost 큰 길 삭제 ( 두 파트로 나뉨 )
        int res = acc - max;

        System.out.println(res);

    }
}
