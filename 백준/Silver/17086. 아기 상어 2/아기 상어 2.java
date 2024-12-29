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
        boolean[][] A = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = st.nextToken().charAt(0) == '1';
            }
        }

        int result = 0;

        // 모든 칸 순회
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 1인 칸은 패스
                if(A[i][j]) continue;

                // 0인 칸은 bfs
                boolean[][] visited = new boolean[N][M];
                visited[i][j] = true;
                Queue<int[]> queue = new LinkedList<>();
                queue.add(new int[]{i, j, 0});
                boolean isEndBfs = false;
                while (!queue.isEmpty() && !isEndBfs) {
                    int[] cur = queue.poll();
                    for (int k = 0; k < 8; k++) {
                        int nextX = cur[0] + dx[k];
                        int nextY = cur[1] + dy[k];
                        int nextDis = cur[2] + 1;
                        if (nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) continue;
                        if (visited[nextX][nextY]) continue;
                        visited[nextX][nextY] = true;
                        if (A[nextX][nextY]){ // 다음 칸에 상어가 있는 경우
                            if (nextDis > result){
                                result = nextDis;
                            }
                            isEndBfs = true; // 현재 칸의 bfs는 종료
                            break;
                        }
                        queue.add(new int[]{nextX, nextY, nextDis});
                    }
                }


            }
        }
        System.out.println(result);
    }
}
