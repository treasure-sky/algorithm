import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int r1 = 0, r2 = 0;
        int sum = Integer.MAX_VALUE;

        int p1 = 0;
        int p2 = n - 1;
        while(p1 < p2){
            int num1 = arr[p1];
            int num2 = arr[p2];
            if(Math.abs(num1 + num2) < Math.abs(sum)){
                r1 = num1;
                r2 = num2;
                sum = num1 + num2;
            }
            if(num1 + num2 > 0){
                p2--;
            } else if (num1 + num2 < 0) {
                p1++;
            }else{
                break;
            }
        }
        System.out.println(r1 + " " + r2);
    }
}
