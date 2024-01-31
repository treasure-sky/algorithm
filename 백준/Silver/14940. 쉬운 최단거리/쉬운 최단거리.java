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
        int[][] A = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        int twoR = 0;
        int twoC = 0;
        for (int r = 0; r < n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < m; c++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 2) { // 2의 row와 column을 저장
                    twoR = r;
                    twoC = c;
                    A[r][c] = 0;
                } else {
                    A[r][c] = input;
                }
            }
        }

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>(); // {현재 row, 현재 col, 레벨}
        queue.add(new int[]{twoR, twoC, 0});
        visited[twoR][twoC] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowR = now[0];
            int nowC = now[1];
            int level = now[2];
            A[nowR][nowC] = level;

            for (int i = 0; i < 4; i++) {
                int nextR = nowR + dr[i];
                int nextC = nowC + dc[i];
                if (nextR < 0 || nextR >= n || nextC < 0 || nextC >= m) continue;
                if (visited[nextR][nextC]) continue;
                if (A[nextR][nextC] == 1) {
                    queue.add(new int[]{nextR, nextC, level + 1});
                    visited[nextR][nextC] = true;
                } else {
                    visited[nextR][nextC] = true; // 도달하지 못하는 위치 판별 위해 0도 방분했다고 침.
                }

            }
        }

        // 도달하지 못하는 위치 판별(방문하지 않고 1인 곳)
        for (int r = 0; r < n; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < m; c++) {
                if (!visited[r][c] && A[r][c] == 1) { // 도달 못했으면 -1
                    sb.append("-1 ");
                } else {
                    sb.append(A[r][c] + " ");
                }
            }
            System.out.println(sb.toString());
        }


    }
}
