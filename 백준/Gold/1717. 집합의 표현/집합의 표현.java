import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        parents = new int[n + 1];
        for (int i = 0; i <= n; i++) parents[i] = i;

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if (type == 0) {
                merge(n1, n2);
            }else{
                int n1P = find(n1);
                int n2P = find(n2);
                if(n1P == n2P){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }

    }

    public static int find(int node) {
        if(parents[node] == node) return node;
        return parents[node] = find(parents[node]);
    }

    public static void merge(int n1, int n2) {
        int n1P = find(n1);
        int n2P = find(n2);
        if(n1P != n2P){
            parents[n1P] = n2P;
        }
    }

}
