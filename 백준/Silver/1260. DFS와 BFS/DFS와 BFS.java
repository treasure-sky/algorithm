import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m, v;
    static boolean[][] A;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        A = new boolean[n + 1][n + 1];

        while (m-- > 0) { // 인접행렬 초기화
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            A[start][end] = true;
            A[end][start] = true;
        }

        visited = new boolean[n + 1];
        visited[v] = true;
        System.out.println(DFS(v));

        visited = new boolean[n + 1];
        visited[v] = true;
        System.out.println(BFS(v));

    }

    static String DFS(int init) {
        StringBuilder sb = new StringBuilder();
        _DFS(sb, init);
        return sb.toString();
    }

    static void _DFS(StringBuilder sb , int now) {
        sb.append(now).append(" "); // 방문
        for (int i = 0; i <= n; i++) {
            if(visited[i] || !A[now][i]) continue; // 방문했거나 간선이 없으면 pass
            visited[i] = true;
            _DFS(sb, i);
        }
    }

    static String BFS(int init) {
        StringBuilder sb = new StringBuilder();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(init);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append(" "); // 방문
            for (int i = 0; i <= n; i++) {
                if(visited[i] || !A[now][i]) continue; // 방문했거나 간선이 없으면 pass
                visited[i] = true;
                queue.add(i);
            }
        }
        return sb.toString();
    }
}
