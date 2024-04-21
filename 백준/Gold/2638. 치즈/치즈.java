import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;

// IDEA
// BFS로 공기 순회
//


public class Main {
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int cheezeCnt = 0;
        int time = 0;
        boolean[][] M = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(st.nextToken());
                if(input == 1){
                    cheezeCnt++;
                    M[i][j] = true;
                }
            }
        }
        while (cheezeCnt != 0){ // 치즈가 다 녹을 때까지
            time++;
            boolean[][] visited = new boolean[n][m];
            Map<SimpleEntry<Integer, Integer>, Integer> meltCandidates = new HashMap<>();
            Queue<SimpleEntry<Integer, Integer>> queue = new LinkedList<>();
            queue.add(new SimpleEntry<>(0, 0));
            while (!queue.isEmpty()) { // 한 타임에 대한 BFS
                SimpleEntry<Integer, Integer> now = queue.poll();
                int nowX = now.getKey();
                int nowY = now.getValue();

                for (int i = 0; i < 4; i++) {
                    int nextX = nowX + dx[i];
                    int nextY = nowY + dy[i];
                    if(nextX < 0 || nextX > n-1 || nextY < 0 || nextY > m-1) continue; // 범위 초과 경우 PASS
                    if(visited[nextX][nextY]) continue; // 방문한 경우 PASS

                    SimpleEntry<Integer, Integer> xySet = new SimpleEntry<>(nextX, nextY);
                    if (M[nextX][nextY]) { // 치즈가 있을 때, 다음에 녹을 치즈 후보군으로
                        if(meltCandidates.containsKey(xySet)){
                            meltCandidates.put(xySet, meltCandidates.get(xySet) + 1);
                        }else{
                            meltCandidates.put(xySet, 1);
                        }
                    }else{
                        // 치즈도 없고 방문도 안한 공기일 때
                        visited[nextX][nextY] = true;
                        queue.add(xySet);
                    }
                }

            }
            // 다음에 녹을 치즈 후보군 중 공기와 두면 이상 접촉한 치즈를 녹임
            for (SimpleEntry<Integer, Integer> meltCandidate : meltCandidates.keySet()) {
                if(meltCandidates.get(meltCandidate) >= 2){
                    M[meltCandidate.getKey()][meltCandidate.getValue()] = false;
                    cheezeCnt--;
                }
            }

        }
        System.out.print(time);


    }
}

