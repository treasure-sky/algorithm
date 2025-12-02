import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        int[][] A = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = i+1; j <= N ; j++) {
                if(A[i][j] == 1){
                    merge(i, j);
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            find(i);
        }



        st = new StringTokenizer(br.readLine());
        int prevP = parents[Integer.parseInt(st.nextToken())];
        for (int i = 1; i < M; i++) {
            int curP = parents[Integer.parseInt(st.nextToken())];
            if (prevP != curP) {
                System.out.println("NO");
                System.exit(0);
            }
        }
        System.out.println("YES");
    }

    static int find(int n){
        if(parents[n] == n) return n;
        return parents[n] = find(parents[n]);
    }

    static void merge(int n1, int n2) {
        int n1P = find(n1);
        int n2P = find(n2);

        if(n1P != n2P){
            parents[n1P] = n2P;
        }

    }
}
