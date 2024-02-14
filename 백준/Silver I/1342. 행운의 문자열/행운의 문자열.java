import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static Set<String> set = new HashSet<>();
    static int len = 0;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        len = str.length();

        Set<Character> exept = new HashSet<>();
        for (int i = 0; i < len; i++) {
            exept.add(str.charAt(i));
        }
        if (exept.size() == len) {
            System.out.println(factorial(len));
        } else if (exept.size() == len - 1) {
            System.out.println(factorial(len) / 2 - factorial(len - 1));
        } else {
            bt(new boolean[len], new StringBuilder(), 0);
            System.out.println(set.size());
        }
    }

    static void bt(boolean[] visited, StringBuilder sb, int size) {
        for (int i = 0; i < len; i++) {
            char now = str.charAt(i);
            // 안 쓰인 문자이며, 이전 문자와 다른 문자일 때 createdStr에 추가해 줌
            if (!visited[i] && (size == 0 || sb.charAt(size - 1) != now)) {
                sb.append(now);
                if (size == len - 1) { // 모든 문자가 사용되었을 때
                    set.add(sb.toString());
                } else {
                    visited[i] = true;
                    bt(visited, sb, size + 1);
                    visited[i] = false;
                }
                sb.deleteCharAt(sb.length() - 1);
            }

        }
    }

    static int factorial (int i) {
        if (i == 1) {
            return 1;
        } else {
            return i * factorial(i - 1);
        }
    }
}
