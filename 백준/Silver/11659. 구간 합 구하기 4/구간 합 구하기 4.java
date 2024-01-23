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
        int[] A = new int[n + 1];
        int[] acc = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            acc[i] = acc[i - 1] + A[i];
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            System.out.println(-acc[Integer.parseInt(st.nextToken()) - 1] + acc[Integer.parseInt(st.nextToken())]);
        }

    }

}
