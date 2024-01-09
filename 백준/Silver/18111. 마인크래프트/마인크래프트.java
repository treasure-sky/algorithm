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
        int b = Integer.parseInt(st.nextToken());
        int blockSum = b;
        int[][] A = new int[n][m];

        for (int row = 0; row < n; row++) {
            st = new StringTokenizer(br.readLine());
            for (int col = 0; col < m; col++) {
                A[row][col] = Integer.parseInt(st.nextToken());
                blockSum += A[row][col];
            }
        }
        int maxHeight = Math.min(256, blockSum / (n * m)); // 만들 수 있는 가장 높은 평지의 높이
        int minTime = Integer.MAX_VALUE;
        int optHeight = -1;
        for (int h = 0; h <= maxHeight; h++) { // h높이의 평지를 만드는 경우
            int time = 0;
            for (int row = 0; row < n; row++) {
                for (int col = 0; col < m; col++) {
                    if (h > A[row][col]) { // 만드려는 높이보다 낮으면
                        time += h - A[row][col];
                    } else {
                        time += 2 * (A[row][col] - h);
                    }
                }
            }
            if (minTime >= time) {
                minTime = time;
                optHeight = h;
            }
        }

        System.out.println(minTime + " " + optHeight);

    }
}
