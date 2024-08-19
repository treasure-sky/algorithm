import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] A = new int[2][n + 2]; // 맨 왼쪽 두칸은 계산을 쉽게 하기위해 추가
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            StringTokenizer st2 = new StringTokenizer(br.readLine());
            for (int j = 2; j < n + 2; j++) {
                A[0][j] = Integer.parseInt(st1.nextToken());
                A[1][j] = Integer.parseInt(st2.nextToken());
            }

            int[][] dp = new int[2][n + 2];
            for (int j = 2; j < n + 2; j++) {
                dp[0][j] = Math.max(dp[1][j - 1], dp[1][j - 2]) + A[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], dp[0][j - 2]) + A[1][j];
            }
            System.out.println(Math.max(dp[0][n + 1], dp[1][n + 1]));
        }
    }
}
