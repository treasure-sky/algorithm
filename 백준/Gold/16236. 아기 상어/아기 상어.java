import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    static int[][] map;
    static int n;
    static int startR;
    static int startC;
    static int size = 2; // 상어크기
    static int exp = 0; // 상어 경험치
    static int time = 0; // 소요된 시간

    static int[] dr = new int[]{-1, 0, 0, 1};
    static int[] dc = new int[]{0, -1, 1, 0};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){ // 상어의 초기 위치
                    startR = i;
                    startC = j;
                    map[i][j] = 0; // 9 지워줌
                }
            }
        }

        int nowR = startR;
        int nowC = startC;
        while (true) {

            int[] res = bfs(nowR, nowC);
            if(res[0] == -1) break;

            nowR = res[0];
            nowC = res[1];

        }
        System.out.print(time);

    }

    public static int[] bfs(int r, int c){
        Queue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {

                if (o1[2] != o2[2]) { // 레벨이 낮은 거 우선
                    return o1[2] - o2[2];
                }

                if (o1[0] != o2[0]) { // row가 작은 거 우선
                    return o1[0] - o2[0];
                }

                return o1[1] - o2[1]; // col이 작은 거 우선

            }
        });
        boolean[][] visited = new boolean[n][n];
        visited[r][c] = true;
        queue.add(new int[]{r, c, 0});

        while (!queue.isEmpty()) {
            int[] next = queue.poll();
            int nowR = next[0];
            int nowC = next[1];
            int nowLevel = next[2];

            int fish = map[nowR][nowC];
            if (fish != 0 && fish < size) { // 먹을 수 있으면
                map[nowR][nowC] = 0;
                expUp(); // 경험치 업
                time += nowLevel;
                return new int[]{nowR, nowC};
            }

            for (int i = 0; i < 4; i++) {
                int nextR = nowR + dr[i];
                int nextC = nowC + dc[i];

                if(nextR < 0 || nextR >= n || nextC < 0 || nextC >= n) continue;
                if(visited[nextR][nextC]) continue;


                if (fish <= size) { // 지나갈 수 있으면
                    visited[nextR][nextC] = true;
                    queue.add(new int[]{nextR, nextC, nowLevel + 1});
                }

            }
        }
        return new int[]{-1, -1};

    }

    public static void expUp(){
        exp++;
        if (exp == size) {
            size++;
            exp = 0;
        }
    }

    public static void debug(){
        System.out.println("time: " + time + "size: " + size + ", exp : " + exp);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }



}
