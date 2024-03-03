import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            int input = Integer.parseInt(br.readLine());
            if (input == 0) {
                if (queue.size() != 0) {
                    sb.append(queue.poll()).append("\n");
                } else {
                    sb.append("0\n");
                }
            } else {
                queue.add(input);
            }
        }
        System.out.print(sb.toString());
    }
}
