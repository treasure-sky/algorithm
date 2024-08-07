import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[][] A = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            A[start][end] = weight;
        }

        int go[] = dijkstra(X, A);

        int[][] B = new int[N + 1][N + 1];
        for (int i = 1; i < N + 1; i++) {
            for (int j = 1; j < N + 1; j++) {
                B[j][i] = A[i][j];
            }
        }

        int[] come = dijkstra(X, B);


        int[] res = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            res[i] = go[i] + come[i];
        }

        Arrays.sort(res);
        System.out.print(res[res.length - 1]);

    }

    static int[] dijkstra(int X, int[][] A){
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        pq.add(new int[]{X, 0});
        int[] opt = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            opt[i] = Integer.MAX_VALUE;
        }
        opt[X] = 0;

        while (!pq.isEmpty()){
            int[] nowNode = pq.poll();
            int now = nowNode[0];
            if(visited[now]) continue;
            visited[now] = true;
            for (int i = 1; i < N + 1; i++) {
                if(A[now][i] == 0) continue; // 엣지가 없으면 패스
                if(opt[i] >= opt[now] + A[now][i]){
                    opt[i] = opt[now] + A[now][i];
                    pq.add(new int[]{i, opt[i]});
                }
            }
        }
        return opt;
    }

}


