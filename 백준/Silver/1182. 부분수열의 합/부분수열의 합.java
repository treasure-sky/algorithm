import java.util.Scanner;

public class Main {
    static int N, S, cnt;
    static int[] arr;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        S = sc.nextInt();
        cnt = 0;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        bt(0, 0, false);
        System.out.println(cnt);
    }


    static void bt(int depth, int acc, boolean plusCnt) {
        if (plusCnt && S == acc) {
            cnt++;
        }
        if(depth < N){
            bt(depth + 1, acc + arr[depth], true); // 현재 원소 포함
            bt(depth + 1, acc, false); // 현재 원소 미포함
        }
    }

}
