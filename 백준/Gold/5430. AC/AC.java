import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        MAINWHILE:
        while (tc-- > 0) {
            String cmd = br.readLine();
            int size = Integer.parseInt(br.readLine());
            String num = br.readLine();
            String[] arr = num.substring(1, num.length() - 1).split(",");
            int sp = 0; // start pointer
            int ep = arr.length - 1; // end pointer
            boolean isForward = true;
            for (int i = 0; i < cmd.length(); i++) {
                if (cmd.charAt(i) == 'R') {
                    isForward = !isForward;
                } else if (size == 0) { // D인데 숫자 없을 때
                    System.out.println("error");
                    continue MAINWHILE;
                } else { // D인데 숫자 있을 때
                    if (isForward) { // 정방향일 때
                        sp++;
                    } else { // 역방향일 때
                        ep--;
                    }
                    size--;
                }
            }

            if (size == 0) {
                System.out.println("[]");
                continue;
            }

            StringBuilder sb = new StringBuilder("[");
            if (isForward) {
                for (int i = sp; i < ep; i++) {
                    sb.append(arr[i]).append(",");
                }
                sb.append(arr[ep]).append("]");
            } else {
                for (int i = ep; i > sp; i--) {
                    sb.append(arr[i]).append(",");
                }
                sb.append(arr[sp]).append("]");
            }
            System.out.println(sb.toString());

        }
    }
}
