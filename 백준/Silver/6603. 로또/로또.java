import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int k = sc.nextInt();
            if(k == 0) break;
            int[] num = new int[k];
            for (int i = 0; i < k; i++) {
                num[i] = sc.nextInt();
            }
            bt(0, 0, new int[6], num);
            System.out.println();
        }
    }

    static void bt(int depth, int cnt, int[] pick, int[] num) {
        if (num.length - depth + cnt >= 6) { // 가능성이 있으면 (남아있는 원소개수와 선택한 원소개수의 합이 6 이상일 때)
            if (cnt == 6) { // 종료조건 (6개 다 골랐을 때)
                for (int i = 0; i < 6; i++) {
                    System.out.print(pick[i] + " ");
                }
                System.out.println();
            } else {
                pick[cnt] = num[depth];
                bt(depth + 1, cnt + 1, pick, num);
                bt(depth + 1, cnt, pick, num); // 해당 depth의 원소 선택 안함
            }
        }
    }
}
