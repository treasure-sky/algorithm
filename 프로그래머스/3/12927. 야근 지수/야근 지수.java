import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int work: works){
            pq.offer(work);
        }
        int remainTime = n;
        while(n-- > 0){
            int maxTask = pq.poll();
            if(maxTask == 0) break;
            pq.offer(maxTask - 1);
        }
        
        while(!pq.isEmpty()){
            answer += Math.pow(pq.poll(), 2);
        }
        
        return answer;
    }
}