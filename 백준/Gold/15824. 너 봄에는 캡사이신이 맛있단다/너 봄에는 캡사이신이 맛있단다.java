import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long REMAIN_VALUE = 1_000_000_007;
    static long[] exp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        exp = new long[n];
        exp[0] = 1;
        for (int i = 1; i < n; i++) {
            exp[i] = exp[i - 1] * 2 % REMAIN_VALUE;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(A);

        long acc = 0L;

        for (int i = 0; i < n; i++) {
            acc = (acc + exp[i] * A[i]) % REMAIN_VALUE;
            acc = (acc - exp[n - 1 - i] * A[i]) % REMAIN_VALUE;
        }

        if (acc < 0) {
            acc += REMAIN_VALUE;
        }

        System.out.println(acc % REMAIN_VALUE);

    }

}
