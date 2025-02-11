import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n <= 20) {
            StringBuilder sb = new StringBuilder();
            recursivePrint(1, 3, n, sb);
            System.out.println(cnt);
            System.out.println(sb);
        }else{
            System.out.println(BigInteger.valueOf(2).pow(n).subtract(BigInteger.ONE));
        }

    }

    public static void recursivePrint(int start, int end, int plate, StringBuilder sb){
        // 하나만 옮기는 경우
        if (plate == 1) {
            cnt++;
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        // 두 개 이상 옮겨야 하는 경우
        int mid = 6 - start - end; // 출발지와 목적지를 제외한 나머지 위치 (plate - 1 만큼의 원판이 경유하는 위치)
        recursivePrint(start, mid, plate - 1, sb);
        cnt++;
        sb.append(start).append(" ").append(end).append("\n");
        recursivePrint(mid, end, plate - 1, sb);
    }


}
