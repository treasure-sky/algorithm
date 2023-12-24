import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            input[i] = sc.nextInt();
        }

        int[] A = new int[n + 1];
        int res = 0;

        for (int i = 1; i <= n; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (input[j] < input[i] && max < A[j]) {
                    max = A[j];
                }
            }
            A[i] = max + 1;
            if (res < A[i]) {
                res = A[i];
            }
        }
        System.out.println(res);

    }
}
