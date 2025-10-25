import java.util.*;
class Solution {
    // 단순 BFS + depth 카운트
    public int solution(int n, int[][] edge) {
        
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        for(int[] e : edge){
            if(adj.get(e[0]) == null){ // 노드의 set이 없을 때
                adj.put(e[0], new HashSet<>());
            }
            adj.get(e[0]).add(e[1]);
            
            if(adj.get(e[1]) == null){ // 노드의 set이 없을 때
                adj.put(e[1], new HashSet<>());
            }
            adj.get(e[1]).add(e[0]);
        }
        
        int depth = 0; // 현재 가장 높은 depth
        int cnt = 0; // 가장 높은 detph의 개수
        
        // [i, j]에서 i는 노드 j는 i의 현재 최소 depth
        Queue<int[]> q = new LinkedList<>();
        
        boolean[] visited = new boolean[n + 1];
        
        // 1번 큐에 넣기
        q.add(new int[]{1, 0});
        visited[1] = true;
        
        while(!q.isEmpty()){ // 큐가 빌 때까지
            // 큐에서 꺼내서
            int[] cur = q.poll();
            int curNode = cur[0];
            int curDepth = cur[1];
            
            // depth 변수보다 깊이가 깊으면 depth 업데이트 및 cnt = 1
            // 깊이가 같으면 depth++
            if (depth < curDepth){
                depth = curDepth;
                cnt = 1;
            }else{
                cnt++;
            }
            
            // 연결 되어있는 노드 중 방문 안한 거 다시 큐에 넣기(depth + 1)
            if(adj.get(curNode) == null) continue;
            Set<Integer> nextNodes = adj.get(curNode);
            for(int nextNode : nextNodes){
                if(visited[nextNode]) continue;
                q.add(new int[]{nextNode, curDepth + 1});
                visited[nextNode] = true;
            }
        }
        return cnt;
    }
}