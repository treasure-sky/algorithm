import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        System.out.println(pow(A % C, B, C));

    }

    public static long pow(long base, long exp, long mod) {
        if (exp == 1) {
            return base % mod;
        } else if(exp % 2 == 0) { // 짝수인 경우
            long divided = pow(base, exp / 2, mod);
            return divided * divided % mod;
        } else {
            long divided = pow(base, (exp - 1) / 2, mod);
            return (divided * divided % mod) * base % mod;
        }
    }

}
