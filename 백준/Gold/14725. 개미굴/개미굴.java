import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    public static class Node {
        TreeMap<String, Node> child;

        public Node() {
            this.child = new TreeMap<>();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        Node root = new Node();

        for (int i = 0; i < n; i++) {
            Node curNode = root;
            StringTokenizer st = new StringTokenizer(br.readLine());
            int depth = Integer.parseInt(st.nextToken());
            for (int j = 0; j < depth; j++) {
                String token = st.nextToken();
                Node nextNode;
                if (!curNode.child.containsKey(token)) {
                    nextNode = new Node();
                    curNode.child.put(token, nextNode);
                }else{
                    nextNode = curNode.child.get(token);
                }
                curNode = nextNode;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Node> entry : root.child.entrySet()) {
            dfs(sb, entry.getValue(), 1, entry.getKey());
        }
        System.out.println(sb);

    }

    public static void dfs(StringBuilder sb, Node node, int depth, String token){
        for (int i = 0; i < depth - 1; i++) {
            sb.append("--");
        }
        sb.append(token).append("\n");

        if(!node.child.isEmpty()){
            for (Map.Entry<String, Node> entry : node.child.entrySet()) {
                dfs(sb, entry.getValue(), depth + 1, entry.getKey());
            }
        }

    }

}
