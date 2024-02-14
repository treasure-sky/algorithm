import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {


    static int len;
    static int cnt = 0;
    static String str;
    static char[] alp = new char[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        len = str.length();
        for (char c : str.toCharArray()) alp[c - 'a']++;
        bt(0, -1);
        System.out.println(cnt);

    }

    static void bt(int size, int pre) {
        for (int i = 0; i < 26; i++) {
            if (alp[i] != 0 && pre != i) { // 해당 문자가 남아있고 전에 사용되지 않았을 때
                if (size == len - 1) { // 종료조건
                    cnt++;
                    break;
                }
                alp[i]--;
                bt(size + 1, i);
                alp[i]++;
            }
        }
    }


}
