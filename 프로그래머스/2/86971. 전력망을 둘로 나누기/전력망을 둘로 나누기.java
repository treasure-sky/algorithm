import java.util.*;
class Solution {
    public int solution(int n, int[][] wires) {
        Set<Integer>[] A = new HashSet[n]; // 인접리스트
        for(int i=0; i<n;i++){
            A[i] = new HashSet<>();
        }
        for(int[] e : wires){
            int p1 = e[0];
            int p2 = e[1];
            A[p1 - 1].add(p2 - 1);
            A[p2 - 1].add(p1 - 1);
        }
        
        int minDiff = Integer.MAX_VALUE;
        for(int i=0; i<wires.length; i++){ // edge를 모두 순회
            int p1 = wires[i][0] - 1;
            int p2 = wires[i][1] - 1;
            A[p1].remove(p2);
            A[p2].remove(p1);
            
            
            boolean[] visited = new boolean[n];
            Queue<Integer> q = new LinkedList<>();
            q.add(p1);
            visited[p1] = true;
            int sumOfPart = 1;
            while(!q.isEmpty()){
                int p = q.poll();
                for(int linkedP : A[p]){
                    if(visited[linkedP]) continue;
                    visited[linkedP] = true;
                    q.add(linkedP);
                    sumOfPart++;
                }
            }
            if(Math.abs((n - sumOfPart) - sumOfPart) < minDiff){
                minDiff = Math.abs((n - sumOfPart) - sumOfPart);
            }
            A[p1].add(p2);
            A[p2].add(p1);
        }
        
        
        
        return minDiff;
    }
}