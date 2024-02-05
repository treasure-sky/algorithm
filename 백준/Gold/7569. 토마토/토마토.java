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
        int h = Integer.parseInt(st.nextToken());
        int[][][] A = new int[h][m][n];
        int unripe = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < h; i++) { // 토마토 위치, 상태, 갱신일자 초기화
            for (int j = 0; j < m; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < n; k++) {
                    int state = Integer.parseInt(st.nextToken());
                    A[i][j][k] = state;
                    if (state == 1) { // 익은 경우 큐에 추가
                        queue.add(new int[]{i, j, k, 0}); // 0 은 0일차에 익어있다는 의미
                    } else if(state == 0){ // 안익은 경우
                        unripe++;
                    }
                }
            }
        }
        int[][] d = new int[][] { // 인접한 토마토의 상대적 위치
                {1, 0, 0},
                {-1, 0, 0},
                {0, 1, 0},
                {0, -1, 0},
                {0, 0, 1},
                {0, 0, -1}
        };
        int nowDay = 0;
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int nowH = temp[0];
            int nowM = temp[1];
            int nowN = temp[2];
            nowDay = temp[3];

            for (int i = 0; i < 6; i++) {
                int nextH = nowH + d[i][0];
                int nextM = nowM + d[i][1];
                int nextN = nowN + d[i][2];
                if (nextH < 0 || nextH >= h || nextM < 0 || nextM >= m || nextN < 0 || nextN >= n) continue;
                if (A[nextH][nextM][nextN] == 0) { // 인접한 토마토가 안익었으면
                    A[nextH][nextM][nextN] = 1; // 익게 하기
                    queue.add(new int[]{nextH, nextM, nextN, nowDay + 1});
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
