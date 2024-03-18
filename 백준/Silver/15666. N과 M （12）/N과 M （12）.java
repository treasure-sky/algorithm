import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[] input;
    static int[] res;
    static int[] visited;
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
        visited = new int[n];
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
            for (int i = 0; i < n; i++) {
                int visitCnt = visited[i];
                if (visitCnt != 0) {
                    for (int j = 0; j < visitCnt; j++) {
                        sb2.append(input[i]).append(" ");
                    }
                }
            }
            sb2.append("\n");
            String resStr = sb2.toString();
            if (!set.contains(resStr)) {
                set.add(resStr);
                sb.append(resStr);
            }
        } else {
            for (int i = 0; i < n; i++) {
                visited[i]++;
                dfs(size + 1);
                visited[i]--;
            }

        }
    }
}
