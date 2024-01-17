import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] A;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        A = new int[n][m];
        visited = new boolean[n][m];

        for (int r = 0; r < n; r++) {
            String str = br.readLine();
            for (int c = 0; c < m; c++) {
                A[r][c] = str.charAt(c) - '0';
            }
        }

        visited[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int level = 1;

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int curX = temp[0];
            int curY = temp[1];
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if (nextX >= 0 && nextX < n && nextY >= 0 && nextY < m) { // 미로의 범위를 벗어나지 않고
                    if (!visited[nextX][nextY] && A[nextX][nextY] != 0) { // 방문하지 않았으며 길이 존재할 때만
                        visited[nextX][nextY] = true;
                        queue.add(new int[]{nextX, nextY});
                        // 기존에 있는 이차원 배열에 레벨을 저장하는 방식 -> 따로 레벨을 카운팅 안해주어도 됨
                        A[nextX][nextY] = A[curX][curY] + 1;
                        if (nextX == n - 1 && nextY == m - 1) {
                            System.out.println(A[n - 1][m - 1]);
                            System.exit(0);
                        }
                    }
                }
            }
        }


    }


}
