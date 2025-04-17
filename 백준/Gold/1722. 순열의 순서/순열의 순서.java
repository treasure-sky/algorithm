import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        // fact[5] = 5!
        long[] fact = new long[n+1];
        fact[0] = 1;
        for(int i = 1; i <= n; i++){
            fact[i] = fact[i-1] * i;
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i <= n; i++){
            list.add(i);
        }

        if(m == 1){
            long k = Long.parseLong(st.nextToken());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < n; i++){
//                if(k > fact[n - i - 1]){
//                    int temp = (int) (k / fact[n - i - 1]);
////                    if (k % fact[n - i - 1] == 0) temp -= 1;
//                    sb.append(list.get(temp)).append(" ");
//                    list.remove(temp);
//                    k -= temp * fact[n - i - 1];
//                }else{
//                    sb.append(list.get(0)).append(" ");
//                    list.remove(0);
//                }
                long f = fact[n - i - 1];     // (n-i-1)!
                int idx = (int) ((k - 1) / f); // 0‑based 인덱스
                sb.append(list.get(idx)).append(' ');
                list.remove(idx);
                k -= idx * f;
            }

            System.out.println(sb);


        }else{
            int[] input = new int[n];
            for(int i = 0; i < n; i++) input[i] = Integer.parseInt(st.nextToken());

            long ans = 1;
            for(int i = 0; i < n; i++){
                int cur = input[i];
                int curIdx = list.indexOf(Integer.valueOf(cur)); // cur이 후보 중 어디에 위치해있는지
                ans += curIdx * fact[n - i - 1];
                list.remove(curIdx);
            }
            System.out.println(ans);
        }
    }

}
