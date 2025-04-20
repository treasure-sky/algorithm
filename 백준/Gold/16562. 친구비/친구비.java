import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] friendCost = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            friendCost[i] = Integer.parseInt(st.nextToken());
        }
        Map<Integer, ArrayList<Integer>> friendLink = new HashMap<>();
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            if(from == to) continue;

            if(!friendLink.containsKey(from)) friendLink.put(from, new ArrayList<>());
            if(!friendLink.containsKey(to)) friendLink.put(to, new ArrayList<>());

            if(friendLink.get(from).contains(to) || friendLink.get(to).contains(from)) continue;
            friendLink.get(from).add(to);
            friendLink.get(to).add(from);

        }

        boolean[] visited = new boolean[n + 1];

        int sum = 0;

        for (int i = 1; i <= n ; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            int minCost = friendCost[i];
            while (!q.isEmpty()) {
                int cur = q.poll();
                minCost = Math.min(minCost, friendCost[cur]);
                ArrayList<Integer> list = friendLink.getOrDefault(cur, new ArrayList<>());
                for (Integer e : list) {
                    if(visited[e]) continue;
                    visited[e] = true;
                    q.add(e);
                }

            }
            sum += minCost;
        }

        if(k >= sum){
            System.out.println(sum);
        }else{
            System.out.println("Oh no");
        }

    }

}
