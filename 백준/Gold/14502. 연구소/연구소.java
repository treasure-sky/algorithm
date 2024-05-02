import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n, m, nonVirusCnt = 0, nonVirusMax = 0;
    static int[] dr = new int[]{-1, 1, 0, 0};
    static int[] dc = new int[]{0, 0, -1, 1};
    static int[][] map;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int input = Integer.parseInt(st.nextToken());
                map[i][j] = input;
                if(input == 0) nonVirusCnt++;
            }
        }

        combine(0,0,0);
        System.out.println(nonVirusMax - 3);

    }

    /**
     * 벽 3개를 세울 수 있는 경우를 모두 찾아 dfs()를 돌리는 메서드
     * @param size 지금까지 세운 벽 개수
     * @param ptrR 다음 세워야 할 벽의 row
     * @param ptrC 다음 세워야 할 벽의 col
     */
    public static void combine(int size, int ptrR, int ptrC){
        if(size == 3) dfs();
        else{
            for (int i = ptrR; i < n; i++) {
                for (int j = (i == ptrR ? ptrC : 0); j < m; j++) {
                    if(map[i][j] != 0) continue;
                    map[i][j] = 1;
                    combine(size + 1, i, j + 1);
                    map[i][j] = 0;
                }
            }
        }
    }


    public static void dfs(){
        int[][] mapCopy = new int[n][m];
        for (int i = 0; i < n; i++) {
            System.arraycopy(map[i], 0, mapCopy[i], 0, m);
        }

        int nonVirusLeft = nonVirusCnt;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(mapCopy[i][j] == 2){
                    nonVirusLeft -= _dfs(mapCopy, i, j);
                }
            }
        }
        if(nonVirusLeft > nonVirusMax) nonVirusMax = nonVirusLeft;
    }

    public static int _dfs(int[][] mapCopy, int virusR, int virusC){
        int virus = 0; // 해당 dfs에서 생성된 virus 수
        for (int i = 0; i < 4; i++) {
            int nextR = virusR + dr[i];
            int nextC = virusC + dc[i];
            if(nextR >= n || nextR < 0 || nextC >= m || nextC < 0) continue;
            int next = mapCopy[nextR][nextC];
            if(next != 0) continue;
            mapCopy[nextR][nextC] = 2;
            virus += 1 + _dfs(mapCopy, nextR, nextC);
        }
        return virus;
    }
}
