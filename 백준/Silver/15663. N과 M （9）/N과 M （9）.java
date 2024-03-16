import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] input;
    static int[] res;
    static boolean[] visited;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        input = new int[n];
        res = new int[m];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);

        dfs(0);

        System.out.println(sb.toString());
    }

    public static void dfs(int size) {
        if (size == m) {
            StringBuilder sb2 = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb2.append(res[i]).append(" ");
            }
            sb2.append("\n");
            String resStr = sb2.toString();
            if (!set.contains(resStr)) {
                set.add(resStr);
                sb.append(sb2);
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    res[size] = input[i];
                    visited[i] = true;
                    dfs(size + 1);
                    visited[i] = false;
                }
            }

        }
    }
}
