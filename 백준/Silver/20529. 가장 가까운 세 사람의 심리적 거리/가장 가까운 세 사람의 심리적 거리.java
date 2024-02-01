import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());

        while (tc-- > 0) {
            int n = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            if (n > 32) { // 비둘기집 원리로 32 초과시에 같은 mbti 3개 존재하는 경우가 무조건 있음. -> 거리 0
                System.out.println(0);
                continue;
            }
            String[] input = new String[n];
            for (int i = 0; i < n; i++) {
                input[i] = st.nextToken();
            }

            int min = 12;
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    for (int k = j + 1; k < n; k++) {
                        min = Math.min(min, getDistance(input[i], input[j], input[k]));
                    }
                }
            }
            System.out.println(min);
        }
    }

    static int getDistance(String str1, String str2, String str3) {
        int distance = 0;
        for (int i = 0; i < 4; i++) {
            distance += (str1.charAt(i) != str2.charAt(i)) ? 1 : 0;
            distance += (str1.charAt(i) != str3.charAt(i)) ? 1 : 0;
            distance += (str2.charAt(i) != str3.charAt(i)) ? 1 : 0;
        }
        return distance;
    }
}
