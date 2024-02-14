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
        int[][] A = new int[m][n];
        int unripe = 0;
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) { // 토마토 위치, 상태, 갱신일자 초기화
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int state = Integer.parseInt(st.nextToken());
                A[i][j] = state;
                if (state == 1) { // 익은 경우 큐에 추가
                    queue.add(new int[]{i, j, 0}); // 0 은 0일차에 익어있다는 의미
                } else if(state == 0){ // 안익은 경우
                    unripe++;
                }
            }
        }
        int[][] d = new int[][] { // 인접한 토마토의 상대적 위치
                {1, 0},
                {-1, 0},
                {0, 1},
                {0, -1}
        };
        int nowDay = 0;
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int nowM = temp[0];
            int nowN = temp[1];
            nowDay = temp[2];

            for (int i = 0; i < 4; i++) {
                int nextM = nowM + d[i][0];
                int nextN = nowN + d[i][1];
                if (nextM < 0 || nextM >= m || nextN < 0 || nextN >= n) continue;
                if (A[nextM][nextN] == 0) { // 인접한 토마토가 안익었으면
                    A[nextM][nextN] = 1; // 익게 하기
                    queue.add(new int[]{nextM, nextN, nowDay + 1});
                    unripe--;
                }
            }
        }


        if (unripe > 0) {
            System.out.println(-1);
        } else {
            System.out.println(nowDay);
        }


    }
}

