import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int c = 0; c < tc; c++) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();
            StringTokenizer st;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                String type = st.nextToken();

                if (map.containsKey(type)) {
                    map.replace(type, map.get(type) + 1);
                } else {
                    map.put(type, 1);
                }
            }

            int res = 1;
            for (int val : map.values()) {
                res *= (val + 1);
            }
            System.out.println(res - 1);


        }
    }


}
