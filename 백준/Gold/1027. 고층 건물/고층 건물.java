import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) { // i번 째 건물 기준
            int iMax = 0;

            for (int j = 0; j < i; j++) { // 왼쪽 건물 탐색
                double m = (double) (b[i] - b[j]) / (i - j); // 기울기

                boolean isPossible = true;
                for (int k = j + 1; k < i; k++) {
                    double lineHeight = m * (k - j) + b[j]; // k건물 위치에서 선분의 높이
                    if(lineHeight <= b[k]){
                        isPossible = false;
                        break;
                    }
                }
                if(isPossible) iMax++;

            }

            for(int j = i+1; j < n; j++){ // 오른쪽 건물 탐색
                double m = (double) (b[j] - b[i]) / (j - i); // 기울기
                boolean isPossible = true;
                for (int k = i + 1; k < j; k++) {
                    double lineHeight = m * (k - i) + b[i]; // k건물 위치에서 선분의 높이
                    if(lineHeight <= b[k]){
                        isPossible = false;
                        break;
                    }
                }
                if(isPossible) iMax++;
            }

            max = Math.max(max, iMax);

        }
        System.out.println(max);
    }
}
