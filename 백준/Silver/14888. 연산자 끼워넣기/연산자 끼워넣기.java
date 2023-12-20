import java.util.Scanner;

public class Main {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;
    static int[] operator;
    static int[] num;
    static int n;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        num = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = sc.nextInt();
        }
        operator = new int[4];
        for (int i = 0; i < 4; i++) {
            operator[i] = sc.nextInt();
        }
        dfs(1, num[0]);
        System.out.println(max);
        System.out.println(min);
    }
    static void dfs(int currIndex, int prevNum) {
        if (currIndex == n) {
            if (max < prevNum) {
                max = prevNum;
            }
            if (min > prevNum) {
                min = prevNum;
            }
            return;
        }
        for (int i = 0; i < 4; i++) {
            if (operator[i] > 0) {
                int temp;
                switch (i) {
                    case 0 -> { // +
                        temp = prevNum + num[currIndex];
                        operator[0]--;
                        dfs(currIndex + 1, temp);
                        operator[0]++;
                    }
                    case 1 -> { // -
                        temp = prevNum - num[currIndex];
                        operator[1]--;
                        dfs(currIndex + 1, temp);
                        operator[1]++;
                    }
                    case 2 -> { // *
                        temp = prevNum * num[currIndex];
                        operator[2]--;
                        dfs(currIndex + 1, temp);
                        operator[2]++;
                    }
                    case 3 -> { // /
                        temp = prevNum / num[currIndex];
                        operator[3]--;
                        dfs(currIndex + 1, temp);
                        operator[3]++;
                    }
                }
            }

        }
    }
}
