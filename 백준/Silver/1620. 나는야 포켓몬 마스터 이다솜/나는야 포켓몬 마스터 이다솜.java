import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Map<String, Integer> map1 = new HashMap<>();
        Map<Integer, String> map2 = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            String temp = br.readLine();
            map1.put(temp, i);
            map2.put(i, temp);
        }
        for (int j = 0; j < m; j++) {
            String str = br.readLine();
            if (isInteger(str)) { // 정수일 때
                System.out.println(map2.get(Integer.parseInt(str)));
            } else { // 문자열일 때
                System.out.println(map1.get(str));
            }
        }

    }

    static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
