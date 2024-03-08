import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int n, disMax, nodeMax;
    static List<int[]>[] adj;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) { //배열 초기화
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            adj[start] = new ArrayList<>();
            List nowNode = adj[start];
            while (true) {
                int end = Integer.parseInt(st.nextToken());
                if(end == -1) break;
                int distance = Integer.parseInt(st.nextToken());
                nowNode.add(new int[]{end, distance});
            }
        }

        disMax = Integer.MIN_VALUE;
        nodeMax = -1;
        dfs(1, 0, new boolean[n + 1]);
        int startNode = nodeMax;
        disMax = Integer.MIN_VALUE;
        nodeMax = -1;
        boolean[] visited = new boolean[n + 1];
        visited[startNode] = true;
        dfs(startNode, 0, visited);
        System.out.println(disMax);


    }

    public static void dfs(int nodeNum, int distance, boolean[] visited) {
        if(disMax < distance){
            disMax = distance;
            nodeMax = nodeNum;
        }

        List<int[]> nowNode = adj[nodeNum];
        for (int[] nextNode : nowNode) {
            if(visited[nextNode[0]]) continue;
            visited[nextNode[0]] = true;
            dfs(nextNode[0], distance + nextNode[1], visited);
        }
    }
}
