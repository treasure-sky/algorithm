import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N, min;
    static int[] arr;
    static boolean[] possible;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        int sum = Arrays.stream(arr).sum();
        possible = new boolean[sum + 2]; // min 찾을 때 set 전체 합 까지 min이 안나올 때를 고려
        bt(0, 0);
        for (int i = 1; i < sum + 2; i++) {
            if (!possible[i]) {
                min = i;
                break;
            }
        }
        System.out.println(min);
    }


    static void bt(int depth, int acc) {
        if (depth == N) {
            possible[acc] = true;
        } else {
            bt(depth + 1, acc + arr[depth]); // 현재 원소 포함
            bt(depth + 1, acc); // 현재 원소 미포함
        }
    }

}
