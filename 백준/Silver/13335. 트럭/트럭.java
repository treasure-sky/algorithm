import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 트럭 수
        int w = Integer.parseInt(st.nextToken()); // 다리 길이
        int L = Integer.parseInt(st.nextToken()); // 최대 하중
        int[] A = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0; // 다리 위에 올라가있는 트럭의 총 무게
        int truckCnt = 0; // 다리 위에 올라가있는 트럭 개수
        int time = 0; // 소요된 시간
        Queue<Integer> q = new LinkedList<>(); // 다리 위 상태를 나타내는 큐
        for (int i = 0; i < w; i++) {
            q.add(0); // 다리 0으로 초기화
        }

        for (int i = 0; i < n; i++) {
            int truckOut = q.poll(); // 다리를 통과하는 트럭 무게
            int truckIn = A[i]; // 다리에 진입하는 트럭 무게
            sum += truckIn - truckOut; // 다리에 걸리게 되는 무게
            if (truckOut == 0) truckCnt++; // 다리를 통과한 트럭이 없으면
            time++;
            // 최대 하중을 넘으면
            while(sum > L){
                int truckOutMore= q.poll();
                if(truckOut != 0) truckCnt--;
                sum -= truckOutMore;
                q.add(0);
                time++;
            }
            q.add(truckIn);
        }
        System.out.println(time + w); // 모든 차가 다리 통과하는 시각

    }
}
