import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int top, bottom, north, east, south, west; // 현재 주사위에 적힌 숫자
    static int n, m;
    static int x, y; // 주사위 현재 위치
    static int[][] A;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        A = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        top = bottom = north = east = south = west = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            move(Integer.parseInt(st.nextToken()));
        }
        System.out.println(sb);

    }

    static void move(int dir){
        switch (dir){
            case 1: { // 동쪽
                y++;
                if(y >= m) {
                    y--;
                    return;
                }
                int temp = top;
                top = west;
                west = bottom;
                bottom = east;
                east = temp;
                break;
            }
            case 2: { // 서쪽
                y--;
                if(y < 0) {
                    y++;
                    return;
                }
                int temp = top;
                top = east;
                east = bottom;
                bottom = west;
                west = temp;
                break;
            }
            case 3: { // 북쪽
                x--;
                if(x < 0) {
                    x++;
                    return;
                }
                int temp = top;
                top = south;
                south = bottom;
                bottom = north;
                north = temp;
                break;
            }
            case 4: { // 남쪽
                x++;
                if (x >= n){
                    x--;
                    return;
                }
                int temp = top;
                top = north;
                north = bottom;
                bottom = south;
                south = temp;
                break;
            }
        }

        if(A[x][y] == 0){
            A[x][y] = bottom;
        }else{
            bottom = A[x][y];
            A[x][y] = 0;
        }

        sb.append(top).append("\n");

    }

}