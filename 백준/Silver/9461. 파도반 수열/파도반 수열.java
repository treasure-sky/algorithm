import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        long[] A = new long[101];
        A[1] = 1;
        A[2] = 1;
        A[3] = 1;
        for (int i = 4; i <= 100; i++) {
            A[i] = A[i - 2] + A[i - 3];
        }

        while (tc-- > 0) {
            System.out.println(A[Integer.parseInt(br.readLine())]);
        }

    }
}
