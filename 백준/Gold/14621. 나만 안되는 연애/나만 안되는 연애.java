import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int[] parent; // 부모 노드 저장 배열

    public static int find(int a) {
        if (parent[a] == a) return a; // 루트 노드인 경우
        return parent[a] = find(parent[a]); // 경로 압축
    }

    public static void union(int a, int b){
        int rootA = find(a);
        int rootB = find(b);
        if(rootA != rootB){
            parent[rootB] = rootA; // 루트 노드 연결
        }
    }

    static class Edge implements Comparable<Edge>{
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[] sex = new char[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            sex[i] = st.nextToken().charAt(0);
        }

        List<Edge> edges = new ArrayList<Edge>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            if (sex[from] != sex[to]) {
                edges.add(new Edge(from, to, cost));
            }
        }

        Collections.sort(edges);

        parent = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            parent[i] = i; // 자기 자신으로 초기화
        }

        int totalCost = 0;
        int edgesUsed = 0;
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalCost += edge.cost;
                edgesUsed++;
            }
            if(edgesUsed == n - 1) break; // n-1개의 간선을 사용했으면 종료
        }
        if (edgesUsed == n - 1) {
            System.out.println(totalCost);
        }else{
            System.out.println(-1);
        }

    }
}
