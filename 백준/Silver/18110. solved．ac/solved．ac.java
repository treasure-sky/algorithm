import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] A = new int[n];
        int trash = (int) Math.round(n * 0.15);
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(A);
        int sum = 0;
        for (int i = trash; i < n - trash; i++) {
            sum += A[i];
        }
        System.out.println((int) Math.round((float) sum / (n - 2 * trash)));
    }
}
