import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int test = 0; test < T; test++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] needTimes = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                needTimes[i] = Integer.parseInt(st.nextToken());
            }

            List<Integer>[] needNodes = new ArrayList[n];
            for (int i = 0; i < n; i++) {
                needNodes[i] = new ArrayList<>();
            }
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                needNodes[to - 1].add(from - 1); // 1-base -> 0-base
            }

            int target = Integer.parseInt(br.readLine()) - 1;

            int[] memo = new int[n];
            Arrays.fill(memo, -1);

            sb.append(bt(target, needTimes, needNodes, memo)).append("\n");
            
        }
        System.out.println(sb);

    }

    public static int bt(int target, int[] needTimes, List<Integer>[] needNodes, int[] memo){
        if(needNodes[target].isEmpty()) return needTimes[target]; // 필요한 선행 노드 없음

        int max = 0;
        for(int needNode : needNodes[target]){
            int calc;
            if(memo[needNode] != -1){
                calc = memo[needNode];
            }else{
                calc = bt(needNode, needTimes, needNodes, memo);
                memo[needNode] = Math.max(memo[needNode], calc);
            }
            max = Math.max(max, calc);
        }
        return max + needTimes[target];
    }

}
