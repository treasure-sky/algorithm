import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int L = sc.nextInt();
        long H = 0;
        String str = sc.next();
        for (int i = 0; i < L; i++) {
            long r = 1;
            for (int j = 0; j < i; j++) {
                r = r * 31 % 1234567891;
            }
            H = (H + (str.charAt(i) - 'a' + 1) * r) % 1234567891;
        }
        System.out.println(H);

    }
}
