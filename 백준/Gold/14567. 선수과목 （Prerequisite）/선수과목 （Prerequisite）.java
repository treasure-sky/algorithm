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
        Queue<int[]> queue = new LinkedList<>(); // {노드 번호, 학기}
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        int[] indegree = new int[n + 1];
        int[] res = new int[n + 1];
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            indegree[to]++;

            Set<Integer> fromSet = adj.get(from);
            if(fromSet == null){
                adj.put(from, new HashSet<>());
                fromSet = adj.get(from);
            }
            fromSet.add(to);
        }

        for (int i = 1; i < n + 1; i++) {
            if(indegree[i] == 0) queue.add(new int[]{i, 1});
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int nowNode = now[0];
            int semester = now[1];
            res[nowNode] = semester;
            Set<Integer> linkedNodes = adj.get(nowNode);
            if(linkedNodes == null) continue;
            for (int linkedNode : linkedNodes){
                indegree[linkedNode]--;
                if(indegree[linkedNode] == 0){
                    queue.add(new int[]{linkedNode, semester + 1});
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            sb.append(res[i]).append(" ");
        }
        System.out.print(sb);


    }
}
