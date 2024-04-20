import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, max;
    static Map< Integer, Map<Integer, Integer> > tree = new HashMap<>();

    /**
     * nodeP를 루트로 하는 트리의 길이를 반환.
     * nodeP를 루트로 하는 트리의 지름을 max에 갱신
     * @param nodeP
     * @return nodeP를 루트로 하는 트리의 지름을 반환
     */
    static public int dfs(int nodeP){
        if(tree.get(nodeP) == null) return 0; // leaf node면 0 반환
        int max1 = 0, max2 = 0; // 최댓값 두개

        // child node가 1개 이상.
        for(int nodeC : tree.get(nodeP).keySet()){
            int length = dfs(nodeC) + tree.get(nodeP).get(nodeC); // 서브트리의 길이 + nodeP까지 길이
            if (max1 < length) {
                max2 = max1;
                max1 = length;
            } else if (max2 < length) {
                max2 = length;
            }
        }

        max = Math.max(max, max1 + max2);
        return max1;

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            if(!tree.containsKey(parent)){ // 부모노드가 트리에 없을 때 추가
                tree.put(parent, new HashMap<>());
            }
            // 트리 정보 추가
            tree.get(parent).put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        dfs(1);
        System.out.print(max);

    }
}
