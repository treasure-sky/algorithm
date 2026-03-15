import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static class Node{
        int min, max;
        Node left, right;

        Node(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    static class Result{
        int min, max;

        Result(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    static int[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        input = new int[n];
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(br.readLine());
        }

        Node root = build(0, n-1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            Result res = calc(root, 0, n - 1, from, to);
            sb.append(res.min).append(" ").append(res.max).append("\n");

        }
        System.out.println(sb);

    }

    static Result calc(Node node, int nodeStart, int nodeEnd, int from, int to) {
        if (to < nodeStart || nodeEnd < from) { // 겹치지 않는 경우
            return new Result(Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        if (from <= nodeStart && nodeEnd <= to) { // 완전히 겹치는 경우
            return new Result(node.min, node.max);
        }

        int mid = (nodeStart + nodeEnd) / 2;
        Result left = calc(node.left, nodeStart, mid, from, to);
        Result right = calc(node.right, mid + 1, nodeEnd, from, to);
        return new Result(Math.min(left.min, right.min), Math.max(left.max, right.max));
    }

    static Node build(int start, int end) {
        if (start == end) {
            return new Node(input[start], input[start]);
        }

        int mid = (start + end) / 2;
        Node left = build(start, mid);
        Node right = build(mid + 1, end);

        Node node = new Node(Math.min(left.min, right.min), Math.max(left.max, right.max));
        node.left = left;
        node.right = right;
        return node;
    }

}
