import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int INF = 4 * 100_000;// 최대 나올 수 있는 값

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][][] dp = new int[100_001][5][5]; // [idx][left]][right]
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, INF);
            }
        }
        dp[0][0][0] = 0;

        int idx = 0;
        while(true){
            int target = Integer.parseInt(st.nextToken());
            if(target == 0) break;

            for (int l = 0; l < 5; l++) {
                for (int r = 0; r < 5; r++) {
                    if(dp[idx][l][r] == INF) continue; // 불가능한 경우 무시

                    if(r != target){
                        int costL = dp[idx][l][r] + cost(l, target);
                        dp[idx + 1][target][r] = Math.min(dp[idx + 1][target][r], costL);
                    }

                    if (l != target) {
                        int costR = dp[idx][l][r] + cost(r, target);
                        dp[idx + 1][l][target] = Math.min(dp[idx + 1][l][target], costR);
                    }

                }
            }

            idx++;
        }

        int ans = INF;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (dp[idx][i][j] < ans) {
                    ans = dp[idx][i][j];
                }
            }
        }

        System.out.println(ans);

    }

    public static int cost(int dir, int target) {
        if(dir == 0) return 2;
        if(dir == target) return 1;
        if(Math.abs(dir - target) == 1 || Math.abs(dir - target) == 3) return 3;
        if(Math.abs(dir - target) == 2) return 4;
        return 0;
    }



}
