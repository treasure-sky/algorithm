import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] input;
    static int[][] tree;
    static int NULL = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        input = new int[n];
        tree = new int[4 * n][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        build(1, 0, n - 1);

        StringBuilder sb = new StringBuilder();
        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == 1) {
                update(1, 0, n - 1, b - 1, c);
            }else{
                int[] q = query(1, 0, n - 1, b - 1, c - 1);
                sb.append(q[0] + q[1]).append("\n");
            }
        }
        System.out.println(sb);


    }

    static int[] build(int node, int start, int end) {
        if (start == end) {
            return tree[node] = new int[]{input[start], NULL};
        }

        int mid = (start + end) / 2;
        int[] left = build(node * 2, start, mid);
        int[] right = build(node * 2 + 1, mid + 1, end);
        int[] merged = merge(left, right);
        return tree[node] = merged;
    }

    static int[] update(int node, int start, int end, int targetIdx, int updateValue) {
        if (targetIdx < start || end < targetIdx) {
            return tree[node];
        }

        if (start == end) {
            return tree[node] = new int[]{updateValue, NULL};
        }

        int mid = (start + end) / 2;
        int[] left = update(node * 2, start, mid, targetIdx, updateValue);
        int[] right = update(node * 2 + 1, mid + 1, end, targetIdx, updateValue);
        int[] merged = merge(left, right);
        return tree[node] = merged;

    }

    static int[] query(int node, int start, int end, int queryStart, int queryEnd) {
        if (queryEnd < start || end < queryStart) {
            return new int[]{NULL, NULL};
        }

        if (queryStart <= start && end <= queryEnd) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int[] left = query(node * 2, start, mid, queryStart, queryEnd);
        int[] right = query(node * 2 + 1, mid + 1, end, queryStart, queryEnd);
        return merge(left, right);
    }

    static int[] merge(int[] left, int[] right) {
        int first = NULL;
        int second = NULL;

        int[] candidates = {left[0], left[1], right[0], right[1]};

        for (int x : candidates) {
            if (x > first) {
                second = first;
                first = x;
            }else if(x > second){
                second = x;
            }
        }

        return new int[]{first, second};
    }

}
