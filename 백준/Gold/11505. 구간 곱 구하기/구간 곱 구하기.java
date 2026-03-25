import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] input;
    static int[] tree;
    static long DIV_VALUE = 1_000_000_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        input = new int[n];
        tree = new int[4 * n];

        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        build(1, 0, n - 1);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m + k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(1, 0, n - 1, b - 1, c);
            }else{
                sb.append(query(1, 0, n - 1, b - 1, c - 1));
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }

    static int build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = input[start];
        }
        int mid = (start + end) / 2;
        int leftMul = build(node * 2, start, mid);
        int rightMul = build(node * 2 + 1, mid + 1, end);
        int res = (int) ((long) leftMul * (long) rightMul % DIV_VALUE);
        return tree[node] = res;
    }

    static int update(int node, int start, int end, int targetIdx, int updateValue) {
        if (targetIdx < start || end < targetIdx) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = updateValue;
        }

        int mid = (start + end) / 2;
        int leftMul = update(node * 2, start, mid, targetIdx, updateValue);
        int rightMul = update(node * 2 + 1, mid + 1, end, targetIdx, updateValue);
        int res = (int) ((long) leftMul * (long) rightMul % DIV_VALUE);
        return tree[node] = res;
    }

    static int query(int node, int start, int end, int queryStart, int queryEnd) {
        if (end < queryStart || queryEnd < start) {
            return 1;
        }
        if (queryStart <= start && end <= queryEnd) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int leftQuery = query(node * 2, start, mid, queryStart, queryEnd);
        int rightQuery = query(node * 2 + 1, mid + 1, end, queryStart, queryEnd);
        int res = (int) ((long) leftQuery * (long) rightQuery % DIV_VALUE);
        return res;
    }

}
