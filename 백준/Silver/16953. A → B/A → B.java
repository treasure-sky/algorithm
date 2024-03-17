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
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        Queue<long[]> queue = new LinkedList<>(); // {현재 숫자, 소모한 연산 횟수}
        queue.add(new long[]{A, 1});

        boolean isPossible = false;
        while (!queue.isEmpty()) {
            long[] now = queue.poll();
            long nowNum = now[0];
            long nowCnt = now[1];

            if (nowNum == B) {
                System.out.println(nowCnt);
                isPossible = true;
                break;
            }

            if (2 * nowNum <= B) {
                queue.add(new long[]{2 * nowNum, nowCnt + 1});
            }
            if (10 * nowNum + 1 <= B) {
                queue.add(new long[]{10 * nowNum + 1, nowCnt + 1});

            }
        }

        if(!isPossible) System.out.println("-1");
    }


}
