import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        int[] x = new int[n + 1];
        int[] y = new int[n + 1];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        x[n] = x[0];
        y[n] = y[0];

        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            sum += (long) x[i] * (long) y[i + 1];
            sum -= (long) x[i + 1] * (long) y[i];
        }

        sum = Math.abs(sum) * 0.5;

        System.out.printf("%.1f", sum);

    }
}
