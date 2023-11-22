import java.util.Scanner;

public class Main {
    static int n, max;
    static int[] A;
    static boolean[] visited;
    static int[] res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }
        visited = new boolean[n];
        res = new int[n];
        max = Integer.MIN_VALUE;
        bf(0, new boolean[n]);
        System.out.println(max);


    }

    static void bf(int depth, boolean[] visited) {
        if (depth == n) {
            int sum = 0;
            for (int i = 0; i < n-1; i++) {
                sum += Math.abs(A[res[i]] - A[res[i+1]]);
            }
            if (sum > max) {
                max = sum;
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!visited[i]) {
                    res[depth] = i;
                    visited[i] = true;
                    bf(depth + 1, visited);
                    visited[i] = false;
                }
            }
        }
    }
}
