import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = new int[n + 3];
        for (int i = 3; i < n + 3; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }
        int[] A = new int[n + 3];
        for (int i = 3; i < n + 3; i++) {
            A[i] = input[i] + Math.max(input[i - 1] + A[i - 3], A[i - 2]);
        }
        System.out.println(A[n + 2]);
    }
}
