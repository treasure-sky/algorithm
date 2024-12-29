import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, 1, 1, 0, 0, -1, -1, -1};
    static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][M];
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int input = Integer.parseInt(st.nextToken());
                if (input == 1) {
                    A[i][j] = 1;
                    queue.add(new int[]{i, j}); // 상어 위치 큐에 넣기
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];
            for (int i = 0; i < 8; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || nx >= N || ny < 0 || ny >= M || A[nx][ny] != 0) continue;
                A[nx][ny] = A[x][y] + 1;
                queue.add(new int[]{nx, ny});
            }
        }

        int maxDistance = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                maxDistance = Math.max(maxDistance, A[i][j]);
            }
        }

        System.out.println(maxDistance - 1);
    }
}
