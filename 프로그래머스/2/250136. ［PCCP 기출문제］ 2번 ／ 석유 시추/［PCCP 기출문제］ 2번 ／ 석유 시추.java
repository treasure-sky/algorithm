import java.util.*;
class Solution {
    static int[] dx = new int[]{-1, 0, 1, 0};
    static int[] dy = new int[]{0, -1, 0, 1};
    public int solution(int[][] land) {       
        int depth = land.length;
        int width = land[0].length;
        
        int[] sum = new int[width];
        boolean[][] visited = new boolean[depth][width];

        for(int d=0; d< depth; d++){
            for(int w=0; w<width; w++){
                if(land[d][w] == 1 && !visited[d][w]){ // 석유O && 방문X
                    Queue<int[]> queue = new LinkedList<>();
                    boolean[] oilWidth = new boolean[width];
                    int oilCnt = 1;
                    queue.add(new int[]{d, w});
                    visited[d][w] = true;
                    oilWidth[w] = true;
                    while(!queue.isEmpty()){
                        int[] cur = queue.poll();
                        for(int i=0; i<4;i++){
                            int nextD = cur[0] + dy[i];
                            int nextW = cur[1] + dx[i];
                            if(nextD < 0 || nextD >= depth || nextW < 0 || nextW >= width) continue;
                            if(visited[nextD][nextW]) continue;
                            if(land[nextD][nextW] == 0) continue;
                            queue.add(new int[]{nextD, nextW});
                            visited[nextD][nextW] = true;
                            oilWidth[nextW] = true;
                            oilCnt++;
                        }
                    }
                    for(int i=0; i<width;i++){
                        if(oilWidth[i]){
                            sum[i] += oilCnt;
                        }
                    }
                }
            }
        }
        
        int answer = 0;
        for(int i=0; i<width; i++){
            if(answer < sum[i]){
                answer = sum[i];
            }
        }
        
        return answer;
    }
    
}


