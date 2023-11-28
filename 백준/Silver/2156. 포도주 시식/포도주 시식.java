import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] input = new int[n];
        int[] max = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = sc.nextInt();
        }
        if(n > 0) max[0] = input[0];
        if(n > 1) max[1] = input[0] + input[1];
        if(n > 2) max[2] = Math.max(max[1], Math.max(max[0] + input[2], input[1] + input[2]));
        for (int i = 3; i < n; i++) {
            max[i] = Math.max(max[i-1], Math.max(max[i-2] + input[i], max[i-3] + input[i] + input[i-1]));
        }
        System.out.println(max[n-1]);
    }
}
