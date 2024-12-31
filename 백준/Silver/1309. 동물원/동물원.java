import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] A = new int[n + 1][3];
        A[1][0] = A[1][1] = A[1][2] = 1;
        final int MOD = 9901;

        for (int i = 2; i <= n; i++) {
            A[i][0] = (A[i - 1][0] + A[i - 1][1] + A[i - 1][2]) % MOD;
            A[i][1] = (A[i - 1][0] + A[i - 1][2]) % MOD;
            A[i][2] = (A[i - 1][0] + A[i - 1][1]) % MOD;
        }
        System.out.println((A[n][0] + A[n][1] + A[n][2]) % MOD);

    }
}
