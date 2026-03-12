import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());
        System.out.println(a + b - c);

        if (b > 9 && b < 100) {
            System.out.println(100 * a + b - c);
        } else if (b > 99 && b < 1000) {
            System.out.println(1000 * a + b - c);
        } else if (b == 1000) {
            System.out.println(10000 * a + b - c);
        }else{
            System.out.println(10 * a + b - c);
        }
    }

}
