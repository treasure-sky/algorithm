import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int n, m;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i; // 처음에는 모두 자기 자신이 parent
        }

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            int root1 = find(node1);
            int root2 = find(node2);

            if (root1 == root2) { // 연결하려는 노드가 이미 같은 그룹일 때
                System.out.println(i);
                System.exit(0);
            }

            // 같은 그룹이 아니면 같은 그룹으로
            parent[root1] = root2;

        }
        System.out.println(0); // 나머지는 답 없음. 0

    }

    public static int find(int nodeNum) {
        if (parent[nodeNum] == nodeNum) { // 자기 자신이 root인 경우
            return nodeNum;
        }

        return parent[nodeNum] = find(parent[nodeNum]);
    }

}
