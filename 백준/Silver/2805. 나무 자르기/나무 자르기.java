import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] tree = new int[n];
        st = new StringTokenizer(br.readLine());
        int tallestTree = 0;
        for (int i = 0; i < n; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            tallestTree = Math.max(tallestTree, tree[i]);
        }

        int min = -1;
        int max = tallestTree;
        while (min != max) {
            int now = (min + max) / 2;
            if(now == min) break;
            long acc = 0;
            for (int i = 0; i < n; i++) {
                acc += Math.max((tree[i] - now), 0);
            }
            if (acc >= m) {
                min = now;
            } else {
                max = now;
            }
        }
        System.out.println(min);

    }
}
