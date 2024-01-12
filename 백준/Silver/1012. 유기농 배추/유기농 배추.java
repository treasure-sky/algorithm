import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 문제의 행, 열을 반전시킴. */

public class Main {
    static int[][] A;
    static int m, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            StringTokenizer st1 = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st1.nextToken());
            n = Integer.parseInt(st1.nextToken());
            int k = Integer.parseInt(st1.nextToken());
            A = new int[m][n];
            StringTokenizer st2;
            for (int j = 0; j < k; j++) { // 배열에 배추 1로 지정
                st2 = new StringTokenizer(br.readLine());
                A[Integer.parseInt(st2.nextToken())][Integer.parseInt(st2.nextToken())] = 1;
            }
            int cnt = 0;
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    if (A[row][col] == 1) {
                        cnt++;
                        dfs(row, col);
                    }
                }
            }
            System.out.println(cnt);
        }
    }

    static void dfs(int i, int j) {
        A[i][j] = 0;
        if (i > 0 && A[i - 1][j] == 1) { // 위쪽에 배추가 있으면
            dfs(i - 1, j);
        }
        if (j > 0 && A[i][j - 1] == 1) { // 왼쪽에 배추가 있으면
            dfs(i, j - 1);
        }
        if (i < m - 1 && A[i + 1][j] == 1) { // 아래쪽에 배추가 있으면
            dfs(i + 1, j);
        }
        if (j < n - 1 && A[i][j + 1] == 1) { // 오른쪽에 배추가 있으면
            dfs(i, j + 1);
        }
    }
}
