import java.util.Scanner;

public class Main {
    static int n, r, c;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        r = sc.nextInt();
        c = sc.nextInt();

        int ptr = 0;
        while (n >= 1) {
            int temp = (int) Math.pow(2, n - 1);
            ptr += ((r / temp) * 2 + c / temp) * temp * temp;
            r %= temp;
            c %= temp;
            n--;
        }
        System.out.println(ptr);

    }
}
