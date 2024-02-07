import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] A;
    static int max = Integer.MIN_VALUE;
    static boolean[][] visited;
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                dfs(i, j, 0, 0);
                visited[i][j] = false;
                specialBlock(i, j);
            }
        }
        System.out.println(max);
    }

    static void dfs(int x, int y, int level, int sum) {
        if (level == 4) {
            max = Math.max(max, sum);
            return;
        }
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue; // 배열크기 오버
            if(visited[nextX][nextY]) continue;
            visited[nextX][nextY] = true;
            dfs(nextX, nextY, level + 1, sum + A[nextX][nextY]);
            visited[nextX][nextY] = false;
        }
    }

    static void specialBlock(int x, int y) { // 보라색 ㅗ 블럭 처리
        for (int i = 0; i < 4; i++) {
                int sum = A[x][y];
                for (int j = 0; j < 4; j++) { // 세 방향만 더해주기 (i가 제외할 방향)
                    if (i == j) continue;
                    int nextX = x + dx[j];
                    int nextY = y + dy[j];
                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) break; // 배열크기 오버
                    sum += A[nextX][nextY];
                }
                max = Math.max(max, sum);
        }
    }
}
