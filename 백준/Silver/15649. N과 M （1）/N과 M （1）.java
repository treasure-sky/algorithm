import java.util.Scanner;

public class Main {
    static int N, M;
    static boolean[] visited;
    static int[] res;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        visited = new boolean[N+1];
        res = new int[M];
        bt(0);
    }

    static void bt(int depth) {
        if (depth == M) { // M개를 고르면 출력.
            for (int i = 0; i < M; i++) {
                System.out.print(res[i] + " ");
            }
            System.out.println();
        } else {
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    res[depth] = i;
                    bt(depth + 1);
                    visited[i] = false;
                }
            }
        }

    }
}
