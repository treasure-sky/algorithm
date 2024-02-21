import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        String str = br.readLine();


        int ptr = 0;
        int cnt = 0;
        while (true) {
            if(ptr + 2 > m) break; // 더이상 패턴이 없는 경우
            if (str.charAt(ptr) == 'I') { // I로 시작하면
                int temp = 0; // 방문한 Pn의 n값
                while (ptr + 2 < m && str.charAt(ptr + 1) == 'O' && str.charAt(ptr + 2) == 'I') { // 다음 패턴이 존재
                    ptr += 2;
                    temp++;
                }
                if (temp >= n) {
                    cnt += temp - n + 1;
                }
            }
            ptr++;
        }
        System.out.println(cnt);

    }
}
