import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[] used; // 0 - 9 중 사용된 숫자를 저장
    static long num = 0; // 현재 숫자
    static boolean[] lt;
    static long maxNum = Long.MIN_VALUE;
    static long minNum = Long.MAX_VALUE;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        lt = new boolean[k + 1]; // less than을 뜻하는 배열, '<' 면 true, '>' 면 false
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            lt[i] = st.nextToken().equals("<");
        }
        for (int i = 0; i < 10; i++) {
            used = new boolean[10];
            used[i] = true;
            num = i;
            bt(0);
        }

        System.out.printf("%0" + (k + 1) + "d%n", maxNum);
        System.out.printf("%0" + (k + 1) + "d%n", minNum);
    }

    public static void bt (int level){
        if (level == k){
            maxNum = Math.max(maxNum, num);
            minNum = Math.min(minNum, num);
            return;
        }

        if (lt[level]) { // '<' 이면
            for (int i = (int) num % 10 + 1; i < 10; i++) {
                if(used[i]) continue;
                used[i] = true;
                num = num * 10 + i;
                bt(level + 1);
                num = num / 10;
                used[i] = false;
            }
        } else { // '>' 이면
            for (int i = (int) num % 10 - 1; i >= 0; i--) {
                if(used[i]) continue;
                used[i] = true;
                num = num * 10 + i;
                bt(level + 1);
                num = num / 10;
                used[i] = false;
            }
        }
    }
}
