import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int deleted;
    static int leafCount = 0;
    static ArrayList<Integer>[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        int root = -1;
        for (int i = 0; i < n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1){ // 부모가 root인 경우
                root = i;
            }else{
                tree[parent].add(i); // 인접리스트 만들기
            }
        }

        deleted = Integer.parseInt(br.readLine());

        // root 삭제시 0 출력
        if(deleted == root){
            System.out.println("0");
            return;
        }

        dfs(root);
        System.out.println(leafCount);
    }

    private static void dfs(int node) {
        boolean isLeaf = true;
        for (int child : tree[node]) {
            if (child == deleted) continue; // 삭제된 노드가 자식에 있는 경우
            isLeaf = false;
            dfs(child);
        }

        if(isLeaf){
            leafCount++;
        }
    }
}
