import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] A, res;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        res = new int[n][n];
        for (int i = 0; i < n; i++) { // 각 노드들에 대해
            visited = new boolean[n];
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dfs(i, j);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(res[i][j]+ " ");
            }
            System.out.println();
        }

    }

    static void dfs(int start, int end) {
        res[start][end] = 1;
        for (int i = 0; i < n; i++) { // i는 노드
            if (!visited[i] && A[end][i] == 1) { // 방문하지 않은 노드이고 다른 연결선이 존재하면
                visited[i] = true;
                dfs(start, i);
            }
        }
    }
}
