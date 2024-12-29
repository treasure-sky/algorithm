import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        for (int i = L; i <= 100; i++) {
            long mid = N / i;
            long start = mid - ((i - 1) / 2);
            if (start < 0) continue;
            long sum = (start * 2 + i - 1) * i / 2;
            StringBuilder sb = new StringBuilder();
            if (sum == N) {
                for (long j = start; j < start + i; j++) {
                    sb.append(j).append(" ");
                }
                System.out.println(sb.toString());
                System.exit(0);
            }
        }
        System.out.printf("-1");
    }
}
