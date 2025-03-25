import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static StringBuilder sb = new StringBuilder();
    static int moveCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        hanoi(1, 3, n);
        System.out.println(moveCnt);
        System.out.println(sb);

    }

    public static void hanoi(int from, int to, int cnt){
        if (cnt == 1) {
            sb.append(from).append(" ").append(to).append("\n");
            moveCnt++;
            return;
        }
        hanoi(from, 6 - from - to, cnt - 1);
        hanoi(from, to, 1);
        hanoi(6 - from - to, to, cnt - 1);
    }

}
