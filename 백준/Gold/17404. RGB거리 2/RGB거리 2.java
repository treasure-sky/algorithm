import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] A = new int[n][3];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
            A[i][2] = Integer.parseInt(st.nextToken());
        }

        int res = Integer.MAX_VALUE;
        // 처음이 R
        int[][] DP = new int[n][3];
        DP[0][0] = A[0][0];
        DP[0][1] = 10_000_000; // G
        DP[0][2] = 10_000_000; // B
        for (int i = 1; i < n; i++) {
            DP[i][0] = Math.min(DP[i-1][1] + A[i][0], DP[i-1][2] + A[i][0]);
            DP[i][1] = Math.min(DP[i-1][0] + A[i][1], DP[i-1][2] + A[i][1]);
            DP[i][2] = Math.min(DP[i-1][1] + A[i][2], DP[i-1][0] + A[i][2]);
        }
        res = Math.min(Math.min(res, DP[n - 1][2]), DP[n - 1][1]);

        // 처음이 G
        DP[0][0] = 10_000_000;
        DP[0][1] = A[0][1];
        DP[0][2] = 10_000_000;
        for (int i = 1; i < n; i++) {
            DP[i][0] = Math.min(DP[i-1][1] + A[i][0], DP[i-1][2] + A[i][0]);
            DP[i][1] = Math.min(DP[i-1][0] + A[i][1], DP[i-1][2] + A[i][1]);
            DP[i][2] = Math.min(DP[i-1][1] + A[i][2], DP[i-1][0] + A[i][2]);
        }
        res = Math.min(Math.min(res, DP[n - 1][0]), DP[n - 1][2]);


        // 처음이 B
        DP[0][0] = 10_000_000;
        DP[0][1] = 10_000_000;
        DP[0][2] = A[0][2];
        for (int i = 1; i < n; i++) {
            DP[i][0] = Math.min(DP[i-1][1] + A[i][0], DP[i-1][2] + A[i][0]);
            DP[i][1] = Math.min(DP[i-1][0] + A[i][1], DP[i-1][2] + A[i][1]);
            DP[i][2] = Math.min(DP[i-1][1] + A[i][2], DP[i-1][0] + A[i][2]);
        }
        res = Math.min(Math.min(res, DP[n - 1][0]), DP[n - 1][1]);

        System.out.println(res);

    }

}
