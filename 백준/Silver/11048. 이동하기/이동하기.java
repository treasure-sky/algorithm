import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        byte[][] A = new byte[n + 1][m + 1];
        int[][] DP = new int[n + 1][m + 1]; // DP배열을 채울 때 경계부분을 따로 처리하지 않기 위해 margin을 한 칸씩 둠.
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= m; j++) {
                A[i][j] = Byte.parseByte(st.nextToken());
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                DP[i][j] = Math.max(Math.max(DP[i - 1][j - 1], DP[i - 1][j]), DP[i][j - 1]) + A[i][j];
            }
        }
        System.out.println(DP[n][m]);
    }
}
