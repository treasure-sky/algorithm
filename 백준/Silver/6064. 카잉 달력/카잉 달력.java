import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        while (k-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (M > N) { // 항상 M이 작도록 해줌
                int temp = M;
                M = N;
                N = temp;
                temp = x;
                x = y;
                y = temp;
            }

            int bound = M * N;
            int nowY = x;
            int idx = x;
            int gap = N - M;
            boolean isPossible = false;

            while (idx <= bound) {
                if (nowY == y) {
                    isPossible = true;
                    break;
                }
                nowY -= gap;
                if(nowY <= 0) nowY += N;
                idx += M;
            }

            if (isPossible) {
                System.out.println(idx);
            } else {
                System.out.println("-1");
            }

        }
    }
}
