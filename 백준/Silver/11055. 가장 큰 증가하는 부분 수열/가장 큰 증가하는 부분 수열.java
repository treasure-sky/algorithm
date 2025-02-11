import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n]; // dp[i] : input[i]를 마지막 원소로 가지는 부분수열의 최대 합
        for (int i = 0; i < n; i++) { // 자기 자신을 원소로 가질 경우
            dp[i] = input[i];
        }
        for (int i = 1; i < n; i++) {
            int tempMax = input[i]; // 자기 자신만을 원소로 가지는 부분 수열의 합으로 초기화
            for (int j = 0; j < i; j++) {
                if(input[j] < input[i]){ // j번째 원소가 i번째 원소보다 작으면 j번째 원소를 마지막으로 하는 부분 수열에 i번째 원소를 추가할 수 있음
                    tempMax = Math.max(tempMax, dp[j] + input[i]);
                }
            }
            dp[i] = tempMax;
        }
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dp[i]);
        }
        System.out.println(res);
        
    }
}
