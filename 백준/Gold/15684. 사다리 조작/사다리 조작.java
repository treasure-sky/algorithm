import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int end;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        end = h * (n - 1); // 마지막 탐색 위치

        boolean[][] linked = new boolean[h][n-1]; // linked[i][j]=true :i 와 i+1 번 세로선이 j번 가로선에서 연결됨을 의미

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1; // 0부터 시작
            int b = Integer.parseInt(st.nextToken()) - 1;
            linked[a][b] = true;
        }

        if(isValid(linked)){
            System.out.println(0);
            System.exit(0);
        };

        for(int i = 1; i <= 3; i++) { // 0-3 개의 선을 추가하는 경우
            dfs(linked, 0, 0, i);
        }
        System.out.println(-1);

    }

    public static void dfs(boolean[][] linked, int curPos, int cnt, int goal) {

        if(cnt == goal && isValid(linked)){
            // 정답을 찾으면 추가한 개수 출력하고 종료
            System.out.println(cnt);
            System.exit(0);
        }

        if(cnt >= goal) return;
        if(curPos + 1 > end) return; // 마지막 탐색위치를 넘어가면 리턴

        int col = curPos % (n-1); // 현재 가로 인덱스
        int row = curPos / (n-1); // 현재 세로 인덱스 (높이)

        if(linked[row][col] == true){
            dfs(linked, curPos + 1, cnt, goal);
            return;
        }

        dfs(linked, curPos + 1, cnt, goal);
        linked[row][col] = true;
        dfs(linked, curPos + 1, cnt + 1, goal);
        linked[row][col] = false; // 복구

    }

    public static boolean isValid(boolean[][] linked) {
        for (int i = 0; i < n; i++) { // 세로 선 별로 구하기
            int col = i;
            int depth = 0;
            while(depth < linked.length) {
                if(col >= 1 && linked[depth][col-1]) { // 좌측 연결
                    col--;
                } else if(col < n-1 && linked[depth][col]) { // 우측 연결
                    col++;
                }
                depth++;
            }
            if(col != i) return false;
        }

        return true;
    }

}
