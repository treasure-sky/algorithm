import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static char[][] A;
    static boolean[][] visited;
    static int[] dx = new int[]{1, 0, -1, 0};
    static int[] dy = new int[]{0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new char[n][n];
        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                A[i][j] = str.charAt(j);
            }
        }

        visited = new boolean[n][n];
        int cntNormal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                } else {
                    cntNormal++;
                    dfs(i, j, A[i][j]);
                }
            }
        }
        System.out.print(cntNormal + " ");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 'R') A[i][j] = 'G'; // R은 모두 G로 변경
            }
        }

        visited = new boolean[n][n];
        int cntBlined = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j]) {
                    continue;
                } else {
                    cntBlined++;
                    dfs(i, j, A[i][j]);
                }
            }
        }
        System.out.print(cntBlined);

    }

    static void dfs(int row, int col, char color) {
        visited[row][col] = true;
        for (int i = 0; i < 4; i++) {
            int nextRow = row + dx[i];
            int nextCol = col + dy[i];
            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= n) continue;
            if(visited[nextRow][nextCol]) continue;
            if(color != A[nextRow][nextCol]) continue;

            dfs(nextRow,nextCol, color);
        }
    }

}
