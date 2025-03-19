import java.util.*;
class Solution {
    static int[] dr = new int[]{1, 0, -1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    
    public int solution(String[] board) {
        int answer = -1;
        int[] rPos = new int[2];
        int[] gPos = new int[2];
        
        for(int i=0; i<board.length; i++){
            String s = board[i];
            if(s.contains("R")){
                rPos[0] = i;
                rPos[1] = s.indexOf("R");
            } else if(s.contains("G")){
                gPos[0] = i;
                gPos[1] = s.indexOf("G");
            }
            
        }
        
        boolean visited[][] = new boolean[board.length][board[0].length()];
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{rPos[0], rPos[1], 0});
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];
            int movedCnt = cur[2];
            if(curR == gPos[0] && curC == gPos[1]){
                answer = movedCnt;
                return answer;
            }
            
            for(int i=0; i<4; i++){
                int nextR = curR;
                int nextC = curC;
                while(nextR+dr[i] >= 0 && nextR+dr[i] < board.length && nextC+dc[i] >= 0 && nextC+dc[i] < board[0].length() && board[nextR+dr[i]].charAt(nextC+dc[i]) != 'D'){
                    nextR += dr[i];
                    nextC += dc[i];
                }
                if(!visited[nextR][nextC]){
                    q.add(new int[]{nextR, nextC, movedCnt+1});
                    visited[nextR][nextC] = true;
                }
            }
            
        }
        
        return answer;
    }
}
// bfs q로 구현
// 한 번 방문한 곳에 방문시 무시
// 큐 빌 때까지 반복