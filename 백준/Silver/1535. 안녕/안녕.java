import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 사람 수
        int[] health = new int[n + 1]; // 체력 감소 배열
        int[] happiness = new int[n + 1]; // 행복 배열

        for (int i = 1; i <= n; i++) {
            health[i] = sc.nextInt();
        }

        for (int i = 1; i <= n; i++) {
            happiness[i] = sc.nextInt();
        }

        int maxHealth = 100; // 체력은 최대 100
        int[][] dp = new int[n + 1][maxHealth]; // DP 테이블

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < maxHealth; j++) {
                if (j < health[i]) {
                    dp[i][j] = dp[i - 1][j]; // 체력이 부족한 경우
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - health[i]] + happiness[i]);
                }
            }
        }

        System.out.println(dp[n][99]); // 체력이 1 이상 남아있는 경우의 최대 행복 출력
    }
}
