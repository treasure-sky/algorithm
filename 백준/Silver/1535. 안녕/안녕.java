import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] health = new int[n + 1];
        int[] happiness = new int[n + 1];
        StringTokenizer stHealth = new StringTokenizer(br.readLine());
        StringTokenizer stHappiness = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            health[i] = Integer.parseInt(stHealth.nextToken());
            happiness[i] = Integer.parseInt(stHappiness.nextToken());
        }

        // DP[i][j] : i번째 사람까지 고려했을 때 j의 체력을 가지고 얻을 수 있는 최대 행복
        int[][] DP = new int[n + 1][100];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < 100; j++) {
                if (j < health[i]) {
                    DP[i][j] = DP[i-1][j];
                    continue;
                }
                DP[i][j] = Math.max(DP[i - 1][j], DP[i - 1][j - health[i]] + happiness[i]);
            }
        }

        System.out.println(DP[n][99]);

    }
}
