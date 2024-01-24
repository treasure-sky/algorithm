import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Integer[] input = new Integer[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        // 중복 제거 단계
        List<Integer> list = Arrays.asList(input);
        Set<Integer> set = new HashSet<>(list);
        list = new ArrayList<>(set);

        Collections.sort(list);

        Map<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int num : list) {
            map.put(num, idx++);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(map.get(input[i])).append(' ');
        }
        System.out.println(sb);

    }
}
