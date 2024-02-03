import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        Set<Integer> set = new HashSet<>();
        if (m != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) set.add(Integer.parseInt(st.nextToken()));
        }


        int min = Integer.MAX_VALUE;
        MAINFOR:
        for (int i = 0; i < 1000000; i++) {
            String str = Integer.toString(i);
            int strSize = str.length();
            for (int j = 0; j < strSize; j++) {
                if (set.contains(str.charAt(j) - '0')) continue MAINFOR; // 고장난 숫자가 있으면 고려X
            }
            min = Math.min(min, strSize + Math.abs(n - i)); // 기존 수 입력횟수 + up down 입력횟수 고려

        }
        min = Math.min(min, Math.abs(100 - n)); // 처음 시작 위치에서 up down키만 누를 경우 고려
        System.out.println(min);

    }
}
