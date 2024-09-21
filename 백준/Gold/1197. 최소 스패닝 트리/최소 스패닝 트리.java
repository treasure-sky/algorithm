import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        Map<Integer, Set<int[]>> map = new HashMap<>();
        boolean[] visited = new boolean[v + 1];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });


        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (map.containsKey(a)) {
                map.get(a).add(new int[]{b, c});
            }else{
                Set<int[]> set = new HashSet<>();
                set.add(new int[]{b, c});
                map.put(a, set);
            }
            if (map.containsKey(b)) {
                map.get(b).add(new int[]{a, c});
            }else{
                Set<int[]> set = new HashSet<>();
                set.add(new int[]{a, c});
                map.put(b, set);
            }

        }

        int visitCnt = 1, res = 0;
        Set<int[]> setInit = map.get(1);
        queue.addAll(setInit);
        visited[1] = true;

        while (visitCnt != v) {
            int[] nowNode = queue.poll();
            if (visited[nowNode[0]]) continue;
            int nextNode = nowNode[0];

            visited[nextNode] = true;
            visitCnt++;
            res += nowNode[1];

            Set<int[]> nowSet = map.get(nextNode);
            if (nowSet != null) queue.addAll(nowSet);
        }

        System.out.print(res);


    }
}
