import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] inputNums = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n + 1; i++) {
            inputNums[i] = Integer.parseInt(st.nextToken());
        }

        boolean[][] A = new boolean[n + 1][n + 1];

        // 대각선과 그 바로 밑에 선을 true로 함(이후 이를 이용해 다른 요소들을 판단 예정)
        for (int i = 1; i < n + 1; i++) {
            A[i][i-1] = true;
            A[i][i] = true;
        }

        // Bottom up 방식
        for (int i = n - 1; i > 0 ; i--) { // 맨 밑에서 두 번째 부터 시작
            for (int j = i + 1; j < n + 1; j++) { // 대각선 우측 요소부터 시작
                if(!A[i+1][j-1]) continue; // 좌측 하단이 true가 아니면 pass

                // 좌 우측에 추가된 값들이 일치하면 true
                if (inputNums[i] == inputNums[j]) {
                    A[i][j] = true;
                }
            }
        }


        int m = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            if (A[s][e]) {
                sb.append(1).append("\n");
                continue;
            }

            sb.append(0).append("\n");
        }

        System.out.println(sb);
            
    }
}
