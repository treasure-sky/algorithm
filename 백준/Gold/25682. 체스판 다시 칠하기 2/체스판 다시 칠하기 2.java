import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        int n, m, k;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[][] A1 = new int[n + 1][m + 1];
        int[][] A2 = new int[n + 1][m + 1];
        int[][] acc1 = new int[n + 1][m + 1];
        int[][] acc2 = new int[n + 1][m + 1];


        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                A1[i][j] = (((i + j) % 2 == 0) == (line.charAt(j - 1) == 'B')) ? 1 : 0;
                A2[i][j] = (((i + j) % 2 == 0) == (line.charAt(j - 1) == 'W')) ? 1 : 0;
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                acc1[i][j] = acc1[i - 1][j] + acc1[i][j - 1] - acc1[i - 1][j - 1] + A1[i][j];
                acc2[i][j] = acc2[i - 1][j] + acc2[i][j - 1] - acc2[i - 1][j - 1] + A2[i][j];
            }
        }

        int res = Integer.MAX_VALUE;

        // 체스판의 가장 오른쪽 밑 좌표 최솟값 = k
        for (int i = k; i <= n; i++) {
            for (int j = k; j <= m; j++) {
                int temp1 = acc1[i][j] - acc1[i-k][j] - acc1[i][j-k] + acc1[i-k][j-k];
                int temp2 = acc2[i][j] - acc2[i-k][j] - acc2[i][j-k] + acc2[i-k][j-k];
                res = Math.min(res, Math.min(temp1, temp2));
            }

        }

        System.out.println(res);

    }
}
