import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 질문의 수 까지 입력 받기
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int q = Integer.parseInt(br.readLine());
        int inputSize = input.length();

        // DP 배열 채우기
        int[][] dp = new int[26][inputSize + 1]; // dp[0][5] : 주어진 문자열에서 첫번째 문자에서 5번째 문자 사이에 a 문자 개수
        for (int i = 1; i <= inputSize; i++) {
            for (int j = 0; j < 26; j++) { // 이전 문자와 동일하게 적용
                dp[j][i] = dp[j][i - 1];
            }
            // i번째 문자는 이전값 + 1 적용
            dp[input.charAt(i - 1) - 'a'][i] = dp[input.charAt(i - 1) - 'a'][i - 1] + 1;
        }


        // 문제 입력받아 해결
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            char a = st.nextToken().charAt(0);
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            sb.append(dp[a - 'a'][r + 1] - dp[a - 'a'][l]).append("\n");
        }
        System.out.println(sb);
    }
}
