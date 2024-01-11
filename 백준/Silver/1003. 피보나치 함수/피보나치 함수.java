import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        int[] dpZero = new int[42]; // 각 인덱스에 해당하는 수가 출력하는 0의 개수
        // 수 i에 대한 1의 개수의 경우 dpZero[i+1] 로 구함
        dpZero[0] = 1;
        dpZero[1] = 0;
        for (int i = 2; i < 42; i++) {
            dpZero[i] = dpZero[i - 1] + dpZero[i - 2];
        }
        for (int i = 0; i < tc; i++) {
            int num = Integer.parseInt(br.readLine());
            System.out.println(dpZero[num] + " " + dpZero[num + 1]);
        }
    }
}
