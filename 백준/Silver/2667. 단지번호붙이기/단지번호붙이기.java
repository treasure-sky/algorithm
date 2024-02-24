import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int n, addr;
    static int[][] input;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n][n];
        input = new int[n][n];
        addr = 0;

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < n; j++) {
                input[i][j] = str.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && input[i][j] == 1) {
                    addr++;
                    visited[i][j] = true;
                    list.add(1);
                    dfs(i, j);
                }
            }
        }

        Collections.sort(list);
        
        System.out.println(addr);
        for (int i = 0; i < addr; i++) {
            System.out.println(list.get(i));
        }


    }

    static void dfs(int row, int col) {
        for (int i = 0; i < 4; i++) {
            int nextR = row + dx[i];
            int nextC = col + dy[i];

            if(nextR < 0 || nextR > n-1 || nextC < 0 || nextC > n-1) continue;
            if(visited[nextR][nextC]) continue;
            if(input[nextR][nextC] != 1) continue;

            visited[nextR][nextC] = true;
            input[nextR][nextC] = addr;
            list.set(addr - 1, list.get(addr - 1) + 1);
            dfs(nextR, nextC);
        }
    }
}
