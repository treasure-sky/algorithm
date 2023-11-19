import java.util.Scanner;

public class Main {
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        int[] set = new int[M];
        bt(0, 0, set);
    }

    static void bt(int idx, int cnt, int[] set) {
        if (cnt == M) { // M개를 고르면 출력.
            for (int i = 0; i < M; i++) {
                System.out.print(set[i] + " ");
            }
            System.out.println();
        } else { // 최대 M번만 호출 가능.
            for (int i = 1; i <= N; i++) {
                boolean contain = false;
                for (int j = 0; j < cnt ; j++){
                    if(set[j] == i) contain = true;
                }
                if (!contain) {
                    set[cnt] = i;
                    bt(idx + 1, cnt + 1, set);
                }
            }
        }

    }
}
