import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] A;
    static int zeroCnt = 0;
    static int oneCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        A = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                int input = Integer.parseInt(st.nextToken());
                A[i][j] = (input == 1); // 1이면 true 0이면 false
            }
        }
        recursive(0, 0, n);
        System.out.println(zeroCnt);
        System.out.println(oneCnt);
    }

    static void recursive(int startRow, int startCol, int size) {
        boolean isSame = true;
        boolean whatColor = A[startRow][startCol];
        int endRow = startRow + size - 1;
        int endCol = startCol + size - 1;
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (whatColor != A[r][c]) { // 색이 전에 색과 다르면
                    isSame = false;
                    break;
                }
            }
            if(!isSame) break;
        }

        if (isSame) { // 색이 모두 같으면
            if (whatColor) {
                oneCnt++;
            } else {
                zeroCnt++;
            }
        } else {
            int halfOfSize = size / 2;
            recursive(startRow, startCol, halfOfSize);
            recursive(startRow + halfOfSize, startCol, halfOfSize);
            recursive(startRow, startCol + halfOfSize, halfOfSize);
            recursive(startRow + halfOfSize, startCol + halfOfSize, halfOfSize);
        }
    }
}
