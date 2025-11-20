import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static HashSet<Integer>[] adj;
    static int N;
    static int[] res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        adj = new HashSet[N+1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if(adj[n1] == null) adj[n1] = new HashSet<>();
            if(adj[n2] == null) adj[n2] = new HashSet<>();

            adj[n1].add(n2);
            adj[n2].add(n1);
        }

        res = new int[N + 1];
        subtreeNodeCnt(R, -1); // 루트노드는 부모 없음
        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            System.out.println(res[query]);
        }

    }

    public static int subtreeNodeCnt(int nodeNum, int parentNum){
        int nodeSum = 1;
        if(adj[nodeNum].size() == 1 && adj[nodeNum].contains(parentNum)) { // leaf 노드면
            res[nodeNum] = nodeSum;
            return nodeSum;
        }
        for(int adjNode : adj[nodeNum]){
            if(adjNode == parentNum) continue; // 부모 노드면 무시
            nodeSum += subtreeNodeCnt(adjNode, nodeNum);
        }

        res[nodeNum] = nodeSum;
        return nodeSum;
    }

}
