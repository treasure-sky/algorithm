import java.util.*;

class Solution {
    
    static int[] parents;
    
    static int find(int node){
        if(parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }
    
    static void combine(int node1, int node2){
        int parents1 = find(node1);
        int parents2 = find(node2);
        if(parents1 == parents2) return;
        
        parents[parents1] = parents2;
    }
    
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b)-> a[2] - b[2]);
        
        parents = new int[n];
        for(int i=0; i<n; i++){
            parents[i] = i;
        }
        int visitCnt = 0;
        int answer = 0;
        
        for(int[] adj : costs){
            if(visitCnt >= n - 1) break;
            
            int node1 = adj[0];
            int node2 = adj[1];
            int cost = adj[2];
            
            if(find(node1) == find(node2)) continue;
            
            combine(node1, node2);
            System.out.println("1");
            visitCnt++;
            answer += cost;
        }
        
        return answer;
    }
}