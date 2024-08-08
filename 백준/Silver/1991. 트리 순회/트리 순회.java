import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static Map<Character, char[]> map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            char node = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);
            map.put(node, new char[]{left, right});
        }
        preorder('A');
        System.out.println();
        inorder('A');
        System.out.println();
        postorder('A');

    }

    public static void preorder (char now){
        if(now == '.') return;
        System.out.print(now);
        preorder(map.get(now)[0]);
        preorder(map.get(now)[1]);
    }
    public static void inorder (char now){
        if(now == '.') return;
        inorder(map.get(now)[0]);
        System.out.print(now);
        inorder(map.get(now)[1]);
    }
    public static void postorder (char now){
        if(now == '.') return;
        postorder(map.get(now)[0]);
        postorder(map.get(now)[1]);
        System.out.print(now);
    }
}
