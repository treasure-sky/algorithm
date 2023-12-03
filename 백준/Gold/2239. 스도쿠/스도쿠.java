import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 초기버전
public class Main {
    static int[][] A = new int[9][9];
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        // 입력된 숫자들을 이차원 배열 A에 넣어주기
        for (int i = 0; i < 9; i++) {
            String inputStr = bf.readLine();
            for (int j = 0; j < 9; j++) {
                A[i][j] = inputStr.charAt(j) - '0';
            }
        }
        bt(0, 0);
    }

    static void bt(int row, int col) {

        // 채워야 하는 다음 인덱스 찾기
        while (A[row][col] != 0) {
            col++;
            if (col == 9) {
                row++;
                col = 0;
                if (row == 9) {
                    for (int i = 0; i < 9; i++) {
                        for (int j = 0; j < 9; j++) {
                            System.out.print(A[i][j]);
                        }
                        System.out.println();
                    }
                    System.exit(0);
                }
            }
        }

        boolean[] exist = new boolean[10];

        // 세로줄 확인
        for (int i = 0; i < 9; i++) {
            if (A[i][col] != 0) {
                exist[A[i][col]] = true;
            }
        }

        // 가로줄 확인
        for (int i = 0; i < 9; i++) {
            if (A[row][i] != 0) {
                exist[A[row][i]] = true;
            }
        }

        // 사각형 확인
        int squareR = (row / 3) * 3;
        int squareC = (col / 3) * 3;
        for (int i = squareR; i < squareR + 3; i++) {
            for (int j = squareC; j < squareC + 3; j++) {
                if (A[i][j] != 0) {
                    exist[A[i][j]] = true;
                }
            }
        }

        for (int i = 1; i <= 9; i++) {
            if (!exist[i]) {
                A[row][col] = i;
                bt(row, col);
                A[row][col] = 0; // 복구
            }

        }
    }


}
// 어떻게 promising을 찾을 것인가? 에 대해
// 처음에는 어떤 하나의 값에 대해 promising한지 결정해서 넣고 재귀호출 하려고 했지만 효율이 안좋을 것 같아서
// 어떤 한 공간에 들어갈 수 있는 값들을 다 찾아서 그 값들만 넣어주기.

// 검사할 때 static boolean[] 으로 각 row, 각 col에 현재 있는 숫자 표시하면 나중에 다시 그 row나col 검사할 때 빠를 듯
