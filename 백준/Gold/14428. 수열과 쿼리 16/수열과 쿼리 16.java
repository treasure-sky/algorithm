import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] input;
    static int[][] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        input = new int[n];
        tree = new int[n*4][2];

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
                sb.append(query(1, 0, n - 1, b - 1, c - 1)[0] + 1);
                sb.append("\n");
            }
        }
        System.out.println(sb);

    }

    static int[] build(int node, int start, int end) {
        if (start == end) {
            tree[node][0] = start;
            tree[node][1] = input[start];
            return new int[]{tree[node][0], tree[node][1]};
        }

        int mid = (start + end) / 2;
        int[] leftMin = build(node * 2, start, mid);
        int[] rightMin = build(node * 2 + 1, mid + 1, end);

        if (leftMin[1] < rightMin[1]) {
            tree[node][0] = leftMin[0];
            tree[node][1] = leftMin[1];
        } else if (leftMin[1] > rightMin[1]) {
            tree[node][0] = rightMin[0];
            tree[node][1] = rightMin[1];
        }else{
            if(leftMin[0] < rightMin[0]){
                tree[node][0] = leftMin[0];
                tree[node][1] = leftMin[1];
            }else{
                tree[node][0] = rightMin[0];
                tree[node][1] = rightMin[1];
            }
        }
        return new int[]{tree[node][0], tree[node][1]};
    }

    static int[] update(int node, int start, int end, int targetIdx, int updateValue) {
        if (targetIdx < start || end < targetIdx) {
            return tree[node];
        }

        if (start == end) {
            tree[node][0] = targetIdx;
            tree[node][1] = updateValue;
            return tree[node];
        }

        int mid = (start + end) / 2;
        int[] leftMin = update(node * 2, start, mid, targetIdx, updateValue);
        int[] rightMin = update(node * 2 + 1, mid + 1, end, targetIdx, updateValue);

        if (leftMin[1] < rightMin[1]) {
            tree[node][0] = leftMin[0];
            tree[node][1] = leftMin[1];
        } else if (leftMin[1] > rightMin[1]) {
            tree[node][0] = rightMin[0];
            tree[node][1] = rightMin[1];
        }else{
            if(leftMin[0] < rightMin[0]){
                tree[node][0] = leftMin[0];
                tree[node][1] = leftMin[1];
            }else{
                tree[node][0] = rightMin[0];
                tree[node][1] = rightMin[1];
            }

        }
        return new int[]{tree[node][0], tree[node][1]};
    }

    static int[] query(int node, int start, int end, int queryStart, int queryEnd) {
        if (end < queryStart || queryEnd < start) {
            return new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        }

        if (queryStart <= start && end <= queryEnd) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        int[] leftMin = query(node * 2, start, mid, queryStart, queryEnd);
        int[] rightMin = query(node * 2 + 1, mid + 1, end, queryStart, queryEnd);

        int[] res = new int[2];

        if (leftMin[1] < rightMin[1]) {
            res[0] = leftMin[0];
            res[1] = leftMin[1];
        } else if (leftMin[1] > rightMin[1]) {
            res[0] = rightMin[0];
            res[1] = rightMin[1];
        }else{
            if(leftMin[0] < rightMin[0]){
                res[0] = leftMin[0];
                res[1] = leftMin[1];
            }else{
                res[0] = rightMin[0];
                res[1] = rightMin[1];
            }

        }
        return new int[]{res[0], res[1]};

    }

}
