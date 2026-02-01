import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<Integer>[] s = new HashSet[n + 1];
        int[] indegree = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            s[i] = new HashSet<>();
        }

        // a -> b list 배열에 저장(중복 처리)
        // 진입차수 배열에도 반영
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int start;
            for (int j = 1; j < c; j++) {
                start = end;
                end = Integer.parseInt(st.nextToken());
                if(!s[start].contains(end)){
                    s[start].add(end);
                    indegree[end]++;
                }
            }
        }

        // 진입차수 0인 애들 큐에 모두 넣기
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            if(indegree[i] == 0){
                q.add(i);
            }
        }

        List<Integer> res = new ArrayList<>();
        int cnt = 0;

        // while(큐 빌때까지) 큐에서 하나 꺼내서 결과 list에 저장
        // 그리고 연결된 것들 진입차수 -1
        // 근데 그 결과가 0 이면 큐에 바로 넣기

        while(!q.isEmpty()){
            int cur = q.poll();
            res.add(cur);
            cnt++;
            for(int next : s[cur]){
                if(--indegree[next] == 0){
                    q.add(next);
                }
            }
        }

        // 결과 list 크기가 n보다 작으면 0출력(사이클 존재)
        if (res.size() < n) {
            System.out.println(0);
        }
        // 아니면 list 출력
        else{
            for (int i = 0; i < n; i++) {
                System.out.println(res.get(i));
            }
        }

    }

}
