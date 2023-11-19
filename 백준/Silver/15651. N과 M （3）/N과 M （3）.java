import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    static int N, M;
    static int[] res;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        N = Integer.parseInt(str[0]);
        M = Integer.parseInt(str[1]);
        res = new int[M];
        bt(0);
        System.out.println(sb);
    }

    static void bt(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(res[i]).append(" ");
            }
            sb.append("\n");
        } else {
            for (int i = 1; i <= N; i++) {
                res[depth] = i;
                bt(depth + 1);
            }
        }
    }
}