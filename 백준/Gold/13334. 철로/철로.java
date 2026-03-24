import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] A = new int[n][3];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(from > to){
                int temp = from;
                from = to;
                to = temp;
            }

            A[i][0] = from;
            A[i][1] = to;
            A[i][2] = A[i][1] - A[i][0];
        }

        Arrays.sort(A, Comparator.comparingInt(a -> a[1])); // 오른쪽 점 기준으로 오름차순 정렬

        int d = Integer.parseInt(br.readLine());
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        int res = 0;

        for (int[] cur : A) {
            if (cur[2] <= d) { // d보다 작은 선분의 경우
                while(pq.peek() != null && pq.peek()[0] < ((long) cur[1] - (long) d)){
                    pq.poll();
                }
                pq.add(new int[]{cur[0], cur[1]}); // 큐에 넣고

                res = Math.max(res, pq.size());
            }
        }

        System.out.println(res);

    }
}
