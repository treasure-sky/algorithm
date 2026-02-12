import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] m = new int[n][2];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m[i][0] = Integer.parseInt(st.nextToken());
            m[i][1] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n][n];

        for (int r = 0; r < n - 1; r++) {
            int c = r + 1;
            dp[r][c] = m[r][0] * m[r][1] * m[c][1];
        }

        for (int line = 2; line < n; line++) {
            for (int r = 0; r < n - line; r++) {
                int c = line + r;
                dp[r][c] = Integer.MAX_VALUE;

                for (int k = r; k < c; k++) {
                    int cost = dp[r][k] + dp[k + 1][c] + (m[r][0] * m[k][1] * m[c][1]);
                    dp[r][c] = Math.min(dp[r][c], cost);
                }

            }
        }
        System.out.println(dp[0][n - 1]);


    }

}