import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] A = new boolean[n + 1][n + 1];
        boolean[] visited = new boolean[n + 1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            A[u][v] = true;
            A[v][u] = true;
        }
        int element = 0;
        for (int i = 1; i <= n; i++) { // 모든 vertex 순회
            if (!visited[i]) { // 방문하지 않은 vertex만
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                element++;
                while (!queue.isEmpty()) { // bfs로 같은요소 탐색 후 방문 표시
                    int now = queue.poll();
                    for (int j = 1; j <= n; j++) {
                        if(visited[j] || !A[now][j]) continue; // 방문했거나 연결되지 않은 vertex는 pass
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }
        }
        System.out.println(element);

    }
}
