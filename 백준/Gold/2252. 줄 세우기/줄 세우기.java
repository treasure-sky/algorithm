import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Queue<Integer> queue = new LinkedList<>();
        int[] indegree = new int[n + 1];
        StringBuilder sb = new StringBuilder("");

        for (int i = 1; i < m + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if(!adj.containsKey(from)) adj.put(from, new HashSet<>());

            Set<Integer> set = adj.get(from);
            set.add(to);
            indegree[to]++;
        }

        for (int i = 1; i < n + 1; i++) {
            if(indegree[i] == 0) queue.add(i);
        }

        while (!queue.isEmpty()) {
            int nowNode = queue.poll();
            sb.append(nowNode).append(" ");
            Set<Integer> linkedNodes = adj.get(nowNode);
            if(linkedNodes == null) continue;
            for (int linkedNode : linkedNodes) {
                indegree[linkedNode]--;
                if (indegree[linkedNode] == 0) {
                    queue.add(linkedNode);
                }
            }
        }

        System.out.print(sb);
    }
}
