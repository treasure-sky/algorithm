import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] A;
    static int node;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        node = Integer.parseInt(br.readLine());
        int edge = Integer.parseInt(br.readLine());
        A = new boolean[node + 1][node + 1];
        boolean[] visited = new boolean[node + 1];
        for (int i = 0; i < edge; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int temp1 = Integer.parseInt(st.nextToken());
            int temp2 = Integer.parseInt(st.nextToken());
            A[temp1][temp2] = true;
            A[temp2][temp1] = true;
        }
        int cnt = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = true;
        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            for (int i = 1; i <= node; i++) {
                if (visited[i] || !A[i][currNode]) continue; // 방문했거나 edge가 없으면
                queue.add(i);
                visited[i] = true;
                cnt++;
            }
        }
        System.out.println(cnt);
        
    }

}
