import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long[] input;
    static long[] sum;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        input = new long[n];
        sum = new long[4 * n];
        for (int i = 0; i < n; i++) {
            input[i] = Long.parseLong(br.readLine());
        }

        build(1, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());
            if (a == 1) {
                update(1, 0, n - 1, b - 1, c);
            }else{
                sb.append(query(1, 0, n - 1, b - 1, (int) c - 1)).append("\n");
            }
        }
        System.out.println(sb);
    }

    static long build(int node, int start, int end) {
        if (start == end) {
            sum[node] = input[start];
            return sum[node];
        }

        int mid = (start + end) / 2;
        long leftSum = build(node * 2, start, mid);
        long rightSum = build(node * 2 + 1, mid + 1, end);
        sum[node] = leftSum + rightSum;
        return sum[node];
    }

    static long update(int node, int start, int end, int targetIdx, long newValue){
        if (targetIdx < start || end < targetIdx) {
            return sum[node];
        }

        if (start == end){
            sum[node] = newValue;
            return sum[node];
        }
        int mid = (start + end) / 2;
        return sum[node] = update(node * 2, start, mid, targetIdx, newValue) + update(node * 2 + 1, mid + 1,
            end, targetIdx, newValue);
    }

    static long query(int node, int start, int end, int rangeStart, int rangeEnd) {
        if (rangeEnd < start || end < rangeStart) {
            return 0;
        }

        if (rangeStart <= start && end <= rangeEnd) {
            return sum[node];
        }

        int mid = (start + end) / 2;
        return query(node * 2, start, mid, rangeStart, rangeEnd) + query(node * 2 + 1, mid + 1, end,
            rangeStart, rangeEnd);
    }

}
