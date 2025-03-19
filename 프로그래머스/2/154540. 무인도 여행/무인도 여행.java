import java.util.*;
class Solution {
    static int[] dr = new int[]{1, 0, -1, 0};
    static int[] dc = new int[]{0, 1, 0, -1};
    static int sumOfIsland = 0;
    public int[] solution(String[] maps) {        
        //for문으로 모든 칸 순회
        //visit으로 섬 구분
        List<Integer> list = new ArrayList<>();
        boolean[][] visited = new boolean[maps.length][maps[0].length()];
        for(int i=0; i<maps.length; i++){
            for(int j=0; j<maps[0].length(); j++){
                if(visited[i][j]) continue;
                if(maps[i].charAt(j) == 'X') continue;
                sumOfIsland = maps[i].charAt(j)-'0';
                visited[i][j] = true;
                dfs(i, j, visited, maps);
                list.add(sumOfIsland);
            }
        }
        if(list.size() == 0) return new int[]{-1};
        int[] answer = new int[list.size()];
        for(int i =0; i< answer.length;i++){
            answer[i] = list.get(i);
        }
        Arrays.sort(answer);
        
        return answer;

    }
    
    public static void dfs(int r, int c, boolean[][] visited, String[] maps){
        for(int i=0; i< 4; i++){
            int nR = r + dr[i];
            int nC = c + dc[i];
            if(nR < 0 || nR >= maps.length || nC < 0 || nC >= maps[0].length()) continue;
            if(maps[nR].charAt(nC) == 'X') continue;
            if(visited[nR][nC]) continue;
            visited[nR][nC] = true;
            sumOfIsland += maps[nR].charAt(nC) - '0';
            dfs(nR, nC, visited, maps);
        }
        return;
    }
}