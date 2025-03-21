import java.util.*;
import java.io.*;
class Main {
    static int[] dr = new int[]{0, 1, 0, -1};
    static int[] dc = new int[]{1, 0, -1, 0};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n==0) break;

            int[][] A = new int[n][n];
            for(int i=0; i<n;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0;j<n;j++){
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("Problem " + cnt++ + ": " + dijk(A, n));
        }
    }

    public static int dijk(int[][] A, int n){
        // index 0, 1에는 row, column / 2에는 거리
        PriorityQueue<int[]> pq = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
        pq.add(new int[]{0,0,A[0][0]});

        boolean[][] visited = new boolean[n][n];

        int[][] distance = new int[n][n];
        for(int i=0; i<n;i++){
            for(int j=0; j<n;j++){
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        while(!pq.isEmpty()){
            int[] node = pq.poll();
            int r = node[0], c = node[1], dis = node[2];
            visited[r][c] = true;
            if(distance[r][c] < dis) continue;
            distance[r][c] = dis;
            if(r == n-1 && c == n-1){
                return distance[n-1][n-1];
            }

            for(int i=0;i<4;i++){
                int nextR = r + dr[i];
                int nextC = c + dc[i];
                if(nextR < 0 || nextR > n-1 || nextC < 0 || nextC > n-1) continue;
                if(visited[nextR][nextC]) continue;
                pq.add(new int[]{nextR, nextC, distance[r][c] + A[nextR][nextC]});
            }

        }
        return distance[n-1][n-1];
    }
}
