import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int back = 0, front = 0;
        int sum = A[0];
        int minLength = Integer.MAX_VALUE;

        //T A0가 s보다 크면 조기종료
        if(A[0] >= s){
            System.out.print("1");
            System.exit(0);
        }

        // (0 1 2 ... n-2 n-1) n
        //      ^이 범위가 수열, n부터는 오바
        while (front < n && back < n) {
            if (sum < s) { // s보다 범위 합이 작은 경우
                front++;
                if(front < n) sum += A[front];
            } else { // s보다 범위 합이 큰 경우
                if(front - back + 1 < minLength){
                    minLength = front - back + 1;
                }
                sum -= A[back];
                back++;
            }
        }

        if(back == 0 && front == n){
            System.out.print("0");
        }else{
            System.out.print(minLength);
        }

        //T 못 찾은거 고려
    }
}
