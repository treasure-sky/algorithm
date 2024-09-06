import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int to;
        int distance;

        public Node(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return distance - o.distance;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());
        boolean[] visited = new boolean[v + 1];
        List<Node>[] list;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        int[] minDis = new int[v + 1];
        Arrays.fill(minDis,Integer.MAX_VALUE);

        list = new ArrayList[v + 1];

        for (int i = 0; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            list[from].add(new Node(to, distance));
        }


        queue.add(new Node(k, 0));
        minDis[k] = 0;
        while (!queue.isEmpty()){
            Node nowNode = queue.poll();
            int nowIdx = nowNode.to;
            int nowMinDis = nowNode.distance;
            if(visited[nowIdx]) continue;
            visited[nowIdx] = true;
            for(Node nextNode : list[nowIdx]){
                if(!visited[nextNode.to] && minDis[nextNode.to] > nowMinDis + nextNode.distance){
                    minDis[nextNode.to] = Math.min(minDis[nextNode.to], nowMinDis + nextNode.distance);
                    queue.add(new Node(nextNode.to, minDis[nextNode.to]));
                }
            }

        }

        StringBuilder sb = new StringBuilder("");
        for (int i = 1; i < v + 1; i++) {
            if (minDis[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            }else{
                sb.append(minDis[i]);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());




    }
}
