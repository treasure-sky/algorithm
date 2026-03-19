import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long REMAIN_VALUE = 1_000_000_007;
    static long[] cache;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] A = new long[n];
        cache = new long[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(A);

        long acc = 0L;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                acc += (two(j - i - 1) * ((A[j] - A[i]) % REMAIN_VALUE)) % REMAIN_VALUE;
            }
        }
        System.out.println(acc % REMAIN_VALUE);


    }

    static long two(int n) {
        if (n == 0) {
            return 1;
        }
        if (cache[n] != 0) {
            return cache[n];
        }
        long res = 1;
        for (int i = 0; i < n; i++) {
            res *= 2;
            res %= REMAIN_VALUE;
        }
        cache[n] = res;
        return res;
    }

}
