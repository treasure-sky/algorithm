import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static Set<Integer>[] adj;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        dp = new int[n + 1][2]; // [i][j] : i번 노드를 루트로 하는 서브트리의 최소 얼리아답터 수(j가 1 일때는 루트가 얼리어답터, 0일때는 루트가 얼리어답터x)

        adj = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new HashSet<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            adj[n1].add(n2);
            adj[n2].add(n1);
        }
        dfs(1, 0);
        System.out.println(Math.min(dp[1][0], dp[1][1]));


    }

    static void dfs(int rootNode, int parentNode) {
        dp[rootNode][0] = 0;
        dp[rootNode][1] = 1;
        for (int node : adj[rootNode]) {
            if(node == parentNode) continue;
            dfs(node, rootNode);
            dp[rootNode][0] += dp[node][1];
            dp[rootNode][1] += Math.min(dp[node][1], dp[node][0]);
        }
    }
}
