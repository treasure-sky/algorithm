
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Main {

    static int n, m;
    static int[] arr;
    static int[] input;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[m];
        input = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(input);

        bt(0, 0);
        System.out.print(sb.toString());

    }

    static public void bt(int ptr, int size){
        if (size == m) {
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
        } else {
            arr[size] = input[ptr];
            bt(ptr, size + 1);
            if (ptr < n - 1) {
                bt(ptr + 1, size);
            }
        }
    }

}