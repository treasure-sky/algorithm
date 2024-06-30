import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Integer>[] A = new ArrayList[n];
        List<Integer>[] DP = new ArrayList[n];

        // 입력과 DP 배열 초기화
        for (int i = 0; i < n; i++) {
            A[i] = new ArrayList<>();
            DP[i] = new ArrayList<>();
        }

        // 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < i + 1; j++) {
                A[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        // DP배열의 맨 밑 줄 초기화
        for (int i = 0; i < n; i++) {
            DP[n - 1].add(A[n - 1].get(i));
        }

        // DP 배열 채우기
        for (int i = n - 2; i >= 0 ; i--) {
            for (int j = 0; j < i + 1; j++) {
                DP[i].add(A[i].get(j) + (Math.max(DP[i+1].get(j), DP[i+1].get(j+1))));
            }
        }
        System.out.println(DP[0].get(0));

    }

}
