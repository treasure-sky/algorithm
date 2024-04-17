import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// IDEA
// 큐에 set을 넣기
public class Main {
    public static void main(String[] args) throws IOException {

        boolean isPossible = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<Integer>[] adjList = new ArrayList[n+1];
        for (int i = 0; i <= n; i++) { // adj배열 초기화
            adjList[i] = new ArrayList<>();
        }
        StringTokenizer st;
        for (int i = 0; i < n - 1; i++) { // adj 입력
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList[start].add(end);
            adjList[end].add(start);
        }

        st = new StringTokenizer(br.readLine());
        Queue<Integer> order = new LinkedList<>();
        while (st.hasMoreTokens()) {
            order.add(Integer.parseInt(st.nextToken()));
        }

        if (order.peek() != 1) {
            System.out.print("0");
            System.exit(0);
        }


        boolean[] visited = new boolean[n + 1];

        Queue< Set<Integer> > queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();
        set.add(1);
        queue.add(set);
        visited[1] = true;
        int visitedCnt = 1;

        while (!queue.isEmpty()) {
            Set<Integer> setNow = queue.poll();

            while (!setNow.isEmpty()) {

                int nowNum = order.poll();

                if (!setNow.contains(nowNum)) { // 불가능한 경우
                    isPossible = false;
                    break;
                }

                Set<Integer> setNext = new HashSet<>(); // setNow 노드 하나당 set 하나 만들어줌.

                for (int i : adjList[nowNum]) {
                    if(!visited[i]){ // 방문 안했을 때
                        visited[i] = true;
                        visitedCnt++;
                        setNext.add(i);
                    }
                }
                setNow.remove(nowNum);
                if (!setNext.isEmpty()) {
                    queue.add(setNext);
                }

            }

        }

        if (isPossible && visitedCnt == n) {
            System.out.printf("1");
        } else {
            System.out.printf("0");
        }
    }
}
