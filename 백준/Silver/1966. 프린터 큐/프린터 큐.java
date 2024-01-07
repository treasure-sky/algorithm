import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Doc {
        int index, importance;

        public Doc(int index, int importance) {
            this.index = index;
            this.importance = importance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tc = Integer.parseInt(br.readLine());
        for (int i = 0; i < tc; i++) {
            Queue<Doc> queue = new LinkedList<>();
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                queue.add(new Doc(j, Integer.parseInt(st.nextToken())));
            }
            int passIdx = 1;
            while (!queue.isEmpty()) {
                Doc curDoc = queue.poll();
                int maxImp = -1;
                for (Doc doc : queue) {
                    if (doc.importance > maxImp) {
                        maxImp = doc.importance;
                    }
                }
                if (maxImp > curDoc.importance) {
                    queue.add(curDoc);
                } else {
                    if (curDoc.index == m) {
                        System.out.println(passIdx);
                        break;
                    } else {
                        passIdx++;
                    }

                }
            }
        }
    }
}
