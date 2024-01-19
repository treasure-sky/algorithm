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
        char[][] A = new char[n][m];
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        boolean[][] visited = new boolean[n][m];
        int doyeonX = 0;
        int doyeonY = 0;
        for (int r = 0; r < n; r++) {
            String str = br.readLine();
            for (int c = 0; c < m; c++) {
                A[r][c] = str.charAt(c);
                if (A[r][c] == 'I') {
                    doyeonX = r;
                    doyeonY = c;
                }
            }
        }

        int people = 0;
        visited[doyeonX][doyeonY] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{doyeonX, doyeonY});

        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int curX = temp[0];
            int curY = temp[1];
            for (int i = 0; i < 4; i++) {
                int nextX = curX + dx[i];
                int nextY = curY + dy[i];
                if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= m) continue;
                if (visited[nextX][nextY] || A[nextX][nextY] == 'X') continue;
                visited[nextX][nextY] = true;
                queue.add(new int[]{nextX, nextY});
                if (A[nextX][nextY] == 'P') {
                    people += 1;
                }

            }
        }
        if (people == 0) {
            System.out.println("TT");
        } else {
            System.out.println(people);
        }
    }
}