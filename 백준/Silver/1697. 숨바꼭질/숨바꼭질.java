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
        int k = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        int min = Integer.MAX_VALUE;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n, 0}); // {현재위치, 경과한 초}
        while (!queue.isEmpty()) {
            int[] temp = queue.poll();
            int pos = temp[0];
            int second = temp[1];

            if(pos < 0 || pos > 100000 || visited[pos]) continue;
            visited[pos] = true;

            if (pos == k) {
                min = second;
                break;
            } else if (pos > k) {
                queue.add(new int[]{pos - 1, second + 1});
            } else {
                queue.add(new int[]{pos - 1, second + 1});
                queue.add(new int[]{pos + 1, second + 1});
                queue.add(new int[]{pos * 2, second + 1});
            }
        }
        System.out.println(min);

    }
}